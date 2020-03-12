package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Replaced with network wide reply command in ArcherionBungee
 */
@Deprecated
public class reply implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage(Data.prefix + "§c/reply <text>");
            return true;
        }

        if (Data.replyList.containsKey(p)) {
            Player target = Data.replyList.get(p);
            StringBuilder msg = new StringBuilder();

            for (String arg : args) {
                msg.append(arg + " ");
            }

            p.sendMessage("§aTO » " + target.getDisplayName() + " §8» §a" + msg);
            target.sendMessage("§eFROM » " + p.getDisplayName() + " §8» §a" + msg);
            target.playSound(target.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
        } else {
            p.sendMessage(Data.prefix + "§cYou did not whisper recently!");
        }

        return true;
    }
}
