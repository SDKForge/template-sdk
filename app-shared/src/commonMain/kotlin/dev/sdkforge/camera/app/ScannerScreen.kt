package dev.sdkforge.camera.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.sdkforge.camera.ui.CameraController
import dev.sdkforge.camera.ui.CameraView
import dev.sdkforge.camera.ui.rememberCameraController

@Composable
fun ScannerScreen(
    modifier: Modifier = Modifier,
) {
    val cameraController = rememberCameraController()

    ApplicationTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            CameraView(
                cameraController = cameraController,
                modifier = Modifier.fillMaxSize(),
            )
            ButtonsOverlay(
                controller = cameraController,
                modifier = modifier.padding(top = innerPadding.calculateTopPadding()),
            )
        }
    }
}

@Composable
fun ButtonsOverlay(
    controller: CameraController,
    modifier: Modifier = Modifier,
) {
    var isFlashOn by remember { mutableStateOf(false) }
    val targetIcon = if (isFlashOn) Icons.Default.FlashOff else Icons.Default.FlashOn
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(8.dp),
    ) {
        Icon(
            imageVector = targetIcon,
            contentDescription = "Flash toggle",
            modifier = Modifier.clickable {
                controller.toggleFlash()
                isFlashOn = controller.isFlashIsOn()
            },
        )
        Icon(
            imageVector = Icons.Default.FlipCameraAndroid,
            contentDescription = "Flip between front and back active cameras",
            modifier = Modifier.clickable {
                controller.toggleActiveCamera()
            },
        )
    }
}
