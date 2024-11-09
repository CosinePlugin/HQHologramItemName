package kr.cosine.hologramitemname.command

import kr.cosine.hologramitemname.config.SettingConfig
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender

@Command(label = "홀로그램아이템이름관리", isOp = true)
class HologramItemNameAdminCommand(
    private val settingConfig: SettingConfig
) {
    @CommandExecutor("리로드", "config.yml을 리로드합니다.")
    fun reload(sender: CommandSender) {
        settingConfig.reload()
        sender.sendMessage("§aconfig.yml을 리로드하였습니다.")
    }
}