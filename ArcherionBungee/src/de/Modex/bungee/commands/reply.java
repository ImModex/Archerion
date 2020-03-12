package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class reply extends Command{

    public reply() {
        super("r", "bungee.whisper", "reply");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This command can only be run ingame!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length != 0) {
            if (Data.replyList.containsKey(p)) {
                ProxiedPlayer target = Data.replyList.get(p);
                StringBuilder msg = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                String done = msg.toString().replace("&", "§");
                target.sendMessage(ChatMessageType.CHAT, new TextComponent("§eFROM §7" + p.getName() + " §8» §3" + done));
                p.sendMessage(ChatMessageType.CHAT, new TextComponent("§aTO §7" + target.getName() + " §8» §3" + done));
            } else {
                p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§cYou did not whisper recently!"));
            }
        } else {
            p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§c/reply <Message>"));
        }
    }
}
