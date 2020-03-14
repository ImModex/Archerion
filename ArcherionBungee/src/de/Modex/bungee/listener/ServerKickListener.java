package de.Modex.bungee.listener;

import de.Modex.bungee.utils.Data;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerKickListener implements Listener {

    @EventHandler
    public void on(ServerKickEvent e) {
        e.setCancelServer(ProxyServer.getInstance().getServerInfo("lobby"));
        StringBuilder msg = new StringBuilder(Data.prefix + "§cYou have been sent back to the lobby! Reason: §e");
        for(BaseComponent component : e.getKickReasonComponent()) {
            msg.append(component.toLegacyText());
        }
        e.getPlayer().sendMessage(ChatMessageType.CHAT, new TextComponent(msg.toString()));
    }
}
