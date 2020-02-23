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

public class delwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (PermissionsEx.getUser(p).has("survival.delwarp")) {
            if (args.length > 0) {
                File f = new File("plugins/Survival/warps.yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

                if (config.contains("warps." + args[0].toLowerCase())) {
                    config.set("warps." + args[0].toLowerCase(), null);
                    p.sendMessage(Data.prefix + "§7The warp §e" + args[0].toLowerCase() + " §7has been deleted successfully.");
                    try {
                        config.save(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    p.sendMessage(Data.prefix + "§cThis warp does not exist!");
                }
            } else {
                p.sendMessage(Data.prefix + "§c/delwarp <warp>");
            }
        } else {
            p.sendMessage(Data.no_perms);
        }


        return true;
    }
}
