package de.Modex.bungee.listener;

import de.Modex.bungee.utils.BanManager;
import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    @EventHandler
    public void on(LoginEvent e) {
        PendingConnection p = e.getConnection();

        if(BanManager.isBanned(p.getUniqueId())) {
            long current = System.currentTimeMillis();
            long end = BanManager.getEnd(p.getUniqueId());

            if(current < end || end == -1) {
                e.setCancelReason(new TextComponent(Data.banKickMessage.replaceAll("%reason%", BanManager.getReason(p.getUniqueId())).replaceAll("%remaining%", BanManager.getRemaining(p.getUniqueId()))));
                e.setCancelled(true);
            }
        }
    }
}
