package de.Modex.lobby.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJumpListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.setAllowFlight(true);
        p.setFlying(false);
    }

    @EventHandler
    public void on(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();

        if(p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
            e.getPlayer().setAllowFlight(false);
            e.getPlayer().setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(1).setY(1));
        }
    }

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() != GameMode.CREATIVE) {
            if(p.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
                p.setAllowFlight(true);
                p.setFlying(false);
            }
        }
    }
}
