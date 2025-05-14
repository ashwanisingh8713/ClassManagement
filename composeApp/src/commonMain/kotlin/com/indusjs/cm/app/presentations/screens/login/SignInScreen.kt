package com.indusjs.cm.app.presentations.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.KeyboardType
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.ic_parking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.text.KeyboardOptions as KeyboardOptions1

@Composable
fun SignInScreen() {
    // State variables
    var emailAddress by remember { mutableStateOf(TextFieldValue("")) }
    var otp by remember { mutableStateOf(TextFieldValue("")) }
    var isVerifying by remember { mutableStateOf(false) }
    var isOTPSent by remember { mutableStateOf(false) }
    var mobileNumberError by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf("") }
    var timerValue by remember { mutableStateOf(60) }
    var isTimerRunning by remember { mutableStateOf(false)}

    val logo: Painter = painterResource(Res.drawable.ic_parking) // Replace with your actual logo

    // Function to validate mobile number
    /*fun validateMobileNumber(number: String): String {
        return if (number.isEmpty()) {
            "Valid email is required"
        } else if (!number.matches(Regex("^[0-9]{10}$"))) {
            "Invalid Email Address"
        } else {
            ""
        }
    }*/

    // Function to validate Email -  Kotlin Multiplatform version
    fun validateEmail(emailValue: String): String {
        return if (emailValue.isEmpty()) {
            "Email is required"
        } else if (!Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matches(emailValue)) {
            "Invalid email address"
        } else {
            ""
        }
    }

    // Function to validate OTP
    fun validateOTP(otpValue: String): String {
        return if (otpValue.isEmpty()) {
            "OTP is required"
        } else if (otpValue.length != 6) {
            "OTP must be 6 digits"
        } else {
            ""
        }
    }


    // Function to simulate sending OTP
    suspend fun sendOTP() {
        mobileNumberError = validateEmail(emailAddress.text)
        if (mobileNumberError.isEmpty()) {
            isVerifying = true
            // Simulate network delay
            delay(2000) // Simulate a 2-second delay
            isOTPSent = true
            isVerifying = false
//            startTimer()
        }
    }

    // Function to simulate verifying OTP
    suspend fun verifyOTP() {
        otpError = validateEmail(otp.text)
        if (otpError.isEmpty()) {
            isVerifying = true
            // Simulate network delay
            delay(2000) // Simulate a 2-second delay
            isVerifying = false
            // Simulate successful sign-in
            println("Successful Sign In! Mobile Number: ${emailAddress.text}, OTP: ${otp.text}")
            //  Here you would typically navigate to the next screen or show a success message.
        }
    }

    // Function to start the timer
    fun startTimer() {
        isTimerRunning = true
        timerValue = 60
        CoroutineScope(Dispatchers.Default).launch { // Use the appropriate dispatcher
            while (timerValue > 0 && isTimerRunning) {
                delay(1000)
                timerValue--
            }
            isTimerRunning = false
        }
    }
    // Function to resend OTP
    fun resendOTP() {
        if (!isTimerRunning) {
            CoroutineScope(Dispatchers.Default).launch {
                sendOTP()
            }
        }
    }

    // Column to hold the entire content
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
            painter = logo,
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
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

        // Mobile Number Text Field
        OutlinedTextField(
            value = emailAddress,
            onValueChange = { emailAddress = it },
            label = { Text("Enter valid email", color = Color(0xFF3F51B5)) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email", tint = Color(0xFF3F51B5)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
//                .clip(RoundedCornerShape(12.dp)),
            /*colors = TextFieldDefaults.colors(
                contai = Color(0xFF3F51B5),
                unfocusedBorderColor = Color(0xFF9FA8DA),
                cursorColor = Color(0xFF3F51B5),
                textColor = Color.Black
            ),*/
            textStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 18.sp),
            keyboardOptions = KeyboardOptions1(keyboardType = KeyboardType.Email), // Use Phone keyboard
            isError = mobileNumberError.isNotEmpty()
        )
        if (mobileNumberError.isNotEmpty()) {
            Text(
                text = mobileNumberError,
                color = MaterialTheme.colorScheme.error,
                style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 12.sp),
                modifier = Modifier.padding(start = 32.dp)
            )
        }

        // OTP Text Field
        if (isOTPSent) {
            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                label = { Text("OTP", color = Color(0xFF3F51B5)) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "OTP", tint = Color(0xFF3F51B5)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp)),
                keyboardOptions = KeyboardOptions1(keyboardType = KeyboardType.Number),
                /*colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3F51B5),
                    unfocusedBorderColor = Color(0xFF9FA8DA),
                    cursorColor = Color(0xFF3F51B5),
                    textColor = Color.Black
                ),*/
                textStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 18.sp),
                isError = otpError.isNotEmpty()
            )
            if (otpError.isNotEmpty()) {
                Text(
                    text = otpError,
                    color = MaterialTheme.colorScheme.error,
                    style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 12.sp),
                    modifier = Modifier.padding(start = 32.dp)
                )
            }
        }

        // Send OTP Button / Verify OTP Button
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    if (!isOTPSent) {
                        sendOTP()
                    } else {
                        verifyOTP()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(top = 32.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F51B5),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
            enabled = !isVerifying // Disable button during verification
        ) {
            Text(
                text = if (!isOTPSent) "Submit Request" else "Verify Email",
                style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
            )
        }

        // Timer Text
        if (isOTPSent && isTimerRunning)
        {
            Text(
                text = "Resend OTP in ${timerValue} seconds",
                style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 16.sp, color = Color.Red),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        // Resend OTP Button
        if (isOTPSent && !isTimerRunning) {
            TextButton(
                onClick = {
                    resendOTP()
                },
                modifier = Modifier.padding(top = 8.dp)

            ) {
                Text(
                    text = "Resend OTP",
                    style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 16.sp, color = Color(0xFF3F51B5))
                )
            }
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

// Preview Composable
@Preview()
@Composable
fun DefaultPreview() {
    SignInScreen()
}
