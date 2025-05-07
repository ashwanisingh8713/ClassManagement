package com.indusjs.cm.app

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import classmanagement.composeapp.generated.resources.*
import com.indusjs.cm.app.presentations.screens.home.HomeScreen
import com.indusjs.cm.app.presentations.screens.home.HomeScreen_
import com.indusjs.cm.app.presentations.screens.login.LoginScreen
import com.indusjs.cm.app.presentations.screens.login.SignUpScreen
import com.indusjs.cm.app.viewmodels.login.SignInViewModel
import com.indusjs.cm.domain.usecase.login.SignInUseCase
import com.indusjs.cm.theme.AppTheme
import com.indusjs.cm.theme.LocalThemeIsDark
import kotlinx.coroutines.isActive
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.KoinContext
import org.koin.compose.getKoin


@Composable
internal fun App(
    navController: NavHostController = rememberNavController()
) {

    // Can you make full one sign in screen with compose? which must have Email and Password Sign In,  "Sign In" button, "Sign Up" button, "Forgot Password" button and "Sign In with Google" button.
    // Also, can you make it responsive for all screen sizes and orientations?
    // Also, can you make it with Material 3 design?
    // Also, can you make it with Jetpack Compose?
    // Also, can you make it with Koin for dependency injection?
    // Also, can you make it with Ktor for network requests?
    // Also, can you make it with SQLDelight for local database?
    // Also, can you make it with Coroutines for asynchronous programming?
    // Also, can you make it with Flow for reactive programming?
    // Also, can you make it with ViewModel for MVI architecture?
    // Also, can you make it with LiveData for reactive programming?

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    KoinContext {  }

    Scaffold(
        topBar = {

        },
        content = {innerPadding ->
            NavHost(
                navController = navController,
                startDestination = LoginScreen,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                composable(route = LoginScreen) {
                    LoginScreen(
                        navController = navController
                    )
                }
                composable(route = SignUpScreen) {
                    SignUpScreen(
                        navController = navController
                    )
                }
                composable(route = HomeScreen) {
                    HomeScreen_(
                        navController = navController
                    )
                }

            }

        },

    )




}

/**
 * Composable t hat displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back_button)
                    )
                }
            }
        }
    )
}