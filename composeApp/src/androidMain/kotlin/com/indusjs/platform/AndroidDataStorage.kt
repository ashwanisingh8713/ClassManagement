package com.indusjs.platform

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_app_data")

class AndroidDataStorage(private val context: Context) : IDataStorage {
    override suspend fun saveData(key: String, data: String) {
        val dataKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[dataKey] = data
        }
    }

    override suspend fun loadData(key: String): String? {
        val dataKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[dataKey]
    }

    override suspend fun clearData(key: String) {
        val dataKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(dataKey)
        }
    }
}
