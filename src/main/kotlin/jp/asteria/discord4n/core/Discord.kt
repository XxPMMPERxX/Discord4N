package jp.asteria.discord4n.core

import jp.asteria.discord4n.Discord4N
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.User

object Discord {
    internal lateinit var jda: JDA
    internal lateinit var bot: User
    internal lateinit var guild: Guild

    fun load() {
        val botName = bot.name
        val botId = bot.id

        val guildName = guild.name
        val guildId = guild.id
        val guildMemberCount = guild.members.size
        val guildRoleCount = guild.roles.size

        Discord4N.instance.logger.info(
            listOf(
                "Bot:",
                " Name: $botName",
                " ID: $botId",
                "Guild:",
                " Name: $guildName",
                " ID: $guildId",
                " MemberCount: $guildMemberCount",
                " RoleCount: $guildRoleCount"
            ).joinToString("\n")
        )
    }
}