package de.Modex.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeChangeListener implements Listener {

    @EventHandler
    public void on(PlayerGameModeChangeEvent e) {
        e.getPlayer().setAllowFlight(true);
    }
}
