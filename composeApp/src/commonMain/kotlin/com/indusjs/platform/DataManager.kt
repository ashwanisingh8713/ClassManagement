package com.indusjs.platform

import SignInResponse
import UserProfile
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

    // Save and retrieve user type
    suspend fun saveUserType(userType: String) {
        dataStorage.saveData("user_type", userType)
    }
    suspend fun getUserType(): String? {
        return dataStorage.loadData("user_type")
    }

    // Save and retrieve user token
    suspend fun saveUserToken(token: String) {
        dataStorage.saveData("user_token", token)
    }
    suspend fun getUserToken(): String? {
        return dataStorage.loadData("user_token")
    }

    // Save and retrieve Client profile data
    suspend fun saveUserProfile(userData: String) {
        dataStorage.saveData("profile_data", userData)
    }
    suspend fun getUserProfile(): UserProfile? {
        val profileData = dataStorage.loadData("profile_data")
        if (profileData.isNullOrEmpty()) {
            return null
        }
        return Json.decodeFromString<UserProfile>(profileData)
    }

    // Save and retrieve app theme
    suspend fun saveAppTheme(theme: String) {
        settingsStorage.saveSetting("app_theme", theme)
    }
    suspend fun getAppTheme(): String? {
        return settingsStorage.loadSetting("app_theme")
    }

    // Save and retrieve app language
    suspend fun clearUserToken() {
        dataStorage.clearData("user_token")
    }
}