package de.Modex.bungee.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class Data {

    public static final String prefix = "§8[§3Archerion§8] ";

    public static final String header = "\n§l§3Archerion Network\n§3You are playing on: §b%server%\n";
    public static final String footer = "\n§3Players online: §b%players%\n§3Ping: §b%ping%\n\n§3Discord: §bModex#6969\n";

    public static HashMap<ProxiedPlayer, ProxiedPlayer> replyList = new HashMap<>();
}
