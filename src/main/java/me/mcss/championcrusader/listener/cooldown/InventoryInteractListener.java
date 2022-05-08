package me.mcss.championcrusader.listener.cooldown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class InventoryInteractListener implements Listener {
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        if (!event.getWhoClicked().getScoreboardTags().contains("STAFF")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onOffhandItem(PlayerSwapHandItemsEvent event) {
        if (!event.getPlayer().getScoreboardTags().contains("STAFF")) {
            event.setCancelled(true);
        }
    }
}
