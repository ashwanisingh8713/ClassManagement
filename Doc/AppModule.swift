// iosApp/iosApp/di/AppModule.swift
import Foundation
import ComposeApp
import KoinSwift

func startKoin() {
    let koinApplication = KoinApplication.init(userDefaults: .standard) {
        modules([appModule])
    }
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin? = nil
var koin: Koin_coreKoin {
    return _koin!
}

let appModule = module {
    single { IOSDataStorage() }
    single { IOSSettingsStorage() }
    single { DataManager(dataStorage: get(), settingsStorage: get()) }
}