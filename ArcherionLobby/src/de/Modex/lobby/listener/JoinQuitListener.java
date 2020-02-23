package de.Modex.lobby.listener;

import de.Modex.lobby.utils.Data;
import de.Modex.lobby.utils.InventoryHandler;
import de.Modex.lobby.utils.Navigator;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        InventoryHandler.setInventory(e.getPlayer());
        e.getPlayer().teleport(Navigator.getSpawn());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Data.allowBuild.remove(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKick(PlayerKickEvent e) {
        Data.allowBuild.remove(e.getPlayer());
    }
}
