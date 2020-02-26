package de.Modex.rank.listener;

import de.Modex.rank.Main;
import de.Modex.rank.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class JoinQuitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!PermissionsEx.getUser(p).inGroup("Owner") && !PermissionsEx.getUser(p).inGroup("Staff") && !PermissionsEx.getUser(p).inGroup("Friend") && !PermissionsEx.getUser(p).inGroup("Donator") && !PermissionsEx.getUser(p).inGroup("Player")) {
            PermissionsEx.getUser(p).addGroup("Player");
        }
        setPrefix(p);
    }

    private void setPrefix(Player p) {

        String team = "";

        if (PermissionsEx.getUser(p).inGroup("Owner")) {
            team = "00000Owner";
        } else if (PermissionsEx.getUser(p).inGroup("Staff")) {
            team = "00001Staff";
        } else if (PermissionsEx.getUser(p).inGroup("Friend")) {
            team = "00002Friend";
        } else if (PermissionsEx.getUser(p).inGroup("Donator")) {
            team = "00003Donator";
        } else if (PermissionsEx.getUser(p).inGroup("Player")) {
            team = "00004Player";
        }

        Main.sb.getTeam(team).addPlayer(p);

        if (PermissionsEx.getUser(p).inGroup("Owner")) {
            p.setDisplayName(Data.prefixOwner + p.getName());
        } else if (PermissionsEx.getUser(p).inGroup("Staff")) {
            p.setDisplayName(Data.prefixStaff + p.getName());
        } else if (PermissionsEx.getUser(p).inGroup("Friend")) {
            p.setDisplayName(Data.prefixFriend + p.getName());
        } else if (PermissionsEx.getUser(p).inGroup("Donator")) {
            p.setDisplayName(Data.prefixDonator + p.getName());
        } else if (PermissionsEx.getUser(p).inGroup("Player")) {
            p.setDisplayName(Data.prefixPlayer + p.getName());
        }

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(Main.sb);
        }
    }
}
