package de.Modex.survival.listener;

import de.Modex.survival.utils.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void on(InventoryCloseEvent e) {
        Data.invseeList.remove(e.getPlayer());
    }
}
