package de.Modex.survival.utils;

import de.Modex.survival.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import java.util.Random;

public class EndfightTimers {


    private static World end = Bukkit.getServer().getWorld("world_the_end");

    /*
    public static void startFireballTask() {
        if (end != null) {
            if ((Data.fireBallTask != null && Data.fireBallTask.isCancelled()) || Data.fireBallTask == null) {
                Data.fireBallTask = Bukkit.getScheduler().runTaskTimer(Main.instance, () -> {
                    if (!end.getEntitiesByClass(EnderDragon.class).isEmpty()) {
                        for (Player endPlayers : end.getPlayers()) {
                            Location playerLoc = endPlayers.getLocation();
                            Location fireballLoc = playerLoc.add(0, 40, 0);

                            Fireball fireball = end.spawn(fireballLoc, Fireball.class);
                            fireball.setIsIncendiary(true);
                            fireball.setYield(0);

                            fireball.setDirection(playerLoc.toVector().subtract(fireballLoc.toVector()));
                        }
                    } else {
                        if (!Data.fireBallTask.isCancelled())
                            Data.fireBallTask.cancel();
                    }
                }, 0, 20 * 15);
            }
        }
    }
    */

    public static void startLightningTask() {
        if (end != null) {
            if ((Data.lightningTask != null && Data.lightningTask.isCancelled()) || Data.lightningTask == null) {
                Data.lightningTask = Bukkit.getScheduler().runTaskTimer(Main.instance, () -> {
                    if (!end.getEntitiesByClass(EnderDragon.class).isEmpty()) {

                        for (Player endPlayers : end.getPlayers()) {
                            endPlayers.getWorld().strikeLightning(endPlayers.getLocation());
                            endPlayers.setFireTicks(20 * 3);
                            endPlayers.damage(2);
                        }
                    } else {
                        if (!Data.lightningTask.isCancelled())
                            Data.lightningTask.cancel();
                    }
                }, 0, 20 * 10);
            }
        }
    }

    public static void startMobSpawnTask() {
        if (end != null) {
            if ((Data.mobSpawnTask != null && Data.mobSpawnTask.isCancelled()) || Data.mobSpawnTask == null) {
                Data.mobSpawnTask = Bukkit.getScheduler().runTaskTimer(Main.instance, () -> {
                    if (!end.getEntitiesByClass(EnderDragon.class).isEmpty()) {
                        for (Player endPlayers : end.getPlayers()) {
                            Location loc = endPlayers.getLocation();

                            for (int i = 0; i < new Random().nextInt(7) + 1; i++) {
                                int mob = new Random().nextInt(7) + 1;
                                loc.add(new Random().nextInt(30 - 3 + 1) + 3, 0, new Random().nextInt(30 - 3 + 1) + 3);
                                loc.setY(end.getHighestBlockYAt(loc));

                                switch (mob) {
                                    case 1: {
                                        end.spawnEntity(loc, EntityType.ZOMBIE);
                                        break;
                                    }
                                    case 2: {
                                        end.spawnEntity(loc, EntityType.SKELETON);
                                        break;
                                    }
                                    case 3: {
                                        Creeper c = end.spawn(loc, Creeper.class);
                                        c.setExplosionRadius(0);
                                        break;
                                    }
                                    case 4: {
                                        end.spawnEntity(loc, EntityType.SPIDER);
                                        break;
                                    }
                                    case 5: {
                                        end.spawnEntity(loc, EntityType.ENDERMAN);
                                        break;
                                    }
                                    case 6: {
                                        end.spawnEntity(loc, EntityType.PHANTOM);
                                        break;
                                    }
                                    case 7: {
                                        end.spawnEntity(loc, EntityType.SILVERFISH);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if (!Data.mobSpawnTask.isCancelled())
                            Data.mobSpawnTask.cancel();
                    }
                }, 0, 20 * 15);
            }
        }
    }

    public static void startMobTargetTask() {
        if (end != null) {
            if ((Data.mobTargetTask != null && Data.mobTargetTask.isCancelled()) || Data.mobTargetTask == null) {
                Data.mobTargetTask = Bukkit.getScheduler().runTaskTimer(Main.instance, () -> {
                    if (!end.getEntitiesByClass(EnderDragon.class).isEmpty()) {
                        for(Entity e : end.getEntities()) {
                            if(e instanceof Monster && !e.getType().equals(EntityType.ENDER_DRAGON)) {
                                Monster m = (Monster) e;
                                if(m.getTarget() == null || m.getTarget().getType().equals(EntityType.ENDER_DRAGON)) {
                                    m.setTarget(end.getPlayers().get(new Random().nextInt(end.getPlayers().size())));
                                }
                            }
                        }
                    } else {
                        if (!Data.mobTargetTask.isCancelled())
                            Data.mobTargetTask.cancel();
                    }
                }, 0, 20 * 10);
            }
        }
    }

    public static void stopFireballTask() {
        if (Data.fireBallTask != null && !Data.fireBallTask.isCancelled()) {
            Data.fireBallTask.cancel();
        }
    }

    public static void stopLightningTask() {
        if (Data.lightningTask != null && !Data.lightningTask.isCancelled()) {
            Data.lightningTask.cancel();
        }
    }

    public static void stopMobSpawnTask() {
        if (Data.mobSpawnTask != null && !Data.mobSpawnTask.isCancelled()) {
            Data.mobSpawnTask.cancel();
        }
    }

    public static void stopMobTargetTask() {
        if (Data.mobTargetTask != null && !Data.mobTargetTask.isCancelled()) {
            Data.mobTargetTask.cancel();
        }
    }
}
