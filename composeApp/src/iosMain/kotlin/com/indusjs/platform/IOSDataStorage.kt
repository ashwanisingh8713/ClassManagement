package com.indusjs.platform

import platform.Foundation.NSUserDefaults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class IOSDataStorage:IDataStorage {

    private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun saveData(key: String, data: String): Unit = withContext(Dispatchers.IO) {
        userDefaults.setObject(data, forKey = key)
        userDefaults.synchronize()
    }

    override suspend fun loadData(key: String): String? = withContext(Dispatchers.IO) {
        userDefaults.stringForKey(key)
    }

    override suspend fun clearData(key: String): Unit = withContext(Dispatchers.IO) {
        userDefaults.removeObjectForKey(key)
        userDefaults.synchronize()
    }
}