package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class ping extends Command implements TabExecutor {

    public ping() {
        super("ping", "bungee.ping");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This command can only be run ingame!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length == 0 || !p.hasPermission("bungee.ping.others")) {
            int ping = p.getPing();
            String color = pingFormatter(ping);

            p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§7Your ping is: " + color + ping + "§7ms"));
        } else {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

            if(target != null) {
                int ping = target.getPing();
                String color = pingFormatter(ping);

                p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + target.getDisplayName() + "§7's ping is: " + color + ping + "§7ms"));
            } else {
                p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§cThis player does not exist!"));
            }
        }
    }

    private String pingFormatter(int ping) {
        if (ping < 50)
            return "§a";
        else if (ping > 50 && ping < 100)
            return "§e";
        else
            return "§c";
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        Set<String> matches = new HashSet<>();
        if (args.length == 1 && sender.hasPermission("bungee.ping.others")) {
            String search = args[0].toLowerCase();
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(search)) {
                    matches.add(player.getName());
                }
            }
        }
        return matches;
    }
}
