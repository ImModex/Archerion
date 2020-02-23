package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.File;
import java.io.IOException;

public class setwarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (PermissionsEx.getUser(p).has("survival.setwarp")) {
            if (args.length > 0) {
                File f = new File("plugins/Survival/warps.yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

                config.set("warps." + args[0].toLowerCase() + ".world", p.getWorld().getName());
                config.set("warps." + args[0].toLowerCase() + ".x", p.getLocation().getX());
                config.set("warps." + args[0].toLowerCase() + ".y", p.getLocation().getY());
                config.set("warps." + args[0].toLowerCase() + ".z", p.getLocation().getZ());
                config.set("warps." + args[0].toLowerCase() + ".yaw", p.getLocation().getYaw());
                config.set("warps." + args[0].toLowerCase() + ".pitch", p.getLocation().getPitch());

                try {
                    config.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                p.sendMessage(Data.prefix + "§7Warp §e" + args[0].toLowerCase() + " §7has been created successfully!");
            } else {
                p.sendMessage(Data.prefix + "§c/setwarp <warp>");
            }
        } else {
            p.sendMessage(Data.no_perms);
        }
        return true;
    }
}
