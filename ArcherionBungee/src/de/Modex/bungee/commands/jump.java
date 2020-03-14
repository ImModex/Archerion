package de.Modex.bungee.commands;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class jump extends Command implements TabExecutor {

    public jump() {
        super("jump", "bungee.jump");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            if (!(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(new TextComponent("This command can only be run ingame!"));
                return;
            }
            ProxiedPlayer p = (ProxiedPlayer) sender;
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

            if (target != null) {
                if(target != p) {
                    p.connect(target.getServer().getInfo());
                } else {
                    sender.sendMessage(new TextComponent(Data.prefix + "§cYou can not jump to yourself!"));
                }
            } else {
                sender.sendMessage(new TextComponent(Data.prefix + "§cThis player does not exist!"));
            }
        } else {
            sender.sendMessage(new TextComponent(Data.prefix + "§c/jump <Player>"));
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
