package me.mcss.championcrusader.listener.plentifularrow;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class ArrowPickup implements Listener {
    // Don't allow players to pick up arrows
    @EventHandler
    public void onArrowPickup(PlayerPickupArrowEvent event) {
        event.setCancelled(true);
    }
}
