package de.Modex.survival.listener;

import de.Modex.survival.utils.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent e) {
        if (Data.teleportCooldown.containsKey(e.getPlayer().getUniqueId())) {
            Player p = e.getPlayer();
            Location curLoc = p.getLocation();
            Location prevLoc = Data.teleportCooldown.get(p.getUniqueId());

            if (curLoc.getX() != prevLoc.getX() || curLoc.getY() != prevLoc.getY() || curLoc.getZ() != prevLoc.getZ()) {
                p.sendMessage(Data.prefix + "§cTeleport has been interrupted!");
                Data.teleportCooldown.remove(p.getUniqueId());
                Data.teleportTask.get(p.getUniqueId()).cancel();
                Data.teleportTask.remove(p.getUniqueId());
                Data.teleportTestCooldown.remove(p.getUniqueId());
            }
        }
    }
}
