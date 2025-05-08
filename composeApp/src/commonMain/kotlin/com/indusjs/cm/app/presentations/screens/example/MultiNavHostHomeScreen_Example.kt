package com.indusjs.cm.app.presentations.screens.example

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

// Define destinations for each tab and their nested screens
sealed class HomeDestinations(val route: String) {
    object Home : HomeDestinations("home")
    object HomeDetail : HomeDestinations("homeDetail")
}

sealed class ProfileDestinations(val route: String) {
    object Profile : ProfileDestinations("profile")
    object ProfileEdit : ProfileDestinations("profileEdit")
}

sealed class SettingsDestinations(val route: String) {
    object Settings : SettingsDestinations("settings")
    object SettingsGeneral : SettingsDestinations("settingsGeneral")
}

// Composable functions for each destination
@Composable
fun HomeScreen(onNavigateToDetail: () -> Unit) {
    Column {
        Text("Home Screen", modifier = Modifier.padding(16.dp))
        Button(onClick = onNavigateToDetail) {
            Text("Go to Home Detail")
        }
    }
}

@Composable
fun HomeDetailScreen() {
    Text("Home Detail Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun ProfileScreen(onNavigateToEdit: () -> Unit) {
    Column {
        Text("Profile Screen", modifier = Modifier.padding(16.dp))
        Button(onClick = onNavigateToEdit) {
            Text("Edit Profile")
        }
    }
}

@Composable
fun ProfileEditScreen() {
    Text("Profile Edit Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun SettingsScreen(onNavigateToGeneral: () -> Unit) {
    Column {
        Text("Settings Screen", modifier = Modifier.padding(16.dp))
        Button(onClick = onNavigateToGeneral) {
            Text("General Settings")
        }
    }
}

@Composable
fun SettingsGeneralScreen() {
    Text("General Settings Screen", modifier = Modifier.padding(16.dp))
}

// Data class for Bottom Navigation items
private data class BottomNavItem_M(val title: String, val icon: ImageVector, val route: String)

@Composable
fun BottomNavigationApp() {
    // Define NavHostControllers for each tab
    val homeNavController = rememberNavController()
    val profileNavController = rememberNavController()
    val settingsNavController = rememberNavController()

    // Define Bottom Navigation items
    val bottomNavItems = listOf(
        BottomNavItem_M("Home", Icons.Filled.Home, "home"),
        BottomNavItem_M("Profile", Icons.Filled.Person, "profile"),
        BottomNavItem_M("Settings", Icons.Filled.Settings, "settings")
    )

    // State to track the selected tab
    var selectedTab by remember { mutableStateOf(bottomNavItems[0].route) }

    Scaffold(modifier = Modifier.height(1.dp),
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val isSelected = selectedTab == item.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedTab = item.route }, // simplified tab switching
                        icon = {
                            val painter = rememberVectorPainter(image = item.icon)
                            Icon(
                                painter = painter,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) },
                    )
                }
            }
        }
    ) { paddingValues ->
        // Content of the selected tab
        when (selectedTab) {
            "home" -> HomeNavHost(navController = homeNavController, paddingValues = paddingValues)
            "profile" -> ProfileNavHost(navController = profileNavController, paddingValues = paddingValues)
            "settings" -> SettingsNavHost(navController = settingsNavController, paddingValues = paddingValues)
        }
    }
}

@Composable
fun HomeNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = HomeDestinations.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(HomeDestinations.Home.route) {
            HomeScreen(onNavigateToDetail = { navController.navigate(HomeDestinations.HomeDetail.route) })
        }
        composable(HomeDestinations.HomeDetail.route) {
            HomeDetailScreen()
        }
    }
}

@Composable
fun ProfileNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = ProfileDestinations.Profile.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(ProfileDestinations.Profile.route) {
            ProfileScreen(onNavigateToEdit = { navController.navigate(ProfileDestinations.ProfileEdit.route) })
        }
        composable(ProfileDestinations.ProfileEdit.route) {
            ProfileEditScreen()
        }
    }
}

@Composable
fun SettingsNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = SettingsDestinations.Settings.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(SettingsDestinations.Settings.route) {
            SettingsScreen(onNavigateToGeneral = { navController.navigate(SettingsDestinations.SettingsGeneral.route) })
        }
        composable(SettingsDestinations.SettingsGeneral.route) {
            SettingsGeneralScreen()
        }
    }
}
