package de.Modex.creative.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class EventCanceller implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if(!e.getEntityType().equals(EntityType.PLAYER))
            e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPistonChange(BlockPistonEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onRedstoneChange(BlockRedstoneEvent e) {
        e.setNewCurrent(0);
    }

    @EventHandler
    public void onNotePlay(NotePlayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onShootBow(EntityShootBowEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBucketUse(PlayerBucketEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onRecipeDiscover(PlayerRecipeDiscoverEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPortal(PortalCreateEvent e) {
        e.setCancelled(true);
    }
}
