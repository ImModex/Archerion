package de.Modex.survival.listener;

import net.minecraft.server.v1_15_R1.AttributeInstance;
import net.minecraft.server.v1_15_R1.AttributeModifier;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;

public class CreatureSpawnListener implements Listener {

    @EventHandler
    public void on(CreatureSpawnEvent e) {
        if (!e.getEntity().getWorld().equals(Bukkit.getWorld("world_the_end"))) {
            if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CURED)) {
                if (e.getEntity() instanceof Monster && !e.getEntityType().equals(EntityType.ENDER_CRYSTAL) && !e.getEntityType().equals(EntityType.ENDER_DRAGON) && !e.getEntityType().equals(EntityType.WITHER_SKULL) && !e.getEntityType().equals(EntityType.WITHER) && !e.getEntityType().equals(EntityType.SKELETON_HORSE) && !e.getEntityType().equals(EntityType.ZOMBIE_HORSE) && !e.getEntityType().equals(EntityType.HORSE) && !e.getEntityType().equals(EntityType.VILLAGER) && !e.getEntityType().equals(EntityType.ZOMBIE_VILLAGER) && !e.getEntityType().equals(EntityType.MINECART) && !e.getEntityType().equals(EntityType.MINECART_CHEST) && !e.getEntityType().equals(EntityType.MINECART_FURNACE) && !e.getEntityType().equals(EntityType.MINECART_HOPPER) && !e.getEntityType().equals(EntityType.MINECART_MOB_SPAWNER)) {
                    if (e.getEntity().getNearbyEntities(16, 16, 16).size() > 25) {
                        e.setCancelled(true);
                    }
                }
            }
        }

        if (e.getEntityType().equals(EntityType.ENDER_DRAGON)) {
            EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) e.getEntity()).getHandle();
            AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.MAX_HEALTH);

            AttributeModifier modifier = new AttributeModifier("Archerion Enderdragon Health Multiplier", 10, AttributeModifier.Operation.MULTIPLY_BASE);

            attributes.addModifier(modifier);
            nmsEntity.setHealth(nmsEntity.getMaxHealth());
        }
    }
}
