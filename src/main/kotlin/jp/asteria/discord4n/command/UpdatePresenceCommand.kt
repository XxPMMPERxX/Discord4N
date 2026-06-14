package jp.asteria.discord4n.command

import cn.nukkit.command.CommandSender
import jp.asteria.discord4n.core.Discord
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity

class UpdatePresenceCommand : BaseCommand(
    name = "d4n-update-presence",
    description = "Update a presence of bot"
) {
    init {
        permission = "asteria.discord4n.update_presence"
        usageMessage = "/d4n-update-presence <status: text> [what: text] [type: text]"
    }

    override fun exec(sender: CommandSender, args: Array<String?>?): Boolean {
        if (args.isNullOrEmpty()) return false
        if (args.size !in 1..3) return false

        val statusKey = args[0] ?: ""
        val status = OnlineStatus.fromKey(statusKey)
        if (status == OnlineStatus.UNKNOWN) return false

        val what = args[1] ?: ""
        val type = when (args[2] ?: "") {
            "0", "playing"   -> Activity.ActivityType.PLAYING
            "1", "streaming" -> Activity.ActivityType.STREAMING
            "2", "listening" -> Activity.ActivityType.LISTENING
            "3", "watching"  -> Activity.ActivityType.WATCHING
            "4", "custom"    -> Activity.ActivityType.CUSTOM_STATUS
            "5", "competing" -> Activity.ActivityType.COMPETING
            else             -> Activity.ActivityType.CUSTOM_STATUS
        }

        try {
            val activity = Activity.of(type, what)
            Discord.jda.presence.setPresence(status, activity)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return false
        }

        return true
    }
}