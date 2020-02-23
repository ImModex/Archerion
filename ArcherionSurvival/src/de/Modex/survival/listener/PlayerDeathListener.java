package de.Modex.survival.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void on(PlayerDeathEvent e) {
        try {
            Player p = e.getEntity();
            e.setDeathMessage("§7" + e.getDeathMessage());
            p.sendMessage("You died at " + (int) p.getLocation().getX() + " | " + (int) p.getLocation().getY() + " | " + (int) p.getLocation().getZ());
            saveInventory(p);
            addDeath(p);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void saveInventory(Player p) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        Date date = new Date();
        String d = format.format(date);

        File f = new File("plugins/Survival/deaths/" + p.getName() + "/", d + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("inventory.armor", p.getInventory().getArmorContents());
        c.set("inventory.content", p.getInventory().getContents());
        c.set("inventory.exp", getPlayerExp(p));
        c.save(f);
    }

    private void addDeath(Player p) throws IOException {
        File f = new File("plugins/Survival/deaths/deaths.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);

        if(c.get(p.getName()) != null) {
            c.set(p.getName(), c.getInt(p.getName()) + 1);
        } else {
            c.set(p.getName(), 1);
        }

        c.save(f);
    }

    private int getPlayerExp(Player player) {
        int exp = 0;
        int level = player.getLevel();

        // Get the amount of XP in past levels
        exp += getExpAtLevel(level);

        // Get amount of XP towards next level
        exp += Math.round(getExpToLevelUp(level) * player.getExp());

        return exp;
    }

    private int getExpAtLevel(int level) {
        if (level <= 16) {
            return (int) (Math.pow(level, 2) + 6 * level);
        } else if (level <= 31) {
            return (int) (2.5 * Math.pow(level, 2) - 40.5 * level + 360.0);
        } else {
            return (int) (4.5 * Math.pow(level, 2) - 162.5 * level + 2220.0);
        }
    }

    private int getExpToLevelUp(int level) {
        if (level <= 15) {
            return 2 * level + 7;
        } else if (level <= 30) {
            return 5 * level - 38;
        } else {
            return 9 * level - 158;
        }
    }
}

