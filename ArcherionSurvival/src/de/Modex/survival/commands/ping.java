package de.Modex.survival.commands;

import de.Modex.survival.utils.Data;
import net.minecraft.server.v1_15_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (args.length == 0) {
            CraftPlayer cp = (CraftPlayer) p;
            EntityPlayer ep = cp.getHandle();
            int ping = ep.ping;
            String color = "";

            if (ping < 50)
                color = "§a";
            else if (ping > 50 && ping < 100)
                color = "§e";
            else
                color = "§c";

            p.sendMessage(Data.prefix + "§7Your ping is: " + color + ping + "§7ms");
        } else if (args.length == 1) {
            if(PermissionsEx.getUser(p).has("ping.others")) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    CraftPlayer cp = (CraftPlayer) target;
                    EntityPlayer ep = cp.getHandle();
                    int ping = ep.ping;
                    String color = "";

                    if (ping < 50)
                        color = "§a";
                    else if (ping > 50 && ping < 100)
                        color = "§e";
                    else
                        color = "§c";

                    p.sendMessage(Data.prefix + target.getDisplayName() + "§7's ping is: " + color + ping + "§7ms");
                } else {
                    p.sendMessage("§cThis player does not exist.");
                }
            } else {
                p.sendMessage(Data.no_perms);
            }
        }


        return true;
    }
}
