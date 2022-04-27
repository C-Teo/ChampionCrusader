package me.mcss.championcrusader.task.ghostrespawn;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;

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
            ItemStack blood = new ItemStack(Material.REDSTONE,1);
            ItemMeta bloodmeta = (ItemMeta)blood.getItemMeta();
            bloodmeta.setDisplayName("Essence Of Rage");
            ArrayList<String> redLore = new ArrayList<String>();
            redLore.add("Collect 2 and Click to activate RAGE!");
            redLore.add("4 for SUPER RAGE! 6 for ULTRA RAGE");
            bloodmeta.setLore(redLore);
            bloodmeta.setDisplayName("Essence Of Rage");
            blood.setItemMeta(bloodmeta);
            blood.setItemMeta(bloodmeta);
            player.getInventory().addItem(blood);
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY + "] " +
                    ChatColor.RED + "You have gotten some essence of rage!");
        } else if (player.getScoreboardTags().contains("paladin")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,100,0));
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY + "] " +
                    ChatColor.GOLD + "Blessing of Paladin!");
        }
    }
}
