package de.Modex.lobby.commands;

import de.Modex.lobby.utils.Data;
import de.Modex.lobby.utils.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class build implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if(PermissionsEx.getUser(p).has("lobby.build")) {
            if(args.length == 0) {
                switchBuildMode(p);
            } else {
                if(PermissionsEx.getUser(p).has("lobby.build.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        switchBuildMode(target);
                        p.sendMessage(Data.prefix + "§7The player " + target.getDisplayName() + " §7can build now.");
                    } else {
                        p.sendMessage(Data.prefix + "§cThis player does not exist!");
                    }
                } else {
                    p.sendMessage(Data.no_perms);
                }
            }
        } else {
            p.sendMessage(Data.no_perms);
        }

        return true;
    }

    private void switchBuildMode(Player p) {
        if(!Data.allowBuild.contains(p)) {
            Data.allowBuild.add(p);
            p.getInventory().clear();
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(Data.prefix + "§7You can build now!");
        } else {
            Data.allowBuild.remove(p);
            InventoryHandler.setInventory(p);
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(Data.prefix + "§7You can not build anymore.");
        }
    }
}
