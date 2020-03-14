package de.Modex.bungee.listener;

import de.Modex.bungee.utils.BanManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {

    @EventHandler
    public void on(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();

        if(BanManager.isBanned(p.getUniqueId())) {
            BanManager.unban(p.getUniqueId());
        }
    }
}
