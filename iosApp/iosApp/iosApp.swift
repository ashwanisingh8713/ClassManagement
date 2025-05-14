import UIKit
import ComposeApp


@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    

    lazy var dataManager: DataManager = {
        let dataStorage = IOSDataStorage()
        let settingsStorage = IOSSettingsStorage()
        return DataManager(dataStorage: dataStorage, settingsStorage: settingsStorage)
    }()

    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        
        // Initialize Koin
        AppKoinKt.doInitKoin(module: MainKt.iosAppModule)
        
        window = UIWindow(frame: UIScreen.main.bounds)
        if let window = window {
            window.rootViewController = MainKt.MainViewController()
            window.makeKeyAndVisible()
        }
        return true
    }
    
    
    
}

class MainViewController: UIViewController {
    lazy var dataManager: DataManager = {
        let dataStorage = IOSDataStorage()
        let settingsStorage = IOSSettingsStorage()
        return DataManager(dataStorage: dataStorage, settingsStorage: settingsStorage)
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Use dataManager here
    }
}
