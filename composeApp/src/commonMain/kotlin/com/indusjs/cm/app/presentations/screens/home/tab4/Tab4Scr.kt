package com.indusjs.cm.app.presentations.screens.home.tab4

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
import androidx.navigation.NavHostController

val TAB4_ROOT_PAGE: String = "TAB4_ROOT_PAGE"

@Composable
fun Tab4Screen(tab5Navigator: NavHostController, topbarVisibility: MutableState<Boolean>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Tab 4", style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center))
    }
}