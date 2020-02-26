package de.Modex.survival.listener;

import de.Modex.survival.Main;
import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            e.setJoinMessage("§8[§a+§8] §8" + e.getPlayer().getDisplayName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        try {
            e.setQuitMessage("§8[§c-§8] §8" + e.getPlayer().getDisplayName());
            Data.backList.remove(e.getPlayer());
            Data.replyList.remove(e.getPlayer());
            Data.invseeList.remove(e.getPlayer());
            Data.teleportCooldown.remove(e.getPlayer().getUniqueId());
            if(Data.teleportTask.containsKey(e.getPlayer().getUniqueId()))
                Data.teleportTask.get(e.getPlayer().getUniqueId()).cancel();
            Data.teleportTask.remove(e.getPlayer().getUniqueId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
