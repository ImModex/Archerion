package de.Modex.bungee.commands;

import de.Modex.bungee.utils.BanManager;
import de.Modex.bungee.utils.Data;
import de.Modex.bungee.utils.MojangAPI;
import de.Modex.bungee.utils.OfflinePlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class unban extends Command {

    public unban() {
        super("unban", "bungee.unban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 0) {
            OfflinePlayer target = MojangAPI.loadPlayer(args[0]);

            if(target != null) {
                if(BanManager.isBanned(target.getUUID())) {
                    BanManager.unban(target.getUUID());

                    for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                        if (staff.hasPermission("bungee.unban.chat")) {
                            staff.sendMessage(ChatMessageType.CHAT, new TextComponent(Data.unbanMessage.replaceAll("%name%", target.getName()).replaceAll("%staff%", sender.getName())));
                        }
                    }

                    ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Data.unbanMessage.replaceAll("%name%", target.getName()).replaceAll("%staff%", sender.getName())));
                } else {
                    sender.sendMessage(new TextComponent(Data.prefix + "§cThis player is not banned!"));
                }
            } else {
                sender.sendMessage(new TextComponent(Data.prefix + "§cThis player does not exist!"));
            }
        } else {
            sender.sendMessage(new TextComponent(Data.prefix + "§c/unban <Player>"));
        }
    }
}
