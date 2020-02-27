package de.Modex.survival.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CreatureSpawnEvent implements Listener {

    @EventHandler
    public void on(org.bukkit.event.entity.CreatureSpawnEvent e) {
        if(!e.getSpawnReason().equals(org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.CURED)) {
            if(e.getEntity() instanceof Monster && !e.getEntityType().equals(EntityType.ENDER_CRYSTAL) && !e.getEntityType().equals(EntityType.ENDER_DRAGON) && !e.getEntityType().equals(EntityType.WITHER_SKULL) && !e.getEntityType().equals(EntityType.WITHER) && !e.getEntityType().equals(EntityType.SKELETON_HORSE) && !e.getEntityType().equals(EntityType.ZOMBIE_HORSE) && !e.getEntityType().equals(EntityType.HORSE) && !e.getEntityType().equals(EntityType.VILLAGER) && !e.getEntityType().equals(EntityType.ZOMBIE_VILLAGER) && !e.getEntityType().equals(EntityType.MINECART) && !e.getEntityType().equals(EntityType.MINECART_CHEST) && !e.getEntityType().equals(EntityType.MINECART_FURNACE) && !e.getEntityType().equals(EntityType.MINECART_HOPPER) && !e.getEntityType().equals(EntityType.MINECART_MOB_SPAWNER)) {
                if(e.getEntity().getNearbyEntities(16, 16, 16).size() > 25) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
