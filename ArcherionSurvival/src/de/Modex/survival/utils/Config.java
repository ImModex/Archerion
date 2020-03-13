package de.Modex.survival.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private static final File warps = new File("plugins/Survival/warps.yml");
    private static final File homes = new File("plugins/Survival/homes.yml");
    public static final File configFile = new File("plugins/Survival/config.yml");
    private static final File dir = new File("plugins/Survival/");

    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    static {
        if (!dir.exists())
            dir.mkdirs();
        if (!warps.exists()) {
            try {
                warps.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!homes.exists()) {
            try {
                homes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
