package de.Modex.rank.listener;

import de.Modex.rank.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        msg = msg.replaceAll("%", "percent");

        if (PermissionsEx.getUser(p).has("archerion.color")) {
            msg = msg.replaceAll("&", "§");
        }

        if (PermissionsEx.getUser(p).inGroup("Owner")) {
            //e.setFormat(Data.prefixOwner + p.getName() + " §7» " + msg);
            sendToAll(Data.prefixOwner + p.getName() + " §7» " + msg);
        } else if (PermissionsEx.getUser(p).inGroup("Staff")) {
            //e.setFormat(Data.prefixStaff + p.getName() + " §7» " + msg);
            sendToAll(Data.prefixStaff + p.getName() + " §7» " + msg);
        } else if (PermissionsEx.getUser(p).inGroup("Friend")) {
            //e.setFormat(Data.prefixFriend + p.getName() + " §7» " + msg);
            sendToAll(Data.prefixFriend + p.getName() + " §7» " + msg);
        } else if (PermissionsEx.getUser(p).inGroup("Donator")) {
            //e.setFormat(Data.prefixDonator + p.getName() + " §7» " + msg);
            sendToAll(Data.prefixDonator + p.getName() + " §7» " + msg);
        } else if (PermissionsEx.getUser(p).inGroup("Player")) {
            //e.setFormat(Data.prefixPlayer + p.getName() + " §7» " + msg);
            sendToAll(Data.prefixPlayer + p.getName() + " §7» " + msg);
        }

        e.setCancelled(true);
    }

    private void sendToAll(String message) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(message);
        }

        Bukkit.getConsoleSender().sendMessage(message);
    }
}
