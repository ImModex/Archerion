package de.Modex.bungee;

import de.Modex.bungee.commands.lobby;
import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    @Override
    public void onEnable() {

        getProxy().getPluginManager().registerCommand(this, new lobby());

        System.out.println(Data.prefix + "Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println(Data.prefix + "Plugin has been disabled!");
    }
}
