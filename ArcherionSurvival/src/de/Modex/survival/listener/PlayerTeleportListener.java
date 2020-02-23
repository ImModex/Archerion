package de.Modex.survival.listener;

import de.Modex.survival.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void on(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        Data.teleportTask.remove(p.getUniqueId());
        Data.teleportCooldown.remove(p.getUniqueId());
    }
}
