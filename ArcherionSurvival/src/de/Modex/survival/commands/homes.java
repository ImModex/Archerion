package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class homes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        File f = new File("plugins/Survival/homes.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

        List<String> homelist = new ArrayList<>();

        if (config.getConfigurationSection("homes." + p.getName()) == null) {
            config.createSection("homes." + p.getName());
        }

        for (Map.Entry<String, Object> entry : config.getConfigurationSection("homes." + p.getName()).getValues(false).entrySet()) {
            homelist.add(entry.getKey());
        }

        p.sendMessage(Data.prefix + "§7Homes: §e" + String.join("§7, §e", homelist));

        return true;
    }
}
