package me.mcss.championcrusader.command.classx;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classx.LeatherTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collections;

public class BerserkerCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;

    public BerserkerCommand(ChampionCrusader testPlugin) {this.testPlugin = testPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (p.hasPermission("champions.select")) {

            // Resetting Character and Inventory
            p.setHealth(20);
            p.getInventory().clear();
            p.getEquipment().clear();
            for (PotionEffect effect : p.getActivePotionEffects())
                p.removePotionEffect(effect.getType());

            p.getEquipment().setHelmet(null);
            p.getEquipment().setChestplate(null);
            p.getEquipment().setLeggings(null);
            p.getEquipment().setBoots(null);

            // Giving and Removing Tags
            p.getScoreboardTags().add("berserker");
            p.getScoreboardTags().remove("mage");
            p.getScoreboardTags().remove("paladin");
            p.getScoreboardTags().remove("ranger");

            // UI
            p.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.RED + " Berserker");
            p.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.RED + " Berserker","Good Luck",1,50,1);
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

            p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The Berserker is a powerful melee combatant. Collect Essence of Rage on kills, save up enough and you can activate RAGE by right clicking! More you save the better the buffs!");

            // Passing Variable
            BukkitTask leatherTask = new LeatherTask(p).runTask(testPlugin);

            // Giving Items
            p.getInventory().addItem(new ItemStack(Material.STONE_AXE));

                // Giving Redstone
                ItemStack blood = new ItemStack(Material.REDSTONE,1);
                ItemMeta bloodmeta = (ItemMeta)blood.getItemMeta();

                ArrayList<String> redLore = new ArrayList<String>();
                redLore.add("Collect 2 and Click to activate RAGE!");
                redLore.add("4 for SUPER RAGE! 6 for ULTRA RAGE");
                bloodmeta.setLore(redLore);
                bloodmeta.setDisplayName("Essence Of Rage");
                blood.setItemMeta(bloodmeta);


//            // Giving Redstone
//                ItemStack blood = new ItemStack(Material.REDSTONE,1);
//                ItemMeta bloodmeta = (ItemMeta)blood.getItemMeta();
//                bloodmeta.setDisplayName("Essence Of Rage");
//                bloodmeta.setLore(Collections.singletonList("Collect 2 and Click to activate RAGE!\n4 for Super Rage! 6 for ULTRA RAGE"));
//                blood.setItemMeta(bloodmeta);

            // Giving Potions
                p.getInventory().addItem(blood);

            } else {

                p.sendMessage(ChatColor.RED + "You do not have permission to do that - Rapid");

            }

        }
        return true;
    }
}
