package de.Modex.bungee.commands;

import de.Modex.bungee.utils.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.*;

public class ban extends Command implements TabExecutor {

    public ban() {
        super("ban", "bungee.ban");
    }

    /*
     *   /ban <Player> [Time] <Reason>
     */

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 0) {
            String targetName = args[0];

            if(sender instanceof ProxiedPlayer) {
                if(sender.getName().toLowerCase().equals(targetName.toLowerCase())) {
                    sender.sendMessage(new TextComponent(Data.prefix + "§cYou can not ban yourself!"));
                    return;
                }
            }

            OfflinePlayer target = MojangAPI.loadPlayer(targetName);

            if (target != null) {

                if (sender instanceof ProxiedPlayer) {
                    ProxiedPlayer p = (ProxiedPlayer) sender;

                    if (!p.getUniqueId().equals(UUID.fromString("3903a9fc-a85c-45ef-84dd-5515d81e58fd"))) {
                        if (BanManager.getRank(p.getUniqueId()).getHierarchy() <= BanManager.getRank(target.getUUID()).getHierarchy()) {
                            sender.sendMessage(new TextComponent(Data.prefix + "§cYou can not ban someone with equal or higher rank than you!"));
                            return;
                        }
                    }
                }

                String banned_by = null;
                if (sender instanceof ProxiedPlayer)
                    banned_by = ((ProxiedPlayer) sender).getUniqueId().toString();
                else
                    banned_by = "CONSOLE";

                boolean foundUnit = false;
                boolean permanent = true;
                long time = -1;
                long origTime = -1;
                String textUnit = null;
                String unit = null;

                if (args.length > 1) {
                    List<List<String>> units = TimeUnit.getUnitsAsString();
                    if (args[1].substring(0, args[1].length() - 1).matches("-?\\d+")) {
                        args[1] = args[1].toLowerCase();
                        String[] split = args[1].split("[^a-z0-9]+|(?<=[a-z])(?=[0-9])|(?<=[0-9])(?=[a-z])");
                        if (split.length == 2) {
                            time = Long.parseLong(split[0]);
                            unit = split[1];
                        } else {
                            sender.sendMessage(new TextComponent(Data.prefix + "§cInvalid time format!"));
                            return;
                        }

                        for (List<String> _unit : units) {
                            if (_unit.contains(unit.toLowerCase())) {
                                origTime = time;
                                time = time * TimeUnit.getUnit(_unit.get(_unit.indexOf(unit.toLowerCase()))).getToSecond();
                                textUnit = TimeUnit.getUnit(_unit.get(_unit.indexOf(unit.toLowerCase()))).getName();
                                foundUnit = true;
                                break;
                            }
                        }

                        if (!foundUnit) {
                            sender.sendMessage(new TextComponent(Data.prefix + "§cThis time unit does not exist!"));
                            return;
                        }
                    }

                    if (unit != null) {
                        List<String> tmp = new ArrayList<>(Arrays.asList(args));
                        tmp.remove(1);
                        args = tmp.toArray(new String[0]);
                        permanent = false;
                    }
                }

                if (!BanManager.isBanned(target.getUUID())) {
                    StringBuilder reason = new StringBuilder();

                    if (args.length > 1) {
                        reason = new StringBuilder();

                        for (int i = 1; i < args.length; i++) {
                            reason.append(args[i]).append(" ");
                        }
                    } else {
                        reason.append("--");
                    }

                    String banMessage = Data.banMessage.replaceAll("%name%", target.getName()).replaceAll("%reason%", reason.toString()).replaceAll("%staff%", sender.getName());

                    if (permanent) {
                        BanManager.ban(target.getUUID(), reason.toString(), -1, banned_by);
                        for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                            if (staff.hasPermission("bungee.ban.chat")) {
                                staff.sendMessage(ChatMessageType.CHAT, new TextComponent(banMessage.replaceAll("%time%", "PERMANENT")));
                            }
                        }

                        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(banMessage.replaceAll("%time%", "PERMANENT")));
                    } else {
                        BanManager.ban(target.getUUID(), reason.toString(), time, banned_by);
                        for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                            if (staff.hasPermission("bungee.ban.chat")) {
                                staff.sendMessage(ChatMessageType.CHAT, new TextComponent(banMessage.replaceAll("%time%", origTime + " " + textUnit)));
                            }
                        }

                        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(banMessage.replaceAll("%time%", origTime + " " + textUnit)));
                    }

                } else {
                    sender.sendMessage(new TextComponent(Data.prefix + "§cThis player is already banned!"));
                }
            } else {
                sender.sendMessage(new TextComponent(Data.prefix + "§cThis player does not exist!"));
            }
        } else {
            sender.sendMessage(new TextComponent(Data.prefix + "§c/ban <Player> [Time] <Reason>"));
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
