package de.Modex.survival.listener;

import de.Modex.survival.events.EndfightStartEvent;
import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

        if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
            Bukkit.getServer().getPluginManager().callEvent(new EndfightStartEvent(p));
            if(p.getInventory().contains(Material.TOTEM_OF_UNDYING)) {
                p.sendMessage("§5EnderDragon §7» §cDon't rely on that idiotic totem, weakling. Hahahaha.");
            }
        }
    }
}
