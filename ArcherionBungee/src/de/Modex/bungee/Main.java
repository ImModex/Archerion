package de.Modex.bungee;

import de.Modex.bungee.commands.lobby;
import de.Modex.bungee.commands.reply;
import de.Modex.bungee.commands.whisper;
import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class Main extends Plugin {

    @Override
    public void onEnable() {

        getProxy().getPluginManager().registerCommand(this, new lobby());
        getProxy().getPluginManager().registerCommand(this, new whisper());
        getProxy().getPluginManager().registerCommand(this, new reply());
        startTablistTimer();

        System.out.println(Data.prefix + "Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println(Data.prefix + "Plugin has been disabled!");
    }

    private void startTablistTimer() {
        getProxy().getScheduler().schedule(this, () -> {

            for (ProxiedPlayer player : getProxy().getPlayers()) {
                player.setTabHeader(new TextComponent(Data.header.replaceAll("%server%", player.getServer().getInfo().getName())), new TextComponent(Data.footer.replaceAll("%players%", getProxy().getPlayers().size() + "").replaceAll("%ping%", player.getPing() + "")));
            }

        }, 0, 1, TimeUnit.SECONDS);
    }
}
