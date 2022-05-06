package me.mcss.championcrusader.listener.mobkills;

import org.bukkit.entity.Cow;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnListener implements Listener {
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Cow || event.getEntity() instanceof Pig) {
            event.getEntity().setPersistent(true);
            event.getEntity().setAI(false);
        }
    }
}
