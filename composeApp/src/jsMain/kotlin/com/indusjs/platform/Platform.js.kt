package com.indusjs.platform

// Web
actual val platform: Platform = Platform.Web

actual fun showToast(message: String) {
    // Web-specific implementation to show a toast message
    // This is a placeholder as web does not have a direct equivalent to Android's Toast
    // You would typically use JavaScript alert or a custom notification library
    println("Toast message: $message")
}

