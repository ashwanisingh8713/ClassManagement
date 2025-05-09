package com.indusjs.cm.app.presentations.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.ic_profile
import com.indusjs.cm.app.presentations.screens.home.tab1.Tab1Screen
import com.indusjs.cm.app.presentations.screens.home.tab2.Tab2Screen
import com.indusjs.cm.app.presentations.screens.home.tab3.Tab3Screen
import com.indusjs.cm.app.presentations.screens.home.tab4.Tab4Screen
import com.indusjs.cm.app.presentations.screens.home.tab5.Tab5Screen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

val TabsMainScreen: String = "tabs_main_screen"


// Define data class for Bottom Navigation items
private data class BottomNavItem(val tabId:Int, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)

@Composable
fun TabsScreen(navController: NavHostController) {

    val tab1Navigator:NavHostController = rememberNavController()
    val tab2Navigator:NavHostController = rememberNavController()
    val tab3Navigator:NavHostController = rememberNavController()
    val tab4Navigator:NavHostController = rememberNavController()
    val tab5Navigator:NavHostController = rememberNavController()

    // Create a list of BottomNavItem
    val items = listOf(
        BottomNavItem(0,"Tab1", Icons.Filled.AccountCircle, "tab1"),
        BottomNavItem(1,"Community", Icons.Filled.Person, "search"),
        BottomNavItem(2,"Home", Icons.Filled.Home, "cart"),
        BottomNavItem(3,"Search", Icons.Filled.Search, "search"),
        BottomNavItem(4,"More", Icons.Filled.Menu, "More")
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
                        0 -> Tab1Screen(tab1Navigator)
                        1 -> Tab2Screen(tab2Navigator)
                        2 -> Tab3Screen(tab3Navigator)
                        3 -> Tab4Screen(tab4Navigator)
                        4 -> Tab5Screen(tab5Navigator)
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
    TabsScreen(navController = rememberNavController())
}