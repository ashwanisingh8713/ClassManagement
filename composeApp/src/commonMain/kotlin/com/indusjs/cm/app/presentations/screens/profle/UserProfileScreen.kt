package com.indusjs.cm.app.presentations.screens.profle

import SignInResponse
import UserProfile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import coil3.compose.AsyncImage
import com.indusjs.cm.app.presentations.theme.topbarColor
import com.indusjs.cm.app.presentations.utils.NavigationRoute
import com.indusjs.platform.DataManager
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin

// Define constants for common padding and sizes
private val ScreenPadding = 16.dp
private val ItemVerticalPadding = 8.dp
private val IconSize = 24.dp
private val ProfileImageSize = 100.dp
private val TopBarHeight = 56.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    navController: NavHostController
) {

    var name by remember { mutableStateOf("") } // Changed to a more realistic placeholder
    var phoneNumber by remember { mutableStateOf("") } // Changed to a more realistic placeholder
    var email by remember { mutableStateOf("") } // Changed to a more realistic placeholder
    var location by remember { mutableStateOf("") } // Changed to a more realistic placeholder
    var gateNo by remember { mutableStateOf("") } // Changed to a more realistic placeholder

    val dataManager = getKoin().get<DataManager>()
    val scope = rememberCoroutineScope()

    scope.launch {
        val userData:UserProfile? = dataManager.getUserProfile()
        userData?.let {
            it.let {
                name = it.name // Assuming clientName is the user's name
                phoneNumber = it.contactEmail // Assuming contactNumber is the user's phone number
                email = it.email
                location = it.address ?: "N/A"
                gateNo = it.clientId // Assuming clientId is the gate number or similar identifier
            }
        }
    }

    // Define the onBackClick function to handle back navigation
    val onBackClick: () -> Unit = {
        println("Ashwani UserProfileScreen Back Button is pressed") // Keep the original print for now
        navController.navigateUp()
    }

    // Define the onLogoutClick function to handle logout
    val onLogoutClick: () -> Unit = {
        println("Ashwani UserProfileScreen Logout Button is pressed") // Keep the original print for now
        // Clear user data
        scope.launch {
            dataManager.saveUserLoggedIn(false)
            dataManager.clearUserToken();
        }
        // Navigate to the SignInScreen,
        // clearing the back stack to avoid going back to the profile screen
        navController.navigate(NavigationRoute.SignInScreen.route) {
            popUpTo(NavigationRoute.TabsMainScreen.route) { inclusive = true }
        }

    }

    // Use Scaffold for the basic screen structure (TopBar, Content, etc.)
    Scaffold(
        modifier = Modifier.height(1.dp),
        // Removed the incorrect height(1.dp) modifier from Scaffold
        topBar = {
            UserProfileTopBar(onBackClick = onBackClick, onLogoutClick = onLogoutClick)
        }
    ) { innerPadding ->
        // Apply the padding provided by Scaffold to the content
        UserProfileContent(
            modifier = Modifier.padding(innerPadding),
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            location = location,
            gateNo = gateNo,
            // Assuming a shared profile image logic will be passed or handled internally
            profileImageUrl = "https://th-i.thgim.com/public/incoming/ysmy06/article69580810.ece/alternates/LANDSCAPE_1200/20250512016L.jpg" // Passed the image URL to content
        )
    }
}

@Composable
fun UserProfileTopBar(onBackClick: () -> Unit, onLogoutClick:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(TopBarHeight)
            .background(brush = Brush.verticalGradient(topbarColor)), // Apply horizontal padding here
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceEvenly // Use SpaceBetween if you had content on both ends
    ) {
        // Back button and title
        Row(modifier = Modifier.padding(start = ScreenPadding), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(IconSize)
                    // Apply clickable and padding directly
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(ScreenPadding)) // Space between icon and text
            Text(
                text = "User Profile",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), // Use titleMedium for title
                color = MaterialTheme.colorScheme.onSurface // Use appropriate color from theme
            )
        }
        // Add more elements here if needed on the right side of the top bar
        Spacer(modifier = Modifier.weight(1f).height(TopBarHeight))
        // Logout button
        Box(modifier = Modifier.height(TopBarHeight/2).width(TopBarHeight+TopBarHeight/2).clickable { onLogoutClick() }
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
        Text(
            text = "Logout",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), // Use titleMedium for title
            color = MaterialTheme.colorScheme.onSurface // Use appropriate color from theme
        )}
        Spacer(modifier = Modifier.width(ScreenPadding))
    }
}


