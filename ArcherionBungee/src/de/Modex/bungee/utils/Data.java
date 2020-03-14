package de.Modex.bungee.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class Data {

    public static final String prefix = "§8[§3Archerion§8] ";
    public static final String staffChatPrefix = "§8[§5Staff§8] ";

    public static final String header = "\n§l§3Archerion Network\n§3You are playing on: §b%server%\n";
    public static final String footer = "\n§3Players online: §b%players%\n§3Ping: §b%ping%\n\n§3Discord: §bModex#6969\n";

    public static final String banMessage = "§8]§7=========[ §cArcherion §4Ban §7]=========§8[\n§7Player: §e%name%\n§7Reason: §e%reason%\n§7Time: §e%time%\n§7Banned by: §e%staff%\n§8]§7=========[ §cArcherion §4Ban §7]=========§8[";
    public static final String unbanMessage = "§8]§7=========[ §cArcherion §4Ban §7]=========§8[\n§7Player: §e%name%\n§7Unbanned by: §e%staff%\n§8]§7=========[ §cArcherion §4Ban §7]=========§8[";
    public static final String banKickMessage = "§cYou were banned from the network!\n\n§3Reason: §e%reason%\n\n§3Time remaining: §e%remaining%\n\n§3For support please dm §eModex#6969 §3on Discord!";

    public static final String postKickMessage = "§cYou were kicked from the network!\n\n§3Reason: §e%reason%\n\n§3For support please dm §eModex#6969 §3on Discord!";
    public static final String kickMessage = "§8]§7=========[ §cArcherion §eKick §7]=========§8[\n§7Player: §e%name%\n§7Reason: §e%reason%\n§7Kicked by: §e%staff%\n§8]§7=========[ §cArcherion §eKick §7]=========§8[";

    public static HashMap<ProxiedPlayer, ProxiedPlayer> replyList = new HashMap<>();
}
