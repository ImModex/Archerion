package de.Modex.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Navigator {
    public static void createNavigator() {
        File folder = new File("plugins//Lobby");
        File file = new File("plugins//Lobby//Navigator.yml");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean getNavigator() {
        File file = new File("plugins//Lobby//Navigator.yml");
        return file.exists();
    }

    public static void setMode(Player p, String mode, int slot, String item, String server, int port) throws Exception {

        if(!mode.chars().allMatch(Character::isLetter))
            throw new Exception("modeName");

        if(Material.getMaterial(item) == null)
            throw new Exception("itemNotFound");

        File file = new File("plugins//Lobby//Navigator.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set(mode + ".X", loc.getX());
        cfg.set(mode + ".Y", loc.getY());
        cfg.set(mode + ".Z", loc.getZ());
        cfg.set(mode + ".Yaw", loc.getYaw());
        cfg.set(mode + ".Pitch", loc.getPitch());
        cfg.set(mode + ".Slot", slot);
        cfg.set(mode + ".Item", item);
        cfg.set(mode + ".Server", server);
        cfg.set(mode + ".Port", port);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Mode getMode(Player p, String mode) throws Exception {
        File file = new File("plugins//Lobby//Navigator.yml");
        Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if(!cfg.contains(mode))
            throw new Exception();

        loc.setX(cfg.getDouble(mode + ".X"));
        loc.setY(cfg.getDouble(mode + ".Y"));
        loc.setZ(cfg.getDouble(mode + ".Z"));
        loc.setYaw((float) cfg.getDouble(mode + ".Yaw"));
        loc.setPitch((float) cfg.getDouble(mode + ".Pitch"));

        Mode ret = new Mode();
        ret.setLocation(loc);
        ret.setSlot(cfg.getInt(mode + ".Slot"));
        ret.setItem(Material.getMaterial(cfg.getString(mode + ".Item")));
        ret.setServer(cfg.getString(mode + ".Server"));
        ret.setPort(cfg.getInt(mode + ".Port"));

        return ret;
    }

    public static Location getSpawn() {
        File file = new File("plugins//Lobby//Navigator.yml");
        Location loc = new Location(Bukkit.getWorld("world"), 0, 0, 0);
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Spawn.X"));
        loc.setY(cfg.getDouble("Spawn.Y"));
        loc.setZ(cfg.getDouble("Spawn.Z"));
        loc.setYaw((float) cfg.getDouble("Spawn.Yaw"));
        loc.setPitch((float) cfg.getDouble("Spawn.Pitch"));

        return loc;
    }
}
