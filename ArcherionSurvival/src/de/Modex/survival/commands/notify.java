package de.Modex.survival.commands;

import de.Modex.survival.utils.Config;
import de.Modex.survival.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class notify implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if(Config.config.contains("ClearNotification." + p.getUniqueId()) && Config.config.getBoolean("ClearNotification." + p.getUniqueId())) {
            Config.config.set("ClearNotification." + p.getUniqueId(), false);
            try {
                Config.config.save(Config.configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendMessage(Data.prefix + "§7Notifications have now been §cdisabled§7.");
        } else {
            Config.config.set("ClearNotification." + p.getUniqueId(), true);
            try {
                Config.config.save(Config.configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendMessage(Data.prefix + "§7Notifications have now been §aenabled§7.");
        }

        return true;
    }
}
