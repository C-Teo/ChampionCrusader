package me.mcss.championcrusader.task.ghostrespawn;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

public class RespawnClassTask extends BukkitRunnable {

    private Player player;

    public RespawnClassTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (player.getScoreboardTags().contains("ranger")){

            player.getInventory().clear();

            // Giving Items
            player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
            player.getInventory().addItem(new ItemStack(Material.BOW));
            player.getInventory().addItem(new ItemStack(Material.ARROW,4));

            // Custom Arrows
            // Slowness Arrows
            ItemStack slowness = new ItemStack(Material.TIPPED_ARROW,2);
            PotionMeta slownessmeta = (PotionMeta) slowness.getItemMeta();
            slownessmeta.setBasePotionData(new PotionData(PotionType.SLOWNESS));
            slowness.setItemMeta(slownessmeta);
            // Weakness Arrows
            ItemStack weakness = new ItemStack(Material.TIPPED_ARROW,2);
            PotionMeta weaknessmeta = (PotionMeta) weakness.getItemMeta();
            weaknessmeta.setBasePotionData(new PotionData(PotionType.WEAKNESS));
            weakness.setItemMeta(weaknessmeta);


            // Giving players Custom Arrows
            player.getInventory().addItem(new ItemStack(slowness));
            player.getInventory().addItem(new ItemStack(weakness));

        } else if (player.getScoreboardTags().contains("berserker")) {

            player.getInventory().clear();

            // Giving Items
            player.getInventory().addItem(new ItemStack(Material.STONE_AXE));

            // Giving Redstone
            ItemStack blood = new ItemStack(Material.REDSTONE,1);
            ItemMeta bloodmeta = (ItemMeta)blood.getItemMeta();
            bloodmeta.setDisplayName("Rage Ability");
            bloodmeta.setLore(Collections.singletonList("Collect 3 and Click to activate RAGE"));
            blood.setItemMeta(bloodmeta);

            // Giving Potions
            player.getInventory().addItem(blood);
        }
    }
}
