package de.Modex.survival.listener;

import de.Modex.survival.events.SpawnerBreakEvent;
import de.Modex.survival.utils.Data;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerBreakListener implements Listener {

    @EventHandler
    public void onBreak(SpawnerBreakEvent e) {
        CreatureSpawner cs = (CreatureSpawner) e.getSpawner().getState();

        ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
        BlockStateMeta meta = (BlockStateMeta) spawner.getItemMeta();
        CreatureSpawner css = (CreatureSpawner) meta.getBlockState();

        css.setSpawnedType(cs.getSpawnedType());
        meta.setBlockState(css);
        spawner.setItemMeta(meta);

        e.getSpawner().getLocation().getWorld().dropItemNaturally(e.getSpawner().getLocation(), spawner);
        ItemStack item = null;

        switch (cs.getSpawnedType()) {
            case ZOMBIE: item = new ItemStack(Material.ZOMBIE_SPAWN_EGG); break;
            case SKELETON: item = new ItemStack(Material.SKELETON_SPAWN_EGG); break;
            case SPIDER: item = new ItemStack(Material.SPIDER_SPAWN_EGG); break;
            case CAVE_SPIDER: item = new ItemStack(Material.CAVE_SPIDER_SPAWN_EGG); break;
            case SILVERFISH: item = new ItemStack(Material.SILVERFISH_SPAWN_EGG); break;
            case BLAZE: item = new ItemStack(Material.BLAZE_SPAWN_EGG); break;
        }

        if(item != null)
             e.getSpawner().getLocation().getWorld().dropItemNaturally(e.getSpawner().getLocation(), item);

        e.getBreaker().sendMessage(Data.prefix + "§7Set down your new spawner and right click it with the spawn egg to change the type.");
    }
}
