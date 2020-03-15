package de.Modex.survival.listener;

import de.Modex.survival.events.EndfightStartEvent;
import de.Modex.survival.utils.Data;
import de.Modex.survival.utils.EndfightTimers;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EndfightStartListener implements Listener {

    @EventHandler
    public void on(EndfightStartEvent e) {
        World end = Bukkit.getServer().getWorld("world_the_end");
        Player p = e.getPlayer();
        Data.playersInEnd.add(p);

        if (end != null) {
            //EndfightTimers.startFireballTask();
            EndfightTimers.startLightningTask();
            EndfightTimers.startMobSpawnTask();
            EndfightTimers.startMobTargetTask();
        }
    }
}
