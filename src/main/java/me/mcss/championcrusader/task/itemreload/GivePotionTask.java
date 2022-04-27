package me.mcss.championcrusader.task.itemreload;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GivePotionTask extends BukkitRunnable {

    // Local Variables
    public final Player player;
    public final ItemStack potion;

    // Main Constructor
    public GivePotionTask(Player player, ItemStack potion) {
        this.player = player;
        this.potion = potion;
    }

    /*
    Task for giving the user the potion they threw
    X amount of time later.
     */
    @Override
    public void run() {
        // If the player has the mage tag
        if (player.getScoreboardTags().contains("mage")) {

            // Give them the potion, sound, and message
            player.getInventory().addItem(potion);
            player.playSound(player.getLocation(), Sound.ENTITY_WITCH_DRINK,0.5f,1.0f);
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Cooldown" + ChatColor.GRAY + "] " + ChatColor.BLUE
                    + " A potion has refreshed!");
        }
    }
}

