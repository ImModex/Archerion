package de.Modex.lobby.listener;

import de.Modex.lobby.utils.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
        if(!Data.allowBuild.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockPlaceEvent e) {
        if(!Data.allowBuild.contains(e.getPlayer()))
            e.setCancelled(true);
    }
}
