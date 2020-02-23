package de.Modex.lobby.utils;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.File;

public class InventoryHandler {

    public static final String compass = "§aNavigator";
    public static final String navigatorClose = "§cBack";

    public static void setInventory(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS).setName(compass).setLore("Use it to choose the server you want to play on").build());
        p.updateInventory();
    }

    public static void setNavigatorInventory(Player p) {
        p.getInventory().clear();

        File file = new File("plugins//Lobby//Navigator.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        for(String key : cfg.getKeys(false)) {
            if(key.equalsIgnoreCase("Staff") && !PermissionsEx.getUser(p).has("lobby.staff"))
                continue;

            p.getInventory().setItem(cfg.getInt(key + ".Slot") - 1, new ItemBuilder(Material.getMaterial(cfg.getString(key + ".Item"))).setName(key).build());
        }

        p.getInventory().setItem(8, new ItemBuilder(Material.BARRIER).setName(navigatorClose).setLore("Go back to the main inventory").build());
        p.updateInventory();
    }
}
