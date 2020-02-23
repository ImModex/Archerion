package de.Modex.survival.listener;

import de.Modex.survival.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (!PermissionsEx.getUser(p).has("survival.invsee.bypass")) {
            if (Data.invseeList.containsKey(p)) {
                e.setCancelled(true);
            }
        }
    }
}
