package com.indusjs.platform

interface IDataStorage {
    suspend fun saveData(key: String, data: String)
    suspend fun loadData(key: String): String?
    suspend fun clearData(key: String)
}