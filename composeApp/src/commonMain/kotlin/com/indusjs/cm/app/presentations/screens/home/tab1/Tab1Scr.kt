package com.indusjs.cm.app.presentations.screens.home.tab1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import classmanagement.composeapp.generated.resources.Res
import classmanagement.composeapp.generated.resources.ic_facility
import classmanagement.composeapp.generated.resources.ic_parking
import classmanagement.composeapp.generated.resources.ic_payments
import classmanagement.composeapp.generated.resources.ic_post
import classmanagement.composeapp.generated.resources.ic_tenant
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Tab1Screen() {
    SocietyManagementScreen()
}

@Composable
fun SocietyManagementScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        QuickActionsBar()
        TenantCard()
        Spacer(modifier = Modifier.weight(1f))
        // BottomNavigationBar (Not included as per request)
    }
}



@Composable
fun QuickActionsBar() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item { QuickActionItem(icon = Res.drawable.ic_post, label = "Post") } // Replace with actual icon resource
        item { QuickActionItem(icon =  Res.drawable.ic_payments, label = "Payments") } // Replace with actual icon resource
        item { QuickActionItem(icon =  Res.drawable.ic_facility, label = "Facility") } // Replace with actual icon resource
        item { QuickActionItem(icon =  Res.drawable.ic_tenant, label = "Tenant") } // Replace with actual icon resource
        item { QuickActionItem(icon =  Res.drawable.ic_parking, label = "Parking") } // Replace with actual icon resource
        // Add more quick actions as needed
    }
}

@Composable
fun QuickActionItem(
    icon: DrawableResource,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE0F7FA) // Light Teal background
            ),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = label,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun TenantCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE0F7FA) // Light Teal background for the card
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFC8E6C9)), // Light Green placeholder
                    contentAlignment = Alignment.Center
                ) {
                    // You might want to load the actual tenant image here
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "Tenant Avatar",
                        tint = Color(0xFF388E3C), // Dark Green icon color
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Ashwani Singh", style = MaterialTheme.typography.headlineSmall)
                    Text(text = "A-123", style = MaterialTheme.typography.bodyLarge)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Person, contentDescription = "Tenants", tint = Color(0xFF1976D2)) // Blue icon
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Tenants: 5", style = MaterialTheme.typography.bodyMedium)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Settings, contentDescription = "Vehicles", tint = Color(0xFF1976D2)) // Blue icon
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Vehicles: 5", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

