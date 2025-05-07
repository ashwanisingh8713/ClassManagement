package com.indusjs.platform

sealed class Platform {
    data object Android : Platform()
    data object Ios : Platform()
    data object Web : Platform()
}

expect val platform: Platform