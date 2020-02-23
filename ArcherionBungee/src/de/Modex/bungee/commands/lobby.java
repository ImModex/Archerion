package de.Modex.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class lobby extends Command {

    public lobby() {
        super("lobby", "bungee.lobby", "hub");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This command can only be run ingame!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        ServerInfo target = ProxyServer.getInstance().getServerInfo("lobby");
        p.connect(target);
    }
}
