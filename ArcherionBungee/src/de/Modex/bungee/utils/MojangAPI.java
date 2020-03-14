package de.Modex.bungee.utils;

import org.json.JSONObject;

import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

public class MojangAPI {
    private static final String API_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final String UUID_API_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";

    public static OfflinePlayer loadPlayer(String name) {
        String playerName = loadName(name);
        UUID playerUUID = loadUUID(name);
        if (playerName != null && playerUUID != null)
            return new OfflinePlayer(playerName, playerUUID);
        else
            return null;
    }

    public static OfflinePlayer loadPlayer(UUID uuid) {
        String playerName = loadName(uuid);
        if (playerName != null)
            return new OfflinePlayer(playerName, getUniqueIdFromString(uuid.toString().replaceAll("-", "")));
        else
            return null;
    }

    private static UUID loadUUID(String name) {
        try {
            final URL url = new URL(API_URL + name);
            return getUniqueIdFromString(new JSONObject(new Scanner(url.openConnection().getInputStream()).nextLine()).getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String loadName(String name) {
        try {
            final URL url = new URL(API_URL + name);
            return new JSONObject(new Scanner(url.openConnection().getInputStream()).nextLine()).getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String loadName(UUID uuid) {
        try {
            final URL url = new URL(UUID_API_URL + uuid.toString().replaceAll("-", ""));
            return new JSONObject(new Scanner(url.openConnection().getInputStream()).nextLine()).getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UUID getUniqueIdFromString(String uuid) {
        return UUID.fromString(uuid.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
    }
}
