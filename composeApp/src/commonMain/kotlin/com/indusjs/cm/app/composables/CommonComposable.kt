package com.indusjs.cm.app.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun showAlertDialog(openDialog: MutableState<Boolean>,
                    title: String,
                    message: String) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = { openDialog.value = false }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = { openDialog.value = false }) {
                Text("Cancel")
            }
        }
    )
}