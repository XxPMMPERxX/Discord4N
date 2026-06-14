package jp.asteria.discord4n.command

import cn.nukkit.command.CommandSender
import cn.nukkit.command.PluginCommand
import jp.asteria.discord4n.Discord4N

abstract class BaseCommand(
    name: String
) : PluginCommand<Discord4N>(name, Discord4N.instance) {
    override fun execute(sender: CommandSender, commandLabel: String?, args: Array<String?>?): Boolean {
        if (!this.testPermission(sender)) return false
        return exec(sender, args)
    }

    abstract fun exec(sender: CommandSender, args: Array<String?>?): Boolean
}