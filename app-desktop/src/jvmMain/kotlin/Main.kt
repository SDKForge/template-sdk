import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.singleWindowApplication
import dev.sdkforge.template.app.App

fun main() = singleWindowApplication(
    title = "app-desktop",
) {
    App(
        modifier = Modifier
            .fillMaxSize(),
    )
}
