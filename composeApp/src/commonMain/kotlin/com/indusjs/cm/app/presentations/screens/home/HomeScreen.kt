package com.indusjs.cm.app.presentations.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.ic_profile
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

val HomeScreen: String = "home_screen"

@Composable
fun HomeScreen_(navController: NavHostController) {
    MainApp()
}

// Define data class for Bottom Navigation items
data class BottomNavItem(val tabId:Int, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)

@Composable
fun MainApp() {
    // Create a list of BottomNavItem
    val items = listOf(
        BottomNavItem(0,"Home", Icons.Filled.Home, "home"),
        BottomNavItem(1,"Search", Icons.Filled.Search, "search"),
        BottomNavItem(2,"Cart", Icons.Filled.ShoppingCart, "cart"),
        BottomNavItem(3,"Profile", Icons.Filled.Person, "profile"),
        BottomNavItem(4,"Settings", Icons.Filled.Settings, "settings")
    )

    // Use remember to survive configuration changes
    val selectedItem = remember { mutableStateOf(items[0]) }
        Scaffold(modifier = Modifier.height(1.dp), // here I need to check
            // Bottom navigation bar
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.fillMaxWidth()//.height(56.dp),// here I need to check
                ) {
                    items.forEach { navigationItem ->
                        NavigationBarItem(
                            icon = {
                                androidx.compose.material3.Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = navigationItem.title,
                                )
                            },
                            label = {
                                Text(
                                    navigationItem.title,
                                    fontSize = 9.sp
                                )
                            }, // Reduced font size for labels
                            selected = selectedItem.value == navigationItem,
                            onClick = {
                                selectedItem.value = navigationItem // Update selected item
                            },
                            //Make the icon and text appear
                            alwaysShowLabel = true,
                        )
                    }
                }
            },
            topBar = {TopAppBarSection()},
            content = { paddingValues ->
                // Content of each screen based on the selected item
                Surface(
                    modifier = Modifier
                        .padding(paddingValues) // Apply padding from Scaffold
                        .fillMaxSize(),
                    color = Color.White
                ) {
                    when (selectedItem.value.tabId) {
                        0 -> Tab1Screen()
                        1 -> Tab2Screen()
                        2 -> Tab3Screen()
                        3 -> Tab4Screen()
                        4 -> Tab5Screen()
                    }
                }
            }
        )

}

@Composable
fun TopAppBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "Person Name", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Society Name", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Row {
            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(Icons.Outlined.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(Icons.Outlined.MailOutline, contentDescription = "Chat")
            }
        }
    }
}

// Preview Composable
//@Preview(showBackground = true)
@Preview
@Composable
fun DefaultPreview() {
    MainApp()
}