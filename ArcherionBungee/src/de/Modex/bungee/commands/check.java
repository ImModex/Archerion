package de.Modex.bungee.commands;

import de.Modex.bungee.utils.BanManager;
import de.Modex.bungee.utils.Data;
import de.Modex.bungee.utils.MojangAPI;
import de.Modex.bungee.utils.OfflinePlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class check extends Command {

    public check() {
        super("check", "bungee.check");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 0) {
            OfflinePlayer target = MojangAPI.loadPlayer(args[0]);

            if(target != null) {
                if(BanManager.isBanned(target.getUUID())) {
                    sender.sendMessage(new TextComponent(Data.banMessage.replaceAll("%name%", target.getName()).replaceAll("%reason%", BanManager.getReason(target.getUUID())).replaceAll("%time%", BanManager.getRemaining(target.getUUID())).replaceAll("%staff%", BanManager.getBannedBy(target.getUUID()))));
                } else {
                    sender.sendMessage(new TextComponent(Data.prefix + "§7This player is not banned."));
                }
            } else {
                sender.sendMessage(new TextComponent(Data.prefix + "§cThis player does not exist!"));
            }
        } else {
            sender.sendMessage(new TextComponent(Data.prefix + "§c/check <Player>"));
        }
    }
}
