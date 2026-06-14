package jp.asteria.discord4n

import cn.nukkit.plugin.PluginBase
import jp.asteria.discord4n.core.Discord
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

class Discord4N : PluginBase() {
    companion object {
        internal lateinit var instance: Discord4N
            private set
    }

    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        try {
            Discord.jda = JDABuilder
                .createDefault(config.getString("bot-token", ""))
                .enableIntents(GatewayIntent.entries)
                .addEventListeners(object : ListenerAdapter() {
                    override fun onReady(event: ReadyEvent) {
                        Discord.bot = event.jda.getUserById(config.getString("bot-id", "")) ?: throw Exception()
                        Discord.guild = event.jda.getGuildById(config.getString("guild-id")) ?: throw Exception()
                    }
                })
                .build()
        } catch (_: Exception) {
            logger.error("トークンが違うか、ボットのIDが違います。")
            server.pluginManager.disablePlugin(this)

            return
        }
    }
}