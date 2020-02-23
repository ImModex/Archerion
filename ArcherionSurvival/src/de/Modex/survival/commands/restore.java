package de.Modex.survival.commands;

import de.Modex.survival.Main;
import de.Modex.survival.utils.Data;
import de.Modex.survival.utils.InventoryWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class restore implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("This command can only be run ingame!");
            return true;
        }
        Player p = (Player) sender;

        if(PermissionsEx.getUser(p).has("survival.restore")) {
            if(args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);

                // TODO: Backup list uses player who executes command instead of target player from args[0]

                if(target != null) {
                    if(args.length > 1) {
                        int toLoad = Integer.parseInt(args[1]);
                        try {
                            if (restoreInventory(target, toLoad) == -1) {
                                p.sendMessage(Data.prefix + "§cThis inventory ID does not exist!");
                            } else {
                                p.sendMessage(Data.prefix + "§7Successfully restored inventory for §e" + target.getName());
                                target.sendMessage(Data.prefix + "§7Your inventory has been restored by " + p.getDisplayName());
                            }
                        } catch (Exception e) {
                            p.sendMessage(Data.prefix + "§cThis player has no saved inventories yet!");
                        }
                    } else {
                        try {
                            p.sendMessage("§8[§dBackups §8- §dList§8]");
                            for (InventoryWrapper pair : listInventories(target)) {
                                p.sendMessage("§e" + pair.getId() + " §8» §7" + pair.getPath());
                            }
                        } catch (Exception e) {
                            p.sendMessage(Data.prefix + "§cThis player has no saved inventories yet!");
                        }
                    }
                } else {
                    p.sendMessage(Data.prefix + "§cThis player does not exist!");
                }
            } else {
                p.sendMessage(Data.prefix + "§c/restore <Player> <id>");
            }
        } else {
            p.sendMessage(Data.no_perms);
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private int restoreInventory(Player p, int id) throws Exception {

        if (listInventories(p).size() < id)
            return -1;
        if (listInventories(p).get(id) == null)
            return -1;

        File f = new File("plugins/Survival/deaths/" + p.getName() + "/", listInventories(p).get(id).getPath());
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
        changePlayerExp(p, (int) c.get("inventory.exp"));

        return 1;
    }

    private List<InventoryWrapper> listInventories(Player p) throws Exception {
        FileFilter filter = File::isFile;
        File directory = new File("plugins/Survival/deaths/" + p.getName() + "/");

        if(!directory.exists())
            throw new Exception("empty");

        File[] inventoryList = directory.listFiles(filter);
        Arrays.sort(inventoryList, Comparator.comparingLong(File::lastModified));
        List<InventoryWrapper> inventories = new ArrayList<>();

        int i = 0;

        for (File f : inventoryList) {
            inventories.add(new InventoryWrapper(i++, f.getName()));
        }

        return inventories;
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
