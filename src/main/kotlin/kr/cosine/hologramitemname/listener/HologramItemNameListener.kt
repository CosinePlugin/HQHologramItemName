package kr.cosine.hologramitemname.listener

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.cosine.hologramitemname.config.SettingConfig
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.coroutine.extension.BukkitMain
import kr.hqservice.framework.bukkit.core.listener.HandleOrder
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import kr.hqservice.framework.nms.extension.getDisplayName
import org.bukkit.entity.Item
import org.bukkit.event.entity.ItemMergeEvent
import org.bukkit.event.entity.ItemSpawnEvent

@Listener
class HologramItemNameListener(
    private val plugin: HQBukkitPlugin,
    private val settingConfig: SettingConfig
) {
    @Subscribe(HandleOrder.LAST)
    fun onItemSpawn(event: ItemSpawnEvent) {
        if (event.isCancelled) return
        event.entity.applyHologramItemName()
    }

    @Subscribe(HandleOrder.LAST)
    fun onItemMerge(event: ItemMergeEvent) {
        if (event.isCancelled) return
        plugin.launch(Dispatchers.BukkitMain) {
            event.target.applyHologramItemName()
        }
    }

    private fun Item.applyHologramItemName(amount: Int? = null) {
        if (!settingConfig.isEnabled) return
        val hologramFormat = settingConfig.getHologramFormat {
            it.replace("%item%", itemStack.getDisplayName())
                .replace("%amount%", "${amount ?: itemStack.amount}")
        }
        customName = hologramFormat
        isCustomNameVisible = true
    }
}