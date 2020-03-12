package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class whisper extends Command {

    public whisper() {
        super("whisper", "bungee.whisper", "w", "msg", "dm");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This command can only be run ingame!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length > 2) {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

            if (target != null) {
                if (target != p) {
                    StringBuilder msg = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        msg.append(args[i]).append(" ");
                    }

                    p.sendMessage(ChatMessageType.CHAT, new TextComponent("§EFROM " + target.getName() + " §8» §a" + msg));
                    target.sendMessage(ChatMessageType.CHAT, new TextComponent("§aTO " + target.getName() + " §8» §a" + msg));

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
}
