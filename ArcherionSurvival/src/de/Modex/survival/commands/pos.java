package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pos implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(Data.prefix + "§7Your coordinates » §e" + getEnvironment(p.getWorld()) + " §7| §e" + (int) p.getLocation().getX() + " §7| §e" + (int) p.getLocation().getY() + " §7| §e" + (int) p.getLocation().getZ());
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                p.sendMessage(Data.prefix + "§a" + target.getDisplayName() + "§7's coordinates » §e" + getEnvironment(p.getWorld()) + " §7| §e" + (int) target.getLocation().getX() + " §7| §e" + (int) target.getLocation().getY() + " §7| §e" + (int) target.getLocation().getZ());
            } else {
                p.sendMessage(Data.prefix + "§cThis player does not exist.");
            }
        }
        return true;
    }

    private String getEnvironment(World world) {
        switch (world.getEnvironment()) {
            case NORMAL:
                return "Overworld";
            case NETHER:
                return "Nether";
            case THE_END:
                return "End";
        }

        return null;
    }
}

