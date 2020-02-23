package de.Modex.survival.commands;

import de.Modex.survival.Main;
import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class back implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (Data.backList.containsKey(p)) {
            if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
                if(!Data.teleportTestCooldown.contains(p.getUniqueId())) {
                    if (!Data.teleportCooldown.containsKey(p.getUniqueId())) {
                        p.sendMessage(Data.prefix + "§7You will be sent back to your previous location in §a5 §7seconds.");
                        Data.teleportCooldown.put(p.getUniqueId(), p.getLocation());
                        BukkitTask task = Bukkit.getScheduler().runTaskLater(Main.instance, () -> teleport(p), 20L * 5);
                        Data.teleportTask.put(p.getUniqueId(), task);
                    } else {
                        p.sendMessage(Data.prefix + "§cYou are already teleporting!");
                    }
                } else {
                    p.sendMessage(Data.prefix + "§cDue to server lag issues teleporting has now a 10min cooldown.");
                }
            } else {
                teleport(p);
                p.sendMessage(Data.prefix + "§7You have been sent to your previous location.");
            }
        } else {
            p.sendMessage(Data.prefix + "§cYou did not warp recently!");
        }

        return true;
    }

    void teleport(Player p) {
        Location tploc = Data.backList.get(p);
        Data.backList.remove(p);
        Data.backList.put(p, p.getLocation());
        p.teleport(tploc);
        Data.teleportCooldown.remove(p.getUniqueId());
        Data.teleportTestCooldown.add(p.getUniqueId());

        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.instance, () -> {
            Data.teleportTestCooldown.remove(p.getUniqueId());
        }, 20 * 60 * 15);
    }
}
