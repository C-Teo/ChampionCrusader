package me.mcss.championcrusader.task.mobkills;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthMobRespawn extends BukkitRunnable {

    private EntityType entity;
    private Location location;

    public HealthMobRespawn(EntityType entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    @Override
    public void run() {
        location.getWorld().spawnEntity(location,entity);
    }
}
