package me.mcss.championcrusader.task.ghostrespawn;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

public class RespawnClassMechanic extends BukkitRunnable {

    // Local variables
    private final Player player;

    // Register variables to be passed in
    public RespawnClassMechanic(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (player.getScoreboardTags().contains("berserker")) {

            ItemStack blood = new ItemStack(Material.REDSTONE,1);
            ItemMeta bloodmeta = (ItemMeta) blood.getItemMeta();
            bloodmeta.setDisplayName("Rage Ability");
            bloodmeta.setLore(Collections.singletonList("Collect 3 and Click to activate RAGE"));
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
