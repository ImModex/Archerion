package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staff extends Command {

    public staff() {
        super("staff", "bungee.staffchat", "sc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 0) {
            StringBuilder msg = new StringBuilder();

            for(String s : args) {
                msg.append(s).append(" ");
            }

            for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if(staff.hasPermission("bungee.staff"))
                    staff.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.staffChatPrefix + "§e" + sender.getName() + " §7» §6" + msg.toString()));
            }

            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Data.staffChatPrefix + "§e" + sender.getName() + " §7» §6" + msg.toString()));

        } else {
            sender.sendMessage(new TextComponent(Data.prefix + "§c/staff <Text>"));
        }
    }
}
