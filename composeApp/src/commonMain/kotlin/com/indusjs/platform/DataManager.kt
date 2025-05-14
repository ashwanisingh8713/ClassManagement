package com.indusjs.platform

class DataManager(private val dataStorage: IDataStorage, private val settingsStorage: ISettingsStorage) {

    suspend fun saveUserLoggedIn() {
        return dataStorage.saveData("isUserLoggedIn", "true")
    }

    suspend fun isUserLoggedIn(): Boolean {
        return dataStorage.loadData("isUserLoggedIn") != null
    }

    suspend fun saveUserToken(token: String) {
        dataStorage.saveData("user_token", token)
    }

    suspend fun getUserToken(): String? {
        return dataStorage.loadData("user_token")
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