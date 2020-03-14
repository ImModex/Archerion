package de.Modex.bungee;

import de.Modex.bungee.commands.*;
import de.Modex.bungee.listener.LoginListener;
import de.Modex.bungee.listener.PostLoginListener;
import de.Modex.bungee.listener.ServerConnectListener;
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

        getProxy().getPluginManager().registerListener(this, new ServerConnectListener());
        getProxy().getPluginManager().registerListener(this, new PostLoginListener());
        getProxy().getPluginManager().registerListener(this, new LoginListener());

        getProxy().getPluginManager().registerCommand(this, new lobby());
        getProxy().getPluginManager().registerCommand(this, new whisper());
        getProxy().getPluginManager().registerCommand(this, new reply());
        getProxy().getPluginManager().registerCommand(this, new maintenance());
        getProxy().getPluginManager().registerCommand(this, new ping());
        getProxy().getPluginManager().registerCommand(this, new ban());
        getProxy().getPluginManager().registerCommand(this, new check());
        getProxy().getPluginManager().registerCommand(this, new unban());
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
                player.setTabHeader(new TextComponent(Data.header.replaceAll("%server%", player.getServer().getInfo().getName())), new TextComponent(Data.footer.replaceAll("%players%", getProxy().getPlayers().size() + "").replaceAll("%ping%", player.getPing() + "")));
            }

        }, 0, 1, TimeUnit.SECONDS);
    }
}
