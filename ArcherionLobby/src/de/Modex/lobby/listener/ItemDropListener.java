package de.Modex.lobby.listener;

import de.Modex.lobby.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class ItemDropListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent e) {
        if (!Data.allowBuild.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player)
            if (!Data.allowBuild.contains((Player) e.getEntity()))
                e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerPickupArrowEvent e) {
        if (!Data.allowBuild.contains(e.getPlayer()))
            e.setCancelled(true);
    }
}
