package com.indusjs.cm.app.presentations.screens.home.tab1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Tab1_TenantScreen(onBackButtonClick:() -> Unit) {
    Column {
        Text(
            text = "Tab 1 Tenant Screen",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {onBackButtonClick()},
            modifier = Modifier.padding(16.dp).width(200.dp).height(50.dp)
        ) {
            Text("Tab 1 Root Button")
        }
    }

}