package de.Modex.creative;

import de.Modex.creative.listener.EventCanceller;
import de.Modex.creative.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventCanceller(), this);
        System.out.println(Data.prefix + "Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println(Data.prefix + "Plugin has been disabled!");
    }
}
