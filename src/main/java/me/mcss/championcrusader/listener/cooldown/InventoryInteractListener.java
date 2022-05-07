package me.mcss.championcrusader.listener.cooldown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryInteractListener implements Listener {
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {
        if (!event.getWhoClicked().getScoreboardTags().contains("STAFF")) {
            event.setCancelled(true);
        }
    }
}
