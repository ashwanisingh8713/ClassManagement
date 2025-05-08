package com.indusjs.cm.app.presentations.screens.home.tab3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val TAB3_ROOT_PAGE: String = "TAB3_ROOT_PAGE"

@Composable
fun Tab3Screen(tab5Navigator: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Tab 3", style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center))
    }
}