package com.indusjs.cm.app.presentations.screens.profle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
public fun UserProfileScreen( navController: NavHostController,
    // Composable lambda for the shared image
) {
    val profileImage: @Composable () -> Unit
    var name: String = "Name"
    var phoneNumber: String = "Phone Number"
    var email: String = "Email"
    var societyName: String = "Society Name"
    var gateNo: String = "Gate No."
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Shared Profile Image
        NetworkProfileImageWithState(imageUrl = "https://th-i.thgim.com/public/incoming/ysmy06/article69580810.ece/alternates/LANDSCAPE_1200/20250512016L.jpg",
            modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // User Details
        ProfileDetailItem(icon = Icons.Filled.Person, label = "Name", value = name)
        Spacer(modifier = Modifier.height(8.dp))
        ProfileDetailItem(icon = Icons.Filled.Phone, label = "Phone Number", value = phoneNumber)
        Spacer(modifier = Modifier.height(8.dp))
        ProfileDetailItem(icon = Icons.Filled.Email, label = "Email", value = email)
        Spacer(modifier = Modifier.height(16.dp))
        ProfileDetailItem(icon = Icons.Filled.Home, label = "Society Name", value = societyName)
        Spacer(modifier = Modifier.height(8.dp))
        ProfileDetailItem(icon = Icons.Filled.LocationOn, label = "Gate No.", value = gateNo)
    }
}

@Composable
fun ProfileDetailItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
            Text(text = value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Example usage where the image is loaded from resources (commonMain)
@Composable
fun ResourceProfileImage(resourceId: DrawableResource, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(resourceId),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
fun NetworkProfileImageWithState(imageUrl: String, modifier: Modifier = Modifier) {
    val painterState = remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Loading(null)) }

    AsyncImage(
        model = imageUrl,
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .then(modifier),
        onState = { state ->
            painterState.value = state
        }
    )

    when (val state = painterState.value) {
        is AsyncImagePainter.State.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        }
        is AsyncImagePainter.State.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Failed to load image")
            }
        }
        is AsyncImagePainter.State.Success -> {
            // The AsyncImage composable itself handles the successful display
        }
        is AsyncImagePainter.State.Empty -> {
            // Optional: Handle the empty state if the model is null or empty
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("No image to display")
            }
        }
    }
}
