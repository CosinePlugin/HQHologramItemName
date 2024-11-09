package kr.cosine.hologramitemname.config

import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration

@Bean
class SettingConfig(
    private val config: HQYamlConfiguration
) {
    val isEnabled get() = config.getBoolean("enabled")

    private var hologramFormat = ""

    fun load() {
        hologramFormat = config.getString("hologram-format").colorize()
    }

    fun reload() {
        config.reload()
        load()
    }

    fun getHologramFormat(replace: (String) -> String): String {
        return replace(hologramFormat)
    }
}