package com.indusjs.platform

interface ISettingsStorage {
    suspend fun saveSetting(key: String, value: String)
    suspend fun loadSetting(key: String): String?
}