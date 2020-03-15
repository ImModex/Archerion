package de.Modex.bungee;

import de.Modex.bungee.commands.*;
import de.Modex.bungee.listener.LoginListener;
import de.Modex.bungee.listener.PostLoginListener;
import de.Modex.bungee.listener.ServerConnectListener;
import de.Modex.bungee.listener.ServerKickListener;
import de.Modex.bungee.utils.BanManager;
import de.Modex.bungee.utils.Data;
import de.Modex.bungee.utils.MySQL;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main extends Plugin {

    public Main instance;

    @Override
    public void onEnable() {

        instance = this;

        getProxy().getPluginManager().registerListener(this, new LoginListener());
        getProxy().getPluginManager().registerListener(this, new PostLoginListener());
        getProxy().getPluginManager().registerListener(this, new ServerConnectListener());
        getProxy().getPluginManager().registerListener(this, new ServerKickListener());

        getProxy().getPluginManager().registerCommand(this, new ban()); // bungee.ban bungee.ban.chat
        getProxy().getPluginManager().registerCommand(this, new check()); // bungee.check
        getProxy().getPluginManager().registerCommand(this, new jump()); // bungee.jump
        getProxy().getPluginManager().registerCommand(this, new kick()); // bungee.kick bungee.kick.chat
        getProxy().getPluginManager().registerCommand(this, new lobby()); // bungee.lobby
        getProxy().getPluginManager().registerCommand(this, new maintenance()); // bungee.maintenance bungee.maintenance.bypass
        getProxy().getPluginManager().registerCommand(this, new ping()); // bungee.ping
        getProxy().getPluginManager().registerCommand(this, new reply()); // bungee.whisper
        getProxy().getPluginManager().registerCommand(this, new staff()); // bungee.staffchat
        getProxy().getPluginManager().registerCommand(this, new unban()); // bungee.unban
        getProxy().getPluginManager().registerCommand(this, new whisper()); // bungee.whisper

        startTablistTimer();

        MySQL.connect();
        MySQL.createDefaultTables();

        System.out.println(Data.prefix + "Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        MySQL.close();
        System.out.println(Data.prefix + "Plugin has been disabled!");
    }

    private void startTablistTimer() {
        getProxy().getScheduler().schedule(this, () -> {

            for (ProxiedPlayer player : getProxy().getPlayers()) {
                if (player.isConnected())
                    player.setTabHeader(new TextComponent(Data.header.replaceAll("%server%", player.getServer().getInfo().getName())), new TextComponent(Data.footer.replaceAll("%players%", getProxy().getPlayers().size() + "").replaceAll("%ping%", player.getPing() + "")));
            }

        }, 0, 1, TimeUnit.SECONDS);
    }
}
