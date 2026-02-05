import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import dev.sdkforge.template.app.App
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        ComposeViewport(
            viewportContainerId = "app-web",
        ) {
            App(
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}
