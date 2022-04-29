package me.mcss.championcrusader.listener.ghostrespawn;

import me.mcss.championcrusader.task.ghostrespawn.*;
import me.mcss.championcrusader.ChampionCrusader;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitTask;

public class PlayerDamageListener implements Listener { // When player gets damaged

    private final ChampionCrusader plugin; // Space to register plugin

    // Register the plugin as a variable
    public PlayerDamageListener(ChampionCrusader plugin) {
        this.plugin = plugin;
    }

    /*
    Runs everytime a player takes damage to check
    if they are supposed to die.
     */

    @EventHandler // Handle the Entity Damaged event
    public void onPlayerDamage(EntityDamageByEntityEvent event) {

        Player player;          // Player
        Player playerKiller;    // Person who killed said player

        // If the entity that has just taken damage is a player
        if (event.getEntity() instanceof Player) {

            player = (Player) event.getEntity(); // Save player

            // if the damage is enough to kill the player
            if (player.getHealth() <= event.getFinalDamage()) {

                // Check if the person who killed the player is also a player
                if (event.getDamager() instanceof Player) {

                    // Save player killer
                    playerKiller = (Player) event.getDamager();

                    // Cancel event and run respawn mechanic task
                    event.setCancelled(true);
                    BukkitTask respawnTask = new RespawnTask(this.plugin, player, playerKiller).runTask(this.plugin);
                    BukkitTask killClassTask = new KillClassTask(playerKiller).runTask(this.plugin);

                // Check if the person who killed the player is an arrow
                } else if (event.getDamager() instanceof Arrow) {
                    // Save the arrow by casting
                    Arrow arrow = (Arrow) event.getDamager();

                    // If the arrow was shot by a player
                    if (arrow.getShooter() instanceof Player) {

                        // Save player killer
                        playerKiller = (Player) arrow.getShooter();

                        // Cancel event and run respawn mechanic task
                        event.setCancelled(true);
                        BukkitTask respawnTask = new RespawnTask(this.plugin, player, playerKiller).runTask(this.plugin);

                    // The player was shot by a mob or other entity
                    } else {

                        // Still cancel event and run respawn mechanic task -> (SEE: null input for playerKiller)
                        event.setCancelled(true);
                        BukkitTask respawnTask = new RespawnTask(this.plugin, player, null).runTask(this.plugin);
                    }
                // The player was killed by a mob or other entity
                } else {

                    // Still cancel event and run respawn mechanic task -> (SEE: null input for playerKiller)
                    event.setCancelled(true);
                    BukkitTask respawnTask = new RespawnTask(this.plugin, player, null).runTask(this.plugin);
                }
            }
        }
    }
}