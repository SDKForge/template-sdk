package dev.sdkforge.camera.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.sdkforge.camera.ui.CameraView
import dev.sdkforge.camera.ui.rememberCameraController

@Composable
fun ScannerScreen(
    modifier: Modifier = Modifier
) {
    val cameraController = rememberCameraController()

    ApplicationTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            CameraView(
                cameraController = cameraController,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
