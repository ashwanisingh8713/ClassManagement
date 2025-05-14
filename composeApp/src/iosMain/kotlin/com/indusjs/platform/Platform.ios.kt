package com.indusjs.platform

// iOS
actual val platform: Platform = Platform.Ios

actual fun showToast(message: String) {
    // iOS-specific implementation to show a toast message
    // This is a placeholder as iOS does not have a direct equivalent to Android's Toast
    // You would typically use SwiftUI or UIKit to show alerts or notifications
}