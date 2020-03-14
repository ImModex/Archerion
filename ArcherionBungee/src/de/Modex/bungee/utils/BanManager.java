package de.Modex.bungee.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BanManager {

    public static void ban(UUID uuid, String reason, long seconds, String banned_by) {
        long current = System.currentTimeMillis();
        long millis = seconds * 1000;
        long end = current + millis;

        if (seconds == -1)
            end = -1;

        MySQL.update("INSERT INTO banned (uuid, reason, end_date, banned_by) VALUES ('" + uuid + "', '" + reason + "', '" + end + "', '" + banned_by + "')");

        if (ProxyServer.getInstance().getPlayer(uuid) != null) {
            ProxyServer.getInstance().getPlayer(uuid).disconnect(new TextComponent(Data.banKickMessage.replaceAll("%reason%", getReason(uuid)).replaceAll("%remaining%", getRemaining(uuid))));
        }
    }

    public static void unban(UUID uuid) {
        MySQL.update("DELETE FROM banned WHERE uuid='" + uuid + "'");
    }

    public static boolean isBanned(UUID uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM banned WHERE uuid='" + uuid + "'");

        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getReason(UUID uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM banned WHERE uuid='" + uuid + "'");

        try {
            while (rs.next()) {
                return rs.getString("reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Long getEnd(UUID uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM banned WHERE uuid='" + uuid + "'");

        try {
            while (rs.next()) {
                return rs.getLong("end_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getRemaining(UUID uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);

        if (end == -1) {
            return "§4PERMANENT";
        }

        long millis = end - current;

        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long weeks = 0;

        while (millis > 1000) {
            millis -= 1000;
            seconds++;
        }

        while (seconds > 60) {
            seconds -= 60;
            minutes++;
        }

        while (minutes > 60) {
            minutes -= 60;
            hours++;
        }

        while (hours > 24) {
            hours -= 24;
            days++;
        }

        while (days > 7) {
            days -= 7;
            weeks++;
        }

        return "§e" + weeks + " Week(s) " + days + " Day(s) " + hours + " Hour(s) " + minutes + " Minute(s) " + seconds + " Second(s)";

    }

    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<>();
        ResultSet rs = MySQL.getResult("SELECT * FROM banned");

        try {
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String getBannedBy(UUID uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM banned WHERE uuid='" + uuid + "'");
        String banned_by = null;

        try {
            while (rs.next()) {
                banned_by = rs.getString("banned_by");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (banned_by != null) {
            OfflinePlayer ret = MojangAPI.loadPlayer(uuid);

            if (ret != null)
                return ret.getName();
            else
                return null;
        } else
            return null;
    }

    public static Rank getRank(UUID uuid) {
        List<String> list = new ArrayList<>();
        ResultSet rs = MySQL.getResult("SELECT * FROM permissions_inheritance WHERE uuid='" + uuid + "'");

        try {
            while (rs.next()) {
                list.add(rs.getString("parent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Rank.getHighest(list);
    }
}
