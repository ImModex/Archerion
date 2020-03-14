package de.Modex.bungee.commands;

import de.Modex.bungee.utils.BanManager;
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
import java.util.UUID;

public class kick extends Command implements TabExecutor {

    public kick() {
        super("kick", "bungee.kick");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

            if(sender instanceof ProxiedPlayer) {
                if(sender.getName().toLowerCase().equals(args[0].toLowerCase())) {
                    sender.sendMessage(new TextComponent(Data.prefix + "§cYou can not kick yourself!"));
                    return;
                }
            }

            if (target != null) {

                if (sender instanceof ProxiedPlayer) {
                    ProxiedPlayer p = (ProxiedPlayer) sender;

                    if (!p.getUniqueId().equals(UUID.fromString("3903a9fc-a85c-45ef-84dd-5515d81e58fd"))) {
                        if (BanManager.getRank(p.getUniqueId()).getHierarchy() <= BanManager.getRank(target.getUniqueId()).getHierarchy()) {
                            sender.sendMessage(new TextComponent(Data.prefix + "§cYou can not ban someone with equal or higher rank than you!"));
                            return;
                        }
                    }
                }

                if (args.length == 1) {
                    target.disconnect(new TextComponent(Data.postKickMessage.replaceAll("%reason%", "--")));

                    for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                        if (staff.hasPermission("bungee.kick.chat")) {
                            staff.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.kickMessage.replaceAll("%name%", target.getName()).replaceAll("%reason%", "--").replaceAll("%staff%", sender.getName())));
                        }
                    }

                    ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Data.kickMessage.replaceAll("%name%", target.getName()).replaceAll("%reason%", "--").replaceAll("%staff%", sender.getName())));
                } else {
                    StringBuilder reason = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        reason.append(args[i]).append(" ");
                    }

                    target.disconnect(new TextComponent(Data.postKickMessage.replaceAll("%reason%", reason.toString())));

                    for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                        if (staff.hasPermission("bungee.kick.chat")) {
                            staff.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.kickMessage.replaceAll("%name%", target.getName()).replaceAll("%reason%", reason.toString()).replaceAll("%staff%", sender.getName())));
                        }
                    }

                    ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Data.kickMessage.replaceAll("%name%", target.getName()).replaceAll("%reason%", reason.toString()).replaceAll("%staff%", sender.getName())));
                }
            } else {
                sender.sendMessage(new TextComponent(Data.prefix + "§cThis player is not online!"));
            }
        } else {
            sender.sendMessage(new TextComponent(Data.prefix + "§c/kick <Player> [Reason]"));
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        Set<String> matches = new HashSet<>();
        if (args.length == 1) {
            String search = args[0].toLowerCase();
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(search) && !player.getName().equals(sender.getName())) {
                    matches.add(player.getName());
                }
            }
        }
        return matches;
    }
}
