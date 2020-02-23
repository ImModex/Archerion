package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class invsee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (PermissionsEx.getUser(p).has("survival.invsee")) {
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {

                    if (p == target) {
                        p.sendMessage(Data.prefix + "§cYou can't invsee your own inventory!");
                    } else {
                        p.openInventory(target.getInventory());
                        Data.invseeList.put(p, target);
                    }
                } else {
                    p.sendMessage(Data.prefix + "§cThis player does not exist!");
                }

            } else {
                p.sendMessage(Data.prefix + "§c/invsee <player>");
            }
        } else {
            p.sendMessage(Data.no_perms);
        }

        return true;
    }
}
