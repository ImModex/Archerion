package de.Modex.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
