package de.Modex.survival.listener;

import de.Modex.survival.utils.EndfightTimers;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void on(EntityDeathEvent e) {
        if (e.getEntityType().equals(EntityType.ENDER_DRAGON)) {
            EndfightTimers.stopFireballTask();
            EndfightTimers.stopLightningTask();
            EndfightTimers.stopMobSpawnTask();
            EndfightTimers.stopMobTargetTask();
        }

        if(e.getEntityType().equals(EntityType.PLAYER)) {
            World end = Bukkit.getWorld("world_the_end");

            if(end != null && end.getPlayers().isEmpty()) {
                EndfightTimers.stopFireballTask();
                EndfightTimers.stopLightningTask();
                EndfightTimers.stopMobSpawnTask();
                EndfightTimers.stopMobTargetTask();
            }
        }
    }
}
