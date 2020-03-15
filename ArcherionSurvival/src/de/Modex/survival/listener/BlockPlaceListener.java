package de.Modex.survival.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void on(BlockPlaceEvent e) {
        World end = Bukkit.getWorld("world_the_end");

        if(end != null && e.getBlockPlaced().getWorld().equals(end)) {
            if(e.getBlockPlaced().getType().name().toLowerCase().contains("bed")) {
                e.setCancelled(true);
            }
        }
    }
}
