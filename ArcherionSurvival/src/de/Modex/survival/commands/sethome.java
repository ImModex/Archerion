package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class sethome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (args.length > 0) {
            File f = new File("plugins/Survival/homes.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

            config.set("homes." + p.getName() + "." + args[0].toLowerCase() + ".world", p.getWorld().getName());
            config.set("homes." + p.getName() + "." + args[0].toLowerCase() + ".x", p.getLocation().getX());
            config.set("homes." + p.getName() + "." + args[0].toLowerCase() + ".y", p.getLocation().getY());
            config.set("homes." + p.getName() + "." + args[0].toLowerCase() + ".z", p.getLocation().getZ());
            config.set("homes." + p.getName() + "." + args[0].toLowerCase() + ".yaw", p.getLocation().getYaw());
            config.set("homes." + p.getName() + "." + args[0].toLowerCase() + ".pitch", p.getLocation().getPitch());

            try {
                config.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }

            p.sendMessage(Data.prefix + "§7Home §e" + args[0].toLowerCase() + " §7has been set successfully!");
        } else {
            p.sendMessage(Data.prefix + "§c/sethome <home>");
        }
        return true;
    }
}
