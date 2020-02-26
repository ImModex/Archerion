package de.Modex.rank.utils;

import de.Modex.rank.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Data {

    public static final String prefix = "§8[§3Archerion§8] ";
    public static final String no_perms = prefix + "§cYou don't have the permission to use this command!";

    public static final String prefixOwner = "§e§lOwner §8● §7";
    public static final String prefixStaff = "§5Staff §8● §7";
    public static final String prefixFriend = "§aFriend §8● §7";
    public static final String prefixDonator = "§bDonator §8● §7";
    public static final String prefixPlayer = "§7";

    private static void setPrefix(Player p) {

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

    public static void updatePrefix(Player p) {
        if (!PermissionsEx.getUser(p).inGroup("Owner") && !PermissionsEx.getUser(p).inGroup("Staff") && !PermissionsEx.getUser(p).inGroup("Friend") && !PermissionsEx.getUser(p).inGroup("Donator") && !PermissionsEx.getUser(p).inGroup("Player")) {
            PermissionsEx.getUser(p).addGroup("Player");
        }
        Data.setPrefix(p);
    }
}
