package com.indusjs.platform

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "my_app_settings")

class AndroidSettingsStorage(private val context: Context):ISettingsStorage {
    override suspend fun saveSetting(key: String, value: String) {
        val settingsKey = stringPreferencesKey(key)
        context.settingsDataStore.edit { preferences ->
            preferences[settingsKey] = value
        }
    }

    override suspend fun loadSetting(key: String): String? {
        val settingsKey = stringPreferencesKey(key)
        val preferences = context.settingsDataStore.data.map { it[settingsKey] }.first()
        return preferences
    }
}