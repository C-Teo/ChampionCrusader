package me.mcss.championcrusader.listener.classes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {

    // Prevent player from dropping item
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        event.setCancelled(true);
    }
}
