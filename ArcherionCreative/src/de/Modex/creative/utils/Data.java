package de.Modex.creative.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Data {

    public static final String prefix = "§8[§3Archerion§8] ";
    public static final String no_perms = prefix + "§cYou don't have the permission to use this command!";

    public static ArrayList<Player> allowBuild = new ArrayList<>();
    public static ArrayList<Player> teleportCooldown = new ArrayList<>();
}
