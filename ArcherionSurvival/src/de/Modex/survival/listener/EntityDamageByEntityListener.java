package de.Modex.survival.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Arrow && e.getEntityType().equals(EntityType.ENDER_CRYSTAL)) {
            Arrow arrow = (Arrow) e.getDamager();

            if(arrow.getShooter() instanceof Player) {
                Player killer = (Player) arrow.getShooter();

                if (killer != null) {
                    killer.damage(4);
                    killer.getWorld().strikeLightning(killer.getLocation());
                    killer.setFireTicks(20 * 3);
                }
            }
        }
    }
}
