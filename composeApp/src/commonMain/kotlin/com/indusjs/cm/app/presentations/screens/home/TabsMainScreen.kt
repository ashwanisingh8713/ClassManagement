package com.indusjs.cm.app.presentations.screens.home

import SignInResponse
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.indusjs.cm.app.presentations.utils.NavigationRoute
import com.indusjs.platform.DataManager
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin




// Define data class for Bottom Navigation items
private data class BottomNavItem(val tabId:Int, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)

@Composable
fun TabsScreen(navController: NavHostController) {

    val scope = rememberCoroutineScope()

    val topbarVisibility = remember { mutableStateOf(true) }

    val tab1Navigator:NavHostController = rememberNavController()
    val tab2Navigator:NavHostController = rememberNavController()
    val tab3Navigator:NavHostController = rememberNavController()
    val tab4Navigator:NavHostController = rememberNavController()
    val tab5Navigator:NavHostController = rememberNavController()

    val onUserProfileClick:() -> Unit = {
        navController.navigate(NavigationRoute.UserProfileScreen.route)
    }

    val dataManager = getKoin().get<DataManager>()
    scope.launch {
        val userDataJson = dataManager.getUserData()
        if (userDataJson != null) {
            val user = Json.decodeFromString<SignInResponse>(userDataJson)
            println("User Data: $userDataJson $user")
        } else {
            println("No user data found.")
        }
    }


    // Create a list of BottomNavItem
    val items = listOf(
        BottomNavItem(0,"Tab1", Icons.Filled.AccountCircle, "tab1"),
        BottomNavItem(1,"Community", Icons.Filled.Person, "community"),
        BottomNavItem(2,"Home", Icons.Filled.Home, "cart"),
        BottomNavItem(3,"Search", Icons.Filled.Search, "search"),
        BottomNavItem(4,"More", Icons.Filled.Menu, "More")
    )

    // Use remember to survive configuration changes
    val selectedItem = remember { mutableStateOf(items[0]) }
        Scaffold(modifier = Modifier.height(1.dp), // here I need to check
            topBar = {
                TopAppBarSection(onUserProfileClick)
                    /*if(topbarVisibility.value) {
                        TopAppBarSection()
                    }*/
                 },
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
            content = { paddingValues ->
                // Content of each screen based on the selected item
                Surface(
                    modifier = Modifier
                        .padding(paddingValues) // Apply padding from Scaffold
                        .fillMaxSize(),
                    color = Color.White
                ) {
                    when (selectedItem.value.tabId) {
                        0 -> Tab1Screen(tab1Navigator, topbarVisibility)
                        1 -> Tab2Screen(tab2Navigator, topbarVisibility)
                        2 -> Tab3Screen(tab3Navigator, topbarVisibility)
                        3 -> Tab4Screen(tab4Navigator, topbarVisibility)
                        4 -> Tab5Screen(tab5Navigator, topbarVisibility)
                    }
                }
            }
        )

}

@Composable
fun TopAppBarSection(onUserProfileClick: () -> Unit) {
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
                    .clickable { onUserProfileClick() } // Navigate to user profile screen
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