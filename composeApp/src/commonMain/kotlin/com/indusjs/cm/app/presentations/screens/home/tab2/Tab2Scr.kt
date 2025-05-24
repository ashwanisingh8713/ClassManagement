package com.indusjs.cm.app.presentations.screens.home.tab2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

val TAB2_ROOT_PAGE: String = "TAB2_ROOT_PAGE"
val TAB2_EMERGENCY_CONTACTS_PAGE: String = "emergency_contacts"
val TAB2_MANAGMENT_COMMITTEE_PAGE: String = "managment_committee"
val TAB2_NEIGHBOURS_PAGE: String = "neighbours"
val TAB2_SERVICE_VENDORS_PAGE: String = "service_vendors"

@Composable
fun Tab2Screen(tab5Navigator: NavHostController, topbarVisibility: MutableState<Boolean>) {
    NavHost(
        navController = tab5Navigator,
        startDestination = TAB2_ROOT_PAGE,
    ) {
        composable(route = TAB2_ROOT_PAGE) {
            CommunityScreen(tab5Navigator, topbarVisibility) {
                tab5Navigator.popBackStack()
            }
        }
        composable(route = TAB2_EMERGENCY_CONTACTS_PAGE) {
            EmergencyContactsScreen(tab5Navigator, topbarVisibility) {
                tab5Navigator.popBackStack()
            }
        }
        composable(route = TAB2_MANAGMENT_COMMITTEE_PAGE) {
            ManagmentCommitteeScreen(tab5Navigator, topbarVisibility) {
                tab5Navigator.popBackStack()
            }
        }
        composable(route = TAB2_NEIGHBOURS_PAGE) {
            NeighboursScreen(tab5Navigator, topbarVisibility) {
                tab5Navigator.popBackStack()
            }
        }
        composable(route = TAB2_SERVICE_VENDORS_PAGE) {
            ServiceVendorsScreen(tab5Navigator, topbarVisibility) {
                tab5Navigator.popBackStack()
            }
        }
    }
}