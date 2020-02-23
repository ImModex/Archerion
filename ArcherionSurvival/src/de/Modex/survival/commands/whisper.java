package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class whisper implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (args.length < 2) {
            p.sendMessage(Data.prefix + "§c/whisper <player> <text>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == p) {
            p.sendMessage(Data.prefix + "§cYou can not whisper to yourself!");
            return true;
        }

        if (target != null) {
            StringBuilder msg = new StringBuilder();

            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    msg.append(args[i] + " ");
                }
            }

            p.sendMessage("§aTO » " + target.getDisplayName() + " §8» §a" + msg);
            target.sendMessage("§eFROM » " + p.getDisplayName() + " §8» §a" + msg);
            target.playSound(target.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

            Data.replyList.put(p, target);
            Data.replyList.put(target, p);
        } else {
            p.sendMessage(Data.prefix + "§cThis player does not exist!");
        }

        return true;
    }
}