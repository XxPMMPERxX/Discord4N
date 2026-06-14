package jp.asteria.discord4n.core

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.User

object Discord {
    internal lateinit var jda: JDA
    internal lateinit var bot: User
    internal lateinit var guild: Guild
}