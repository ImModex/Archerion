package de.Modex.survival.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Data {

    public static final String prefix = "§8[§3Archerion§8] ";
    public static final String no_perms = prefix + "§cYou don't have the permission to use this command!";

    public static HashMap<Player, Player> invseeList = new HashMap<>();
    public static HashMap<Player, Location> backList = new HashMap<>();
    public static HashMap<Player, Player> replyList = new HashMap<>();
    public static HashMap<UUID, Location> teleportCooldown = new HashMap<>();
    public static HashMap<UUID, BukkitTask> teleportTask = new HashMap<>();
    public static ArrayList<UUID> teleportTestCooldown = new ArrayList<>();
    public static ArrayList<Player> playersInEnd = new ArrayList<>();

    public static BukkitTask fireBallTask = null;
    public static BukkitTask lightningTask = null;
    public static BukkitTask mobSpawnTask = null;
    public static BukkitTask mobTargetTask = null;
}
