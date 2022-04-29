package me.mcss.championcrusader.listener.mobkills;

import org.bukkit.entity.IronGolem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;


public class PotionEffectListener implements Listener {

    @EventHandler
    public void onPotionEffectEvent(EntityPotionEffectEvent event){

        if (event.getEntity() instanceof IronGolem){

            event.setCancelled(true);

        }
    }
}
