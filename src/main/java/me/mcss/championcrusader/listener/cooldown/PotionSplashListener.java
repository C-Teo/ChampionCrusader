package me.mcss.championcrusader.listener.cooldown;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.cooldown.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class PotionSplashListener implements Listener { // Check when a potion splashes

    // Local Variable
    public final ChampionCrusader plugin; // Main plugin
    public final HashMap<String, String> playerToTeam;
    public final HashMap<String, Boolean> gameRunning;

    // Main Constructor
    public PotionSplashListener(ChampionCrusader plugin) {
        this.plugin = plugin;
        this.playerToTeam = plugin.getPlayerToTeam();
        this.gameRunning = plugin.getGameRunning();
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {

        // If person who threw the potion was a player
        if (event.getPotion().getShooter() instanceof Player) {

            // Save player and potion
            Player player = (Player) event.getPotion().getShooter();
            ItemStack potion = event.getPotion().getItem();

            // If the player has a Team
            if (playerToTeam.containsKey(player.getName())) {

                int arena = plugin.getConfig().getIntegerList(plugin.getPlayerToTeam().get(player.getName())).get(0);

                if (gameRunning.get("A"+arena)) {

                    // If potion is REGENERATION
                    if (event.getPotion().getEffects().contains
                            (new PotionEffect(PotionEffectType.REGENERATION, 300, 0))) {

                        // Give the potion
                        BukkitTask cooldown = new GivePotionTask(player, potion, 3, plugin).runTaskLater(this.plugin, 200L);

                        // If potion is POISON
                    } else if (event.getPotion().getEffects().contains
                            (new PotionEffect(PotionEffectType.POISON, 200, 0))) {

                        // Give the potion
                        BukkitTask cooldown = new GivePotionTask(player, potion, 1, plugin).runTaskLater(this.plugin, 200L);

                        // If potion is HARM
                    } else if (event.getPotion().getEffects().contains
                            (new PotionEffect(PotionEffectType.HARM, 20, 1))) {

                        // Give the potion
                        BukkitTask cooldown = new GivePotionTask(player, potion, 2, plugin).runTaskLater(this.plugin, 200L);

                        // If potion is HEAL
                    } else if (event.getPotion().getEffects().contains
                            (new PotionEffect(PotionEffectType.HEAL, 20, 1))) {

                        // Give the potion
                        BukkitTask cooldown = new GivePotionTask(player, potion, 4, plugin).runTaskLater(this.plugin, 200L);
                    }
                }
            }
        }
    }
}
