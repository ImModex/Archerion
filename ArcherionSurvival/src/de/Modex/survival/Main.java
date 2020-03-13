package de.Modex.survival;

import de.Modex.survival.commands.*;
import de.Modex.survival.listener.*;
import de.Modex.survival.utils.Config;
import de.Modex.survival.utils.Countdown;
import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static Main instance;

    public void onEnable() {
        instance = this;

        File f = new File("plugins/Survival/warps.yml");
        File f1 = new File("plugins/Survival/homes.yml");
        File dir = new File("plugins/Survival/");
        if (!dir.exists())
            dir.mkdirs();
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getCommand("xpt").setExecutor(new xpt());
        getCommand("pos").setExecutor(new pos());
        getCommand("warp").setExecutor(new warp());
        getCommand("delwarp").setExecutor(new delwarp());
        getCommand("setwarp").setExecutor(new setwarp());
        getCommand("listwarp").setExecutor(new listwarp());
        getCommand("invsee").setExecutor(new invsee());
        getCommand("back").setExecutor(new back());
        //getCommand("whisper").setExecutor(new whisper());
        //getCommand("reply").setExecutor(new reply());
        getCommand("notify").setExecutor(new notify());
        getCommand("home").setExecutor(new home());
        getCommand("sethome").setExecutor(new sethome());
        getCommand("delhome").setExecutor(new delhome());
        getCommand("homes").setExecutor(new homes());
        getCommand("restore").setExecutor(new restore());
        getCommand("deaths").setExecutor(new deaths());
        //getCommand("ping").setExecutor(new ping());
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new CreatureSpawnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamagedListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpawnerBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);

        startCleanupTimer();

        System.out.println(Data.prefix + "Plugin has been loaded!");
    }

    public void onDisable() {
        System.out.println(Data.prefix + "Plugin has been unloaded!");
    }

    private void startCleanupTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> new Countdown(30, this) {

            @Override
            public void count(int t) {
                if (t == 30 || t == 20 || t == 10 || t == 3 || t == 2 || t == 1) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (Config.config.contains("ClearNotification." + all.getUniqueId()) && Config.config.getBoolean("ClearNotification." + all.getUniqueId())) {
                            all.sendMessage(Data.prefix + "§7Ground items will be cleared in " + t + " seconds.");
                        } else if (!Config.config.contains("ClearNotification." + all.getUniqueId())) {
                            Config.config.set("ClearNotification." + all.getUniqueId(), true);
                            try {
                                Config.config.save(Config.configFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if (t == 0) {
                    for (World world : Bukkit.getWorlds()) {
                        world.getEntities().stream().filter(Item.class::isInstance).forEach(Entity::remove);

                        for (Entity e : world.getEntities()) {
                            if (e instanceof Monster || e.getType().equals(EntityType.PHANTOM) && !e.getType().equals(EntityType.ENDER_CRYSTAL) && !e.getType().equals(EntityType.ENDER_DRAGON) && !e.getType().equals(EntityType.WITHER_SKULL) && !e.getType().equals(EntityType.WITHER) && !e.getType().equals(EntityType.HORSE) && !e.getType().equals(EntityType.SKELETON_HORSE) && !e.getType().equals(EntityType.ZOMBIE_HORSE) && !e.getType().equals(EntityType.VILLAGER) && !e.getType().equals(EntityType.ZOMBIE_VILLAGER) && !e.getType().equals(EntityType.MINECART) && !e.getType().equals(EntityType.MINECART_CHEST) && !e.getType().equals(EntityType.MINECART_FURNACE) && !e.getType().equals(EntityType.MINECART_HOPPER) && !e.getType().equals(EntityType.MINECART_MOB_SPAWNER)) {
                                if (e.getCustomName() == null) {
                                    e.remove();
                                }
                            }
                        }
                    }

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (Config.config.contains("ClearNotification." + all.getUniqueId()) && Config.config.getBoolean("ClearNotification." + all.getUniqueId())) {
                            all.sendMessage(Data.prefix + "§7Ground items have been cleared.");
                        } else if (!Config.config.contains("ClearNotification." + all.getUniqueId())) {
                            Config.config.set("ClearNotification." + all.getUniqueId(), true);
                            try {
                                Config.config.save(Config.configFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }.start(), 20L * 60 * 15, 20L * 60 * 15);
    }
}

