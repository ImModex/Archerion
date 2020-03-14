package de.Modex.bungee.utils;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final static File dir = new File("plugins/ArcherionBungee/");
    private final static File file = new File("plugins/ArcherionBungee/maintenance.yml");
    private final static File mysqlFile = new File("plugins/ArcherionBungee/mysql.yml");
    public static Configuration config;
    public static Configuration mysqlConfig;


    static {
        boolean firstTime = false;

        if (!dir.exists())
            dir.mkdirs();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!mysqlFile.exists()) {
            try {
                mysqlFile.createNewFile();
                firstTime = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            mysqlConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(mysqlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (firstTime || !mysqlConfig.contains("username")) {
            mysqlConfig.set("username", "root");
            mysqlConfig.set("password", "password");
            mysqlConfig.set("database", "database");
            mysqlConfig.set("host", "localhost");
            mysqlConfig.set("port", "3306");

            saveMySQL();
        }
    }

    public static void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMySQL() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(mysqlConfig, mysqlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
