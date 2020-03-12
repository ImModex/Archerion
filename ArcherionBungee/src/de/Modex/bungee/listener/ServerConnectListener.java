package de.Modex.bungee.listener;

import de.Modex.bungee.utils.Config;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class ServerConnectListener implements Listener {

    @EventHandler
    public void onPlayerConnect(ServerConnectEvent e) {
        ProxiedPlayer p = e.getPlayer();

        if (p.getServer() == null) {
            if (Config.config.getBoolean("maintenance") && Config.config.getBoolean("maintenance")) {
                if (!p.hasPermission("bungee.maintenance.bypass")) {
                    p.disconnect(new TextComponent("§cThis server is in maintenance mode!"));
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPing(ProxyPingEvent e) {
        if (Config.config.getBoolean("maintenance") && Config.config.getBoolean("maintenance")) {
            e.getResponse().setDescriptionComponent(new TextComponent("§cArcherion Network is in maintenance!"));
        }
    }
}
