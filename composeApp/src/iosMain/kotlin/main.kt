//import androidx.compose.ui.window.ComposeUIViewController
import com.indusjs.cm.app.App
import platform.UIKit.UIViewController
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
