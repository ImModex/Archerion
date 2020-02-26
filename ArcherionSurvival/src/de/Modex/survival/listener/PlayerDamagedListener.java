package de.Modex.survival.listener;

import de.Modex.survival.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamagedListener implements Listener {

    @EventHandler
    public void on(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(Data.teleportCooldown.containsKey(p.getUniqueId())) {
                p.sendMessage(Data.prefix + "§cTeleport has been interrupted!");
                Data.teleportCooldown.remove(p.getUniqueId());
                Data.teleportTask.get(p.getUniqueId()).cancel();
                Data.teleportTask.remove(p.getUniqueId());
                Data.teleportTestCooldown.remove(p.getUniqueId());
            }
        }
    }
}
