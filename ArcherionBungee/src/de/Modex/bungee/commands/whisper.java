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

public class whisper extends Command implements TabExecutor {

    public whisper() {
        super("w", "bungee.whisper", "whisper", "msg", "dm");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This command can only be run ingame!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length > 1) {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

            if (target != null) {
                if (target != p) {
                    StringBuilder msg = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        msg.append(args[i]).append(" ");
                    }

                    String done = msg.toString().replace("&", "§");

                    target.sendMessage(ChatMessageType.CHAT, new TextComponent("§eFROM §7" + p.getName() + " §8» §3" + done));
                    p.sendMessage(ChatMessageType.CHAT, new TextComponent("§aTO §7" + target.getName() + " §8» §3" + done));

                    Data.replyList.put(p, target);
                    Data.replyList.put(target, p);
                } else {
                    p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§cYou can not whisper to yourself!"));
                }
            } else {
                p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§cThis player is not online!"));
            }
        } else {
            p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§c/whisper <Player> <Message>"));
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        Set<String> matches = new HashSet<>();
        if (args.length == 1) {
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
