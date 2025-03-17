package dk.holonet.clock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import dk.holonet.core.HoloNetModule
import dk.holonet.core.ModuleConfig
import dk.holonet.core.Position
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper
import java.text.SimpleDateFormat
import java.util.Date

class ClockPlugin(wrapper: PluginWrapper) : Plugin(wrapper) {
    override fun start() {
        println("ClockPlugin.start()")
    }

    override fun stop() {
        println("ClockPlugin.stop()")
    }

    @Extension
    class ClockModule() : HoloNetModule {
        override val config: ModuleConfig = ModuleConfig(Position.CENTER)

        @Composable
        override fun render() {
            var time by remember { mutableStateOf("") }
            val sdf = remember { SimpleDateFormat("HH:mm:ss", java.util.Locale.ROOT) }

            LaunchedEffect(Unit) {
                while(isActive) {
                    time = sdf.format(Date())
                    delay(1000)
                }
            }

            Box(modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center) {
                Text(time, fontSize = 40.sp)
            }
        }

    }
}
