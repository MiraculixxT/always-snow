package de.miraculixx.alwayssnow

import de.miraculixx.alwayssnow.config.AlwaysSnowAutoConfig
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val LOGGER: Logger = LogManager.getLogger("AlwaysSnow")

class AlwaysSnow : ClientModInitializer {
    override fun onInitializeClient() {
        AutoConfig.register(AlwaysSnowAutoConfig::class.java) { definition: Config?, configClass: Class<AlwaysSnowAutoConfig> ->
            GsonConfigSerializer(definition, configClass)
        }

        LOGGER.info("AlwaysSnow loaded!")

        // Save config on game exit
        ClientLifecycleEvents.CLIENT_STOPPING.register {

        }
    }

    companion object {
        fun getConfig(): AlwaysSnowAutoConfig = AutoConfig.getConfigHolder(AlwaysSnowAutoConfig::class.java).config
    }
}