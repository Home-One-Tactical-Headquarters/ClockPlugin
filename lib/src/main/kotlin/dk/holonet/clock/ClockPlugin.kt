package dk.holonet.clock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dk.holonet.core.HoloNetModule
import dk.holonet.core.HoloNetPlugin
import dk.holonet.core.ModuleConfiguration
import dk.holonet.core.asString
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.pf4j.Extension
import org.pf4j.PluginWrapper
import java.text.SimpleDateFormat
import java.util.Date

class ClockPlugin(wrapper: PluginWrapper) : HoloNetPlugin(wrapper) {
    override fun start() {
        super.start()
//        println("ClockPlugin.start()")
    }

    override fun stop() {
//        println("ClockPlugin.stop()")
    }

    @Extension
    class ClockModule() : HoloNetModule() {
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

            Box(modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Text(
                    time,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        override fun configure(configuration: ModuleConfiguration?) {
            super.configure(configuration)

            configuration?.config?.let { props ->
//                props["key1"]?.let { println("Reading key1: ${it.asString()}") }
//                props["key2"]?.let { println("Reading key2: ${it.asString()}") }
            }
        }

    }
}
