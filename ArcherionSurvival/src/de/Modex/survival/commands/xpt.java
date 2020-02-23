package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class xpt
        implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(Data.prefix + "§7You currently have §a" + getPlayerExp(p) + " §7experience points");
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                p.sendMessage(Data.prefix + "§7The player §e" + target.getDisplayName() + " §7currently has §a" + getPlayerExp(target) + " §7experience points.");
            } else {
                p.sendMessage(Data.prefix + "§cThis player does not exist!");
            }
        } else if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            int exp = 0;

            if (target == null) {
                p.sendMessage(Data.prefix + "§cThis player does not exist!");
                return true;
            }

            if (target == p) {
                p.sendMessage(Data.prefix + "§cYou can not trade exp with yourself!");
                return true;
            }

            if (!args[1].equalsIgnoreCase("all")) {
                try {
                    exp = Integer.parseInt(args[1]);
                } catch (Exception e) {
                    p.sendMessage(Data.prefix + "§c/xpt <name> <exp>");
                    return true;
                }
            }

            if (getPlayerExp(p) < exp || args[1].equalsIgnoreCase("all")) {
                changePlayerExp(target, getPlayerExp(p));

                p.sendMessage(Data.prefix + "§7You successfully traded §a" + getPlayerExp(p) + " §7exp to §e" + target.getDisplayName());
                target.sendMessage(Data.prefix + "§7You have been given §a" + getPlayerExp(p) + " §7exp from §e" + p.getDisplayName());
                p.setExp(0);
                p.setLevel(0);
            } else {
                changePlayerExp(target, exp);
                changePlayerExp(p, exp * -1);
                p.sendMessage(Data.prefix + "§7You successfully traded §a" + exp + " §7exp to §e" + target.getDisplayName());
                target.sendMessage(Data.prefix + "§7You have been given §a" + exp + " §7exp from §e" + p.getDisplayName());
            }
        }

        return true;
    }

    private int getExpToLevelUp(int level) {
        if (level <= 15) {
            return 2 * level + 7;
        } else if (level <= 30) {
            return 5 * level - 38;
        } else {
            return 9 * level - 158;
        }
    }

    // Calculate total experience up to a level
    private int getExpAtLevel(int level) {
        if (level <= 16) {
            return (int) (Math.pow(level, 2) + 6 * level);
        } else if (level <= 31) {
            return (int) (2.5 * Math.pow(level, 2) - 40.5 * level + 360.0);
        } else {
            return (int) (4.5 * Math.pow(level, 2) - 162.5 * level + 2220.0);
        }
    }

    // Calculate player's current EXP amount
    private int getPlayerExp(Player player) {
        int exp = 0;
        int level = player.getLevel();

        // Get the amount of XP in past levels
        exp += getExpAtLevel(level);

        // Get amount of XP towards next level
        exp += Math.round(getExpToLevelUp(level) * player.getExp());

        return exp;
    }

    // Give or take EXP
    private int changePlayerExp(Player player, int exp) {
        // Get player's current exp
        int currentExp = getPlayerExp(player);

        // Reset player's current exp to 0
        player.setExp(0);
        player.setLevel(0);

        // Give the player their exp back, with the difference
        int newExp = currentExp + exp;
        player.giveExp(newExp);

        // Return the player's new exp amount
        return newExp;
    }
}

