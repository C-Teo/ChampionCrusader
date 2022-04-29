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

public class PotionSplash implements Listener { // Check when a potion splashes

    // Local Variable
    public final ChampionCrusader plugin; // Main plugin

    // Main Constructor
    public PotionSplash(ChampionCrusader plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {

        // If person who threw the potion was a player
        if (event.getPotion().getShooter() instanceof Player) {

            // Save player and potion
            Player player = (Player) event.getPotion().getShooter();
            ItemStack potion = event.getPotion().getItem();

            // If potion is REGENERATION
            if (event.getPotion().getEffects().contains
                    (new PotionEffect(PotionEffectType.REGENERATION,300,0))) {

                // Give the potion
                BukkitTask cooldown = new GivePotion(player, potion).runTaskLater(this.plugin,200L);
                player.sendMessage("Regeneration Potion"); // Test Message

            // If potion is POISON
            } else if (event.getPotion().getEffects().contains
                    (new PotionEffect(PotionEffectType.POISON,200,0))) {

                // Give the potion
                BukkitTask cooldown = new GivePotion(player, potion).runTaskLater(this.plugin,200L);
                player.sendMessage("Poison Potion"); // Test Message

            // If potion is HARM
            } else if (event.getPotion().getEffects().contains
                    (new PotionEffect(PotionEffectType.HARM,20,1))) {

                // Give the potion
                BukkitTask cooldown = new GivePotion(player, potion).runTaskLater(this.plugin,200L);
                player.sendMessage("Harm Potion"); // Test Message

            // If potion is HEAL
            } else if (event.getPotion().getEffects().contains
                    (new PotionEffect(PotionEffectType.HEAL,20,1))) {

                // Give the potion
                BukkitTask cooldown = new GivePotion(player, potion).runTaskLater(this.plugin,200L);
                player.sendMessage("Heal Potion"); // Test Message

            // If potion is STRENGTH
            } else if (event.getPotion().getEffects().contains
                    (new PotionEffect(PotionEffectType.INCREASE_DAMAGE,200,0))) {

                // Give the potion
                BukkitTask cooldown = new GivePotion(player, potion).runTaskLater(this.plugin, 200L);
                player.sendMessage("Strength Potion"); // Test Message
            }
        }
    }
}
