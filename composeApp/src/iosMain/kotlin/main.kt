import androidx.compose.ui.window.ComposeUIViewController
import com.indusjs.cm.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
