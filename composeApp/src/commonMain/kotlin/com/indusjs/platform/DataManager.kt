package com.indusjs.platform

import SignInResponse
import kotlinx.serialization.json.Json

class DataManager(private val dataStorage: IDataStorage, private val settingsStorage: ISettingsStorage) {

    suspend fun saveUserLoggedIn(boolean: Boolean) {
        return dataStorage.saveData("isUserLoggedIn", if(boolean) "true" else "false")
    }

    suspend fun isUserLoggedIn(): Boolean {
        val isLoggedIn = dataStorage.loadData("isUserLoggedIn")
        if (isLoggedIn == null) {
            return false
        } else if (isLoggedIn == "true") {
            return true
        }
        return false
    }

    suspend fun saveUserToken(token: String) {
        dataStorage.saveData("user_token", token)
    }

    suspend fun getUserToken(): String? {
        return dataStorage.loadData("user_token")
    }

    suspend fun saveUserData(userData: String) {
        dataStorage.saveData("user_data", userData)
    }
    suspend fun getUserData(): SignInResponse? {
        return Json.decodeFromString<SignInResponse>(dataStorage.loadData("user_data") ?: "")
    }

    suspend fun saveAppTheme(theme: String) {
        settingsStorage.saveSetting("app_theme", theme)
    }

    suspend fun getAppTheme(): String? {
        return settingsStorage.loadSetting("app_theme")
    }

    suspend fun clearUserToken() {
        dataStorage.clearData("user_token")
    }
}