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

public class delhome implements CommandExecutor {
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

            if (config.contains("homes." + p.getName() + "." + args[0].toLowerCase())) {
                config.set("homes." + p.getName() + "." + args[0].toLowerCase(), null);
                p.sendMessage(Data.prefix + "§7The home §e" + args[0].toLowerCase() + " §7has been deleted successfully.");
                try {
                    config.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                p.sendMessage(Data.prefix + "§cThis home does not exist!");
            }
        } else {
            p.sendMessage(Data.prefix + "§c/delhome <home>");
        }

        return true;
    }
}
