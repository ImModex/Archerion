package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Config;
import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class maintenance extends Command {

    public maintenance() {
        super("maintenance", "bungee.maintenance");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(!Config.config.contains("maintenance") || !Config.config.getBoolean("maintenance")) {
            Config.config.set("maintenance", true);
            sender.sendMessage(new TextComponent(Data.prefix + "§cMaintenance activated!"));

            for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if(!player.hasPermission("bungee.maintenance.bypass"))
                  player.disconnect(new TextComponent("§cThe server is now in maintenance mode!"));
            }
        } else {
            Config.config.set("maintenance", false);
            sender.sendMessage(new TextComponent(Data.prefix + "§aMaintenance deactivated!"));
        }

        Config.save();
    }
}