@Composable
private fun UserProfileContent(
    modifier: Modifier = Modifier, // Accept modifier from the caller (Scaffold)
    name: String,
    phoneNumber: String,
    email: String,
    location: String,
    gateNo: String,
    profileImageUrl: String? = null // Made profile image URL optional
) {
    Column(
        modifier = modifier // Apply the modifier passed from Scaffold
            .fillMaxSize()
            .padding(ScreenPadding), // Apply screen padding to the column
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(26.dp)) // Adjust spacing as needed

        // Shared Profile Image
        // Use a Box to center the loading/error indicators over the image area
        Box(
            modifier = Modifier
                .size(ProfileImageSize) // Apply size to the Box
                .clip(CircleShape)
                .background(Color.Gray), // Placeholder background while loading
            contentAlignment = Alignment.Center // Center content within the Box
        ) {
            if (!profileImageUrl.isNullOrEmpty()) {
                NetworkProfileImageWithState(imageUrl = profileImageUrl)
            } else {
                // Fallback or default image when URL is null or empty
                Icon(
                    imageVector = Icons.Default.Person, // Example fallback icon
                    contentDescription = "Default Profile Picture",
                    modifier = Modifier.size(ProfileImageSize * 0.6f), // Adjust size of fallback icon
                    tint = Color.White // Adjust tint
                )
            }
        }


        Spacer(modifier = Modifier.height(ScreenPadding)) // Space after image

        // User Details - Using a common composable for each item
        ProfileDetailItem(icon = Icons.Filled.Person, label = "Name", value = name)
        ProfileDetailItem(icon = Icons.Filled.Phone, label = "Phone Number", value = phoneNumber)
        ProfileDetailItem(icon = Icons.Filled.Email, label = "Email", value = email)
        ProfileDetailItem(icon = Icons.Filled.Home, label = "Society Name", value = location)
        ProfileDetailItem(icon = Icons.Filled.LocationOn, label = "Gate No.", value = gateNo)
    }
}

@Composable
fun ProfileDetailItem(
    icon: ImageVector? = null,
    drawableResource: DrawableResource? = null,
    label: String,
    value: String
) {
    Column(modifier = Modifier.fillMaxWidth()) { // Use a Column to structure label, value, and divider
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = ItemVerticalPadding), // Apply vertical padding here
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display icon or drawable
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier.size(IconSize),
                    tint = MaterialTheme.colorScheme.primary // Use theme color for icons
                )
            }
            drawableResource?.let {
                Image(
                    painter = painterResource(drawableResource),
                    contentDescription = label, // Use label for content description
                    modifier = Modifier.size(IconSize),
                    contentScale = ContentScale.Inside // Or adjust as needed
                )
            }

            Spacer(modifier = Modifier.width(ScreenPadding)) // Space between icon/drawable and text

            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), // Use bodySmall for label
                    color = MaterialTheme.colorScheme.onSurfaceVariant // Use appropriate color
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium, // Use bodyMedium for value
                    color = MaterialTheme.colorScheme.onSurface // Use appropriate color
                )
            }
        }
        // Divider below the detail item
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp, // Explicitly set thickness
            color = MaterialTheme.colorScheme.outlineVariant // Use theme color for divider
        )
    }
}

// Example usage where the image is loaded from resources (commonMain) - Kept as is
@Composable
fun ResourceProfileImage(resourceId: DrawableResource, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(resourceId),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(ProfileImageSize) // Use constant
            .clip(CircleShape)
    )
}

@Composable
fun NetworkProfileImageWithState(imageUrl: String, modifier: Modifier = Modifier) {
    // Coil's AsyncImage handles loading and state internally.
    // You can use onState to react to state changes, but often you don't need to
    // manually show a progress indicator or error text *around* it like this
    // unless you need specific UI for those states.

    // Let's simplify this and use the AsyncImage with placeholders/errors handled by Coil directly.
    // If you need custom loading/error UI layered *over* the image area,
    // the approach in UserProfileContent's Box is more appropriate.

    AsyncImage(
        model = imageUrl,
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(ProfileImageSize) // Use constant
            .clip(CircleShape),
        // You can add placeholder and error drawables directly here if available
        // placeholder = painterResource(R.drawable.placeholder_image),
        // error = painterResource(R.drawable.error_image),
        // onState is useful if you need to observe the state for logging or complex UI changes
        // onState = { state -> /* React to state changes */ }
    )

    // If you still need explicit loading/error indicators *overlayed*,
    // manage their visibility based on the painter state *outside* this composable
    // or within a Box like shown in UserProfileContent.

    // Removing the manual state handling and indicator display from here
    // as AsyncImage is designed to handle basic display itself.
}