package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class reply extends Command {

    public reply() {
        super("reply", "bungee.whisper", "r");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This command can only be run ingame!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length > 1) {
            if (Data.replyList.containsKey(p)) {
                ProxiedPlayer target = Data.replyList.get(p);
                StringBuilder msg = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }

                p.sendMessage(ChatMessageType.CHAT, new TextComponent("§EFROM " + target.getName() + " §8» §a" + msg));
                target.sendMessage(ChatMessageType.CHAT, new TextComponent("§aTO " + target.getName() + " §8» §a" + msg));
            } else {
                p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§cYou did not whisper recently!"));
            }
        } else {
            p.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.prefix + "§c/reply <Message>"));
        }
    }
}
