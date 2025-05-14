package com.indusjs.platform

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import platform.Foundation.NSUserDefaults

class IOSSettingsStorage: ISettingsStorage {
    private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun saveSetting(key: String, value: String): Unit = withContext(Dispatchers.IO) {
        userDefaults.setObject(value, forKey = key)
        userDefaults.synchronize()
    }

    override suspend fun loadSetting(key: String): String? = withContext(Dispatchers.IO) {
        userDefaults.stringForKey(key)
    }

}