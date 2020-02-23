package de.Modex.lobby.listener;

import de.Modex.lobby.Main;
import de.Modex.lobby.utils.Data;
import de.Modex.lobby.utils.InventoryHandler;
import de.Modex.lobby.utils.Mode;
import de.Modex.lobby.utils.Navigator;
import net.minecraft.server.v1_15_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_15_R1.ParticleParam;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlayerInteractListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        CraftPlayer cp = (CraftPlayer) p;

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack item = p.getInventory().getItemInMainHand();

            if(item.getType().equals(Material.AIR))
                return;

            ItemMeta meta = item.getItemMeta();
            String name = meta.getDisplayName();

            File file = new File("plugins//Lobby//Navigator.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

            if(name.equals(InventoryHandler.compass)) {
                InventoryHandler.setNavigatorInventory(p);
            } else if(name.equals(InventoryHandler.navigatorClose)) {
                InventoryHandler.setInventory(p);
            } else {
                try {
                    Mode m = Navigator.getMode(p, name);

                    InventoryHandler.setInventory(p);

                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3.0F, 1.0F);

                    if(!isOnline(m.getPort())) {
                        p.sendMessage(Data.prefix + "§cThe server §e" + m.getServer() + " §cis offline.");
                        return;
                    }

                    if(!name.equalsIgnoreCase("Spawn"))
                        sendPlayerToServer(p, m.getServer());
                    else
                        p.teleport(m.getLocation());
                } catch (Exception e1) {
                    p.sendMessage(Data.prefix + "§cError! Please send the administrator the following code: §emodeNotExistException:" + name);
                }
            }
        }
    }

    private void sendPlayerToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            player.sendMessage(Data.prefix + "§cThere was an error connecting to the server!");
            return;
        }

        player.sendPluginMessage(Main.instance, "BungeeCord", b.toByteArray());
    }

    private boolean isOnline(int port) {
        boolean online = false;
        try {
            Socket s = new Socket("modex.dev", port);
            s.close();
            online = true;
        } catch (Exception ignored){}

        return online;
    }
}
