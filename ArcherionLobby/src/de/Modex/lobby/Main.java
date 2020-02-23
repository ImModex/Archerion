package de.Modex.lobby;

import de.Modex.lobby.commands.build;
import de.Modex.lobby.commands.setmode;
import de.Modex.lobby.listener.*;
import de.Modex.lobby.utils.Data;
import de.Modex.lobby.utils.Navigator;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        disableAchievements();

        if (!Navigator.getNavigator()) {
            Navigator.createNavigator();
        }

        getCommand("build").setExecutor(new build());
        getCommand("setmode").setExecutor(new setmode());

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DoubleJumpListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntitySpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new GamemodeChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemDropListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFoodListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), this);

        System.out.println(Data.prefix + "Plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        System.out.println(Data.prefix + "Plugin has been unloaded!");

    }

    private void disableAchievements() {
        for(World world : getServer().getWorlds()) {
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }
    }
}
