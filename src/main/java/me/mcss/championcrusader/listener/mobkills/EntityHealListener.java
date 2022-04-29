package me.mcss.championcrusader.listener.mobkills;

import org.bukkit.entity.IronGolem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityHealListener implements Listener {

    @EventHandler
    public void onMobHeal(EntityRegainHealthEvent event) {

        if (event.getEntity() instanceof IronGolem) {

            event.setCancelled(true);

        }
    }
}