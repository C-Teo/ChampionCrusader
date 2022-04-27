package me.mcss.championcrusader.task.classx;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class LeatherTask extends BukkitRunnable {

    private final Player player;

    public LeatherTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        // Check player Team
        if (player.getScoreboardTags().contains("RED")) {
            changeItemColor(Color.RED);
        } else if (player.getScoreboardTags().contains("GREEN")) {
            changeItemColor(Color.GREEN);
        } else if (player.getScoreboardTags().contains("BLUE")){
            changeItemColor(Color.BLUE);
        } else if (player.getScoreboardTags().contains("YELLOW")) {
            changeItemColor(Color.YELLOW);
        } else if (player.getScoreboardTags().contains("ORANGE")) {
            changeItemColor(Color.ORANGE);
        } else if (player.getScoreboardTags().contains("CYAN")) {
            changeItemColor(Color.AQUA);
        } else if (player.getScoreboardTags().contains("PURPLE")) {
            changeItemColor(Color.PURPLE);
        } else if (player.getScoreboardTags().contains("PINK")) {
            changeItemColor(Color.FUCHSIA);
        }

        // Check if player is a Paladin
        if (player.getScoreboardTags().contains("paladin")) {
            // Iron Chest Plate instead
            ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
            player.getEquipment().setChestplate(chestplate);
        }
    }

    public void changeItemColor(Color color) {
        // Change color of the Helmet
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta HMeta = (LeatherArmorMeta) helmet.getItemMeta();
        HMeta.setColor(color);
        helmet.setItemMeta(HMeta);

        // Change color of the Chestplate
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta CMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        CMeta.setColor(color);
        chestplate.setItemMeta(CMeta);

        // Change color of the Leggings
        ItemStack legging = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta LMeta = (LeatherArmorMeta) legging.getItemMeta();
        LMeta.setColor(color);
        legging.setItemMeta(LMeta);

        // Change color of the Boots
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta BMeta = (LeatherArmorMeta) boots.getItemMeta();
        BMeta.setColor(color);
        boots.setItemMeta(BMeta);

        // Change all equipped pieces
        player.getEquipment().setHelmet(helmet);
        player.getEquipment().setChestplate(chestplate);
        player.getEquipment().setLeggings(legging);
        player.getEquipment().setBoots(boots);
    }
}
