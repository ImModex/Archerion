package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class deaths implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;
        File f = new File("plugins/Survival/deaths/deaths.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);

        if (args.length == 0) {
            if (c.get(p.getName()) != null) {
                p.sendMessage(Data.prefix + "§7You died §e" + c.getInt(p.getName()) + " §7times so far!");
            } else {
                p.sendMessage(Data.prefix + "§7You have not died yet! :)");
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("leaderboard") || args[0].equalsIgnoreCase("top")) {
                Map<String, Integer> deaths = new HashMap<>();

                for(String key : c.getKeys(false)) {
                    deaths.put(key, c.getInt(key));
                }

                Map<String, Integer> leaderboard = sortByValue(deaths);

                p.sendMessage("§8[§dDeaths §8- §dLeaderboard§8]");

                int i = 0;
                String last = "";

                ArrayList<String> keylist = new ArrayList<>(leaderboard.keySet());

                for(int j = keylist.size() - 1; j >= 0; j--) {
                    if(i < 3) {
                        if(last.equals(keylist.get(j)))
                            break;
                        if(leaderboard.get(keylist.get(j)) > 1)
                            p.sendMessage("§a" + (i + 1) + "§7. Place §8-> §a" + keylist.get(j) + "§7 with §e" + leaderboard.get(keylist.get(j)) + " §7deaths!");
                        else
                            p.sendMessage("§a" + (i + 1) + "§7. Place §8-> §a" + keylist.get(j) + "§7 with §e" + leaderboard.get(keylist.get(j)) + " §7death!");
                        last = keylist.get(j);
                        i++;
                    } else
                        break;
                }
            } else {
                if (c.get(args[0]) != null) {
                    p.sendMessage(Data.prefix + "§7This player died §e" + c.getInt(args[0]) + " §7times so far!");
                } else {
                    p.sendMessage(Data.prefix + "§7This player has not died yet! :)");
                }
            }
        }

        return true;
    }

    private static HashMap<String, Integer> sortByValue(Map<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(hm.entrySet());

        list.sort(Comparator.comparing(o -> (o.getValue())));

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
