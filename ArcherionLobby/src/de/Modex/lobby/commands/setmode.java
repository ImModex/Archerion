package de.Modex.lobby.commands;

import de.Modex.lobby.utils.Data;
import de.Modex.lobby.utils.Navigator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class setmode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (PermissionsEx.getUser(p).has("lobby.setmode")) {
            if(args.length == 5) {

                int slot = Integer.parseInt(args[1]);
                if(slot > 7 || slot < 1) {
                    p.sendMessage(Data.prefix + "§cThe slot can only be 1-7");
                    return true;
                }

                int port = Integer.parseInt(args[4]);

                try {
                    if(args[0].equalsIgnoreCase("spawn")) {
                        Navigator.setMode(p, "Spawn", 8, args[2], args[3], port);
                        p.sendMessage(Data.prefix + "§7Successfully set §eSpawn");
                    } else {
                        Navigator.setMode(p, args[0], slot, args[2], args[3], port);
                        p.sendMessage(Data.prefix + "§7Successfully set mode §e" + args[0]);
                    }
                } catch (Exception e) {
                    if(e.getMessage().equals("modeName")) {
                        p.sendMessage(Data.prefix + "§cThe mode name can only contain letters!");
                    } else if(e.getMessage().equals("itemNotFound")) {
                        p.sendMessage(Data.prefix + "§cThe item could not be found!");
                    }
                }
            } else {
                p.sendMessage(Data.prefix + "§cUsage: /setmode <Mode> <Slot> <Item> <Server>");
            }
        } else {
            p.sendMessage(Data.no_perms);
        }
        return true;
    }
}
