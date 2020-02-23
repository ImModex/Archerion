package de.Modex.survival.commands;

import de.Modex.survival.Main;
import de.Modex.survival.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public class home implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if (args.length > 0) {
            File f = new File("plugins/Survival/homes.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(f);

            if (config.contains("homes." + p.getName() + "." + args[0].toLowerCase())) {
                if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
                    if(!Data.teleportTestCooldown.contains(p.getUniqueId())) {
                        if (!Data.teleportCooldown.containsKey(p.getUniqueId())) {
                            p.sendMessage(Data.prefix + "§7You will teleport to your home §e" + args[0].toLowerCase() + "§7 in §a5 §7seconds.");
                            Data.teleportCooldown.put(p.getUniqueId(), p.getLocation());
                            BukkitTask task = Bukkit.getScheduler().runTaskLater(Main.instance, () -> teleport(p, config, args), 20L * 5);
                            Data.teleportTask.put(p.getUniqueId(), task);
                        } else {
                            p.sendMessage(Data.prefix + "§cYou are already teleporting!");
                        }
                    } else {
                        p.sendMessage(Data.prefix + "§cDue to server lag issues teleporting has now a 10min cooldown.");
                    }
                } else {
                    teleport(p, config, args);
                    p.sendMessage(Data.prefix + "§7You have teleported to your home §e" + args[0].toLowerCase() + "§7.");
                }
            } else {
                p.sendMessage(Data.prefix + "§cThis home does not exist!");
            }
        } else {
            p.sendMessage(Data.prefix + "§c/home <home>");
        }

        return true;
    }

    private void teleport(Player p, YamlConfiguration config, String[] args) {
        World world = Bukkit.getWorld(config.getString("homes." + p.getName() + "." + args[0].toLowerCase() + ".world"));
        double x = config.getDouble("homes." + p.getName() + "." + args[0].toLowerCase() + ".x");
        double y = config.getDouble("homes." + p.getName() + "." + args[0].toLowerCase() + ".y");
        double z = config.getDouble("homes." + p.getName() + "." + args[0].toLowerCase() + ".z");
        float yaw = Float.parseFloat(config.getString("homes." + p.getName() + "." + args[0].toLowerCase() + ".yaw"));
        float pitch = Float.parseFloat(config.getString("homes." + p.getName() + "." + args[0].toLowerCase() + ".pitch"));

        Location tploc = new Location(world, x, y, z, yaw, pitch);

        Data.backList.put(p, p.getLocation());
        p.teleport(tploc);
        Data.teleportCooldown.remove(p.getUniqueId());
        Data.teleportTestCooldown.add(p.getUniqueId());

        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.instance, () -> {
            Data.teleportTestCooldown.remove(p.getUniqueId());
        }, 20 * 60 * 15);
    }
}
