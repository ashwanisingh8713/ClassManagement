package com.indusjs.cm.app.presentations.screens.login


import SignInResponse
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.ic_parking
import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.app.presentations.screens.home.TabsMainScreen
import com.indusjs.cm.app.viewmodels.login.SignInViewModel
import com.indusjs.platform.DataManager
import com.indusjs.platform.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin

// Regex for basic email validation.
private val EMAIL_ADDRESS_REGEX = Regex(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

/**
 * Utility object for input validation.
 */
object ValidationUtils {



    /**
     * Validates an email address.
     * @param email The email string to validate.
     * @return True if the email is valid, false otherwise.
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && EMAIL_ADDRESS_REGEX.matches(email)
    }

    /**
     * Validates a password based on several criteria.
     * @param password The password string to validate.
     * @return A [PasswordValidationResult] indicating if the password is valid and a corresponding message.
     */
    fun isValidPassword(password: String): PasswordValidationResult {
        if (password.isBlank()) {
            return PasswordValidationResult(isValid = false, message = "Password cannot be empty.")
        }
        if (password.length < 8) {
            return PasswordValidationResult(isValid = false, message = "Password must be at least 8 characters long.")
        }
        if (!password.any { it.isDigit() }) {
            return PasswordValidationResult(isValid = false, message = "Password must contain at least one digit.")
        }
        if (!password.any { it.isUpperCase() }) {
            return PasswordValidationResult(isValid = false, message = "Password must contain at least one uppercase letter.")
        }
        if (!password.any { it.isLowerCase() }) {
            return PasswordValidationResult(isValid = false, message = "Password must contain at least one lowercase letter.")
        }
        if (password.any { it.isWhitespace() }) {
            return PasswordValidationResult(isValid = false, message = "Password must not contain whitespace.")
        }
        if (!password.any { !it.isLetterOrDigit() }) {
            return PasswordValidationResult(isValid = false, message = "Password must contain at least one special character (e.g., !@#\$%).")
        }
        return PasswordValidationResult(isValid = true)
    }
}

/**
 * Data class to hold the result of password validation.
 * @property isValid True if the password meets all criteria, false otherwise.
 * @property message An optional error message if validation fails.
 */
data class PasswordValidationResult(
    val isValid: Boolean,
    val message: String? = null
)


data class SignInState(
    val email: String = "radhika@gmail.com",
    val password: String = "admin",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
    val isSignInEnabled: Boolean = false
) {
    fun updateEmail(newEmail: String): SignInState {
        return copy(email = newEmail, isEmailValid = true, emailErrorMessage = null)
    }

    fun updatePassword(newPassword: String): SignInState {
        return copy(password = newPassword, isPasswordValid = true, passwordErrorMessage = null)
    }

    fun updateValidation(isEmailValid: Boolean, emailErrorMessage: String? = null, isPasswordValid: Boolean, passwordErrorMessage: String? = null): SignInState {
        return copy(
            isEmailValid = isEmailValid,
            emailErrorMessage = emailErrorMessage,
            isPasswordValid = isPasswordValid,
            passwordErrorMessage = passwordErrorMessage,
            isSignInEnabled = isEmailValid && isPasswordValid && email.isNotEmpty() && password.isNotEmpty()
        )
    }
}

object SignInValidation {
    fun validateEmail(email: String): String? {
        if (email.isEmpty()) {
            return "Email cannot be empty"
        }
        if (!isValidEmail(email)) {
            return "Enter a valid email address"
        }
        return null
    }

    /**
     * Validates an email address.
     * @param email The email string to validate.
     * @return True if the email is valid, false otherwise.
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && EMAIL_ADDRESS_REGEX.matches(email)
    }

    fun validatePassword(password: String): String? {
        if (password.isEmpty()) {
            return "Password cannot be empty"
        }
        if (password.length < 4) {
            return "Password must be at least 6 characters long"
        }
        return null
    }
}


@Composable
fun SignInScreenE(navController: NavHostController,
                  signInViewModel: SignInViewModel = getKoin().get<SignInViewModel>()) {

    val scope = rememberCoroutineScope()

    // Get DataManager instance from Koin
    val dataManager = getKoin().get<DataManager>()

    // UI-Effect
    signInViewModel.coroutineScope.launch {
        signInViewModel.effect.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.NavigateToSignUpScreen -> navController.navigate(SignUpScreen)
                is LoginContract.Effect.NavigateToHomeScreen -> {
                    // Save User logged In Status
                    scope.launch {
                        dataManager.saveUserLoggedIn()
                    }
                    // Launching the Tab Main screen
                    withContext(Dispatchers.Main) {
                        // Using withContext on Main Thread,
                        // If we call "scope.launch" in same thread, it will not work
                        navController.navigate(TabsMainScreen) {
                            popUpTo(LoginScreen) {
                                inclusive = true
                            }
                        }
                    }

                }
                else -> {}
            }
        }
    }

    // UI-State
    signInViewModel.coroutineScope.launch {
        signInViewModel.uiState.collectLatest { state->
            when(state.loginResponse) {
                is ResourceUiState.Success -> {
                    println("Ashwani Success...")
                    // Show loading indicator
                    signInViewModel.setEvent(LoginContract.Event.OnGoToHomeScreenClick)
                }
                is ResourceUiState.Loading -> {
                    // Show loading indicator
                    println("Ashwani Loading...")
                }
                is ResourceUiState.Empty -> {
                    // Show loading indicator
                    println("Ashwani Empty...")
                }
                is ResourceUiState.Idle -> {
                    // Show loading indicator
                    println("Ashwani Idel...")
                }
                is ResourceUiState.Error -> {}
                else -> {}
            }
        }
    }


    val onSignInClicked: (String, String) -> Unit = { email, password ->
        signInViewModel.setEvent(LoginContract.Event.OnLoginClick(email, password))
    }



    var signInState by remember { mutableStateOf(SignInState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF90CAF9), Color(0xFFE1F5FE))
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // App Logo
        Image(
            painter = painterResource(Res.drawable.ic_parking),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Inside
        )

        // Welcome Text
        Text(
            text = "Welcome Back!",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A237E)
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Sign in to continue",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color(0xFF3F51B5)
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            value = signInState.email,
            onValueChange = { newEmail ->
                signInState = signInState.updateEmail(newEmail)
                signInState = signInState.updateValidation(
                    isEmailValid = SignInValidation.validateEmail(newEmail) == null,
                    emailErrorMessage = SignInValidation.validateEmail(newEmail),
                    isPasswordValid = signInState.isPasswordValid,
                    passwordErrorMessage = signInState.passwordErrorMessage
                )
            },
            label = { Text("Email") },
            isError = !signInState.isEmailValid,
            supportingText = {
                if (!signInState.isEmailValid && signInState.emailErrorMessage != null) {
                    Text(signInState.emailErrorMessage!!)
                }
            },
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            value = signInState.password,
            onValueChange = { newPassword ->
                signInState = signInState.updatePassword(newPassword)
                signInState = signInState.updateValidation(
                    isPasswordValid = SignInValidation.validatePassword(newPassword) == null,
                    passwordErrorMessage = SignInValidation.validatePassword(newPassword),
                    isEmailValid = signInState.isEmailValid,
                    emailErrorMessage = signInState.emailErrorMessage
                )
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = !signInState.isPasswordValid,
            supportingText = {
                if (!signInState.isPasswordValid && signInState.passwordErrorMessage != null) {
                    Text(text = signInState.passwordErrorMessage!!)
                }
            },
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            onClick = { onSignInClicked(signInState.email, signInState.password) },
            enabled = signInState.isSignInEnabled,
        ) {
            Text("Sign In")
        }

        // Forgot Password Text
        Text(
            text = "Forgot Password?",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color(0xFF3F51B5),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )


        // Sign Up Text
        Row(
            modifier = Modifier.padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = Color(0xFF616161)
                )
            )
            TextButton(
                onClick = {
                    // Navigate to sign up screen
                    println("Navigate to Sign Up")
                }
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3F51B5)
                    )
                )
            }
        }
    }
}