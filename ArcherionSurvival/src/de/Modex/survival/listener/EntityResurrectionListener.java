package de.Modex.survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class EntityResurrectionListener implements Listener {

    @EventHandler
    public void on(EntityResurrectEvent e) {

        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            World end = Bukkit.getWorld("world_the_end");

            if (end != null) {
                if (p.getWorld().equals(end)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
