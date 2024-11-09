package kr.cosine.hologramitemname.listener

import kr.cosine.hologramitemname.config.SettingConfig
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import kr.hqservice.framework.nms.extension.getDisplayName
import org.bukkit.entity.Item
import org.bukkit.event.entity.EntitySpawnEvent

@Listener
class HologramItemNameListener(
    private val settingConfig: SettingConfig
) {
    @Subscribe
    fun onItemSpawn(event: EntitySpawnEvent) {
        if (!settingConfig.isEnabled) return
        val item = event.entity as? Item ?: return
        val itemStack = item.itemStack
        val hologramFormat = settingConfig.getHologramFormat {
            it.replace("%item%", itemStack.getDisplayName())
                .replace("%amount%", "${itemStack.amount}")
        }
        item.isCustomNameVisible = true
        item.customName = hologramFormat
    }
}