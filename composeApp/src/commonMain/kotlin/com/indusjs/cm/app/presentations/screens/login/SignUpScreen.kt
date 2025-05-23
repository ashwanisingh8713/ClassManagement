package com.indusjs.cm.app.presentations.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton // Using OutlinedButton for upload actions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.email
import classmanagement.composeapp.generated.resources.enter_your_password
import classmanagement.composeapp.generated.resources.password
import classmanagement.composeapp.generated.resources.sign_in
import classmanagement.composeapp.generated.resources.sign_up
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Camera // For profile photo
import compose.icons.feathericons.FileText // For ID proof
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class) // Opt-in for ExperimentalMaterial3Api for TopAppBar
@Composable
fun SignUpScreen(navController: NavHostController, signUpViewModel: SignUpViewModel = koinInject<SignUpViewModel>()) {

    LaunchedEffect(key1 = Unit) {
        signUpViewModel.effect.collectLatest { effect ->
            when(effect) {
                is LoginContract.Effect.NavigateToSignInScreen -> navController.navigateUp()
                else -> {}
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Box(
            modifier = Modifier.height(56.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            // Top Bar
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.sign_up),
                        style = MaterialTheme.typography.titleLarge
                    )
                }, // Use MaterialTheme typography
                navigationIcon = {
                    IconButton(onClick = { signUpViewModel.setEvent(LoginContract.Event.OnBackToSignInClick) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, // Use a filled back arrow icon
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer // Ensure icon color matches title
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer, // Use primaryContainer color
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer // Use onPrimaryContainer color for title
                ),
                modifier = Modifier.align(Alignment.TopCenter) // Align TopAppBar to the top center
            )
        }
        // Main Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 600.dp, max = 900.dp) // Set a minimum height for the column
                    .padding(horizontal = 24.dp) // Increased horizontal padding for better aesthetics
                    .padding(
                        top = 25.dp,
                        bottom = 16.dp
                    ) // Adjusted top padding to avoid overlap, added bottom padding
                    .verticalScroll(rememberScrollState()), // Make the column scrollable
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val isSecurityChecked = remember { mutableStateOf(false) }
                val isResidentialChecked = remember { mutableStateOf(false) }



                // Username and Password Fields
                TextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    label = { Text(stringResource(Res.string.email)) },
                    placeholder = { Text(stringResource(Res.string.enter_your_password)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp)) // Increased space
                /*TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text(stringResource(Res.string.password)) },
                    placeholder = { Text(stringResource(Res.string.enter_your_password)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )*/
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                    value = password.value,
                    onValueChange = { newPassword ->

                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                   // isError = !signInState.isPasswordValid,
                    supportingText = {
                        /*if (!signInState.isPasswordValid && signInState.passwordErrorMessage != null) {
                            Text(text = signInState.passwordErrorMessage!!)
                        }*/
                    },
                )
                Spacer(modifier = Modifier.height(24.dp)) // Increased space

                // User Type Checkboxes
                Text(
                    text = "Select User Type:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isSecurityChecked.value,
                            onCheckedChange = { newValue ->
                                isSecurityChecked.value = newValue
                                if (newValue) {
                                    isResidentialChecked.value = false
                                }
                            }
                        )
                        Text("Security", style = MaterialTheme.typography.bodyLarge)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isResidentialChecked.value,
                            onCheckedChange = { newValue ->
                                isResidentialChecked.value = newValue
                                if (newValue) {
                                    isSecurityChecked.value = false
                                }
                            }
                        )
                        Text("Residential", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp)) // Space before upload options

                // Profile Photo Upload Option
                OutlinedButton(
                    onClick = {
                        // TODO: Implement logic to open image picker for profile photo
                        println("Upload Profile Photo clicked!")
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(56.dp) // Consistent height for buttons
                ) {
                    Icon(
                        imageVector = FeatherIcons.Camera,
                        contentDescription = "Upload Profile Photo",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Upload Profile Photo", style = MaterialTheme.typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(16.dp)) // Space between upload buttons

                // ID Proof Upload Option
                OutlinedButton(
                    onClick = {
                        // TODO: Implement logic to open document picker for ID proof
                        println("Upload ID Proof clicked!")
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Icon(
                        imageVector = FeatherIcons.FileText,
                        contentDescription = "Upload ID Proof",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Upload ID Proof", style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(modifier = Modifier.height(32.dp)) // Space before action buttons

                Button(
                    onClick = {
                        // TODO: Implement your sign-up logic here
                        println("Signing up with:")
                        println("Email: ${username.value.text}")
                        println("Password: ${password.value.text}")
                        println("Is Security User: ${isSecurityChecked.value}")
                        println("Is Residential User: ${isResidentialChecked.value}")
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(56.dp) // Consistent height for buttons
                ) {
                    Text(
                        stringResource(Res.string.sign_up),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp)) // Space between action buttons
                OutlinedButton( // Changed to OutlinedButton for secondary action
                    onClick = { signUpViewModel.setEvent(LoginContract.Event.OnBackToSignInClick) },
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = FeatherIcons.ArrowLeft,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            stringResource(Res.string.sign_in),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp)) // Bottom padding for scrollable content
            }
        }

}
