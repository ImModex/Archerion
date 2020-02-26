package de.Modex.rank;


import de.Modex.rank.listener.JoinQuitListener;
import de.Modex.rank.listener.PlayerChatListener;
import de.Modex.rank.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public class Main extends JavaPlugin {

    public static Main instance;
    public static Scoreboard sb;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);

        scoreboardSetup();

        for (Player p : Bukkit.getOnlinePlayers()) {
            Data.updatePrefix(p);
        }

        System.out.println(Data.prefix + "Plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        System.out.println(Data.prefix + "Plugin has been unloaded!");
    }

    private void scoreboardSetup() {
        sb = Bukkit.getScoreboardManager().getNewScoreboard();

        sb.registerNewTeam("00000Owner");
        sb.registerNewTeam("00001Staff");
        sb.registerNewTeam("00002Friend");
        sb.registerNewTeam("00003Donator");
        sb.registerNewTeam("00004Player");

        sb.getTeam("00000Owner").setPrefix(Data.prefixOwner);
        sb.getTeam("00001Staff").setPrefix(Data.prefixStaff);
        sb.getTeam("00002Friend").setPrefix(Data.prefixFriend);
        sb.getTeam("00003Donator").setPrefix(Data.prefixDonator);
        sb.getTeam("00004Player").setPrefix(Data.prefixPlayer);

        sb.getTeam("00000Owner").setColor(ChatColor.GRAY);
        sb.getTeam("00001Staff").setColor(ChatColor.GRAY);
        sb.getTeam("00002Friend").setColor(ChatColor.GRAY);
        sb.getTeam("00003Donator").setColor(ChatColor.GRAY);
        sb.getTeam("00004Player").setColor(ChatColor.GRAY);
    }
}

