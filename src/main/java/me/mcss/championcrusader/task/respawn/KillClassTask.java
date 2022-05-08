package me.mcss.championcrusader.task.respawn;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class KillClassTask extends BukkitRunnable {

    // Local variables
    private final Player player;

    // Register variables to be passed in
    public KillClassTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (player.getScoreboardTags().contains("berserker")) {
            // Giving Redstone
            ItemStack rage = new ItemStack(Material.REDSTONE, 1);
            ItemMeta ragemeta = (ItemMeta) rage.getItemMeta();

            // Set Display and Lore
            ArrayList<String> redLore = new ArrayList<String>(); // Lore list
            redLore.add("Collect 2 and Click to activate RAGE!");
            redLore.add("4 for SUPER RAGE! 6 for ULTRA RAGE");
            ragemeta.setLore(redLore);

            // Display
            ragemeta.setDisplayName(ChatColor.RESET + "Essence Of Rage");
            rage.setItemMeta(ragemeta);

            // Giving Potions
            player.getInventory().addItem(rage);

            // Message the killer they got their rage
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY + "] " +
                    ChatColor.RED + "You have gotten some essence of rage!");

        } else if (player.getScoreboardTags().contains("paladin")) {

            // Heal the player
            if (player.getHealth() + 6 > 20.0) {
                player.setHealth(20.0);
            } else {
                player.setHealth(player.getHealth() + 6);
            }

            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY + "] " +
                    ChatColor.GOLD + "Blessing of Paladin!");
        }
    }
}
