import androidx.compose.ui.window.ComposeUIViewController
import com.indusjs.cm.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
