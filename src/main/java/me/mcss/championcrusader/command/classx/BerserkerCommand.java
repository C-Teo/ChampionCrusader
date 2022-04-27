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
    public BerserkerCommand(ChampionCrusader testPlugin) { this.testPlugin = testPlugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // If commander user is a player

            Player player = (Player) sender; // Player Variable

            if (player.hasPermission("champions.select")) { // Check for permission

                // Resetting Player health and Inventory
                player.setHealth(20);
                player.getInventory().clear();
                player.getEquipment().clear();

                // Clear each potion effect
                for (PotionEffect effect : player.getActivePotionEffects()) { player.removePotionEffect(effect.getType()); }

                // Set player armor to nothing
                player.getEquipment().setHelmet(null);
                player.getEquipment().setChestplate(null);
                player.getEquipment().setLeggings(null);
                player.getEquipment().setBoots(null);

                // Giving and Removing Tags
                player.getScoreboardTags().add("berserker");
                player.getScoreboardTags().remove("mage");
                player.getScoreboardTags().remove("paladin");
                player.getScoreboardTags().remove("ranger");

                // Graphical User Interface
                player.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.RED + " Berserker");
                player.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.RED + " Berserker",
                        "Good Luck",1,50,1);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

                player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The Berserker is a powerful melee combatant. " +
                        "Collect Essence of Rage on kills, save up enough and you can activate RAGE by right clicking! " +
                        "More you save the better the buffs!");

                // Passing Variable
                BukkitTask leatherTask = new LeatherTask(player).runTask(testPlugin);

                // Giving Items
                player.getInventory().addItem(new ItemStack(Material.STONE_AXE));

                // Giving Redstone (Rage Mechanic)
                ItemStack rage = new ItemStack(Material.REDSTONE,1);
                ItemMeta ragemeta = (ItemMeta)rage.getItemMeta();

                ArrayList<String> redLore = new ArrayList<String>(); // Organize lore lines
                redLore.add("Collect 2 and Click to activate RAGE!");
                redLore.add("4 for SUPER RAGE! 6 for ULTRA RAGE");
                ragemeta.setLore(redLore);

                // Display
                ragemeta.setDisplayName("Essence Of Rage");
                rage.setItemMeta(ragemeta);

                // Giving Potions
                player.getInventory().addItem(rage);

            } else {

                // If the Sender is not a player
                player.sendMessage(ChatColor.RED + "You have not discovered this Rapid69 Technology.");

            }

        }
        return true; // Always end
    }
}
