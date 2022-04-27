package me.mcss.championcrusader.command.classx;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classx.LeatherTask;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import org.bukkit.scheduler.BukkitTask;

import java.util.Iterator;


public class MageCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;
    public MageCommand(ChampionCrusader testPlugin) { this.testPlugin = testPlugin; }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){ // If commander user is player

            Player player = (Player) sender; // Player variable

            if (player.hasPermission("champions.select")) { // Check for permission

                // Resetting Character and Inventory
                player.setHealth(20);
                player.getInventory().clear();
                player.getEquipment().clear();

                // Reset each potion effect on player
                for (PotionEffect effect : player.getActivePotionEffects()) { player.removePotionEffect(effect.getType()); }

                // Set player equipped armor empty
                player.getEquipment().setHelmet(null);
                player.getEquipment().setChestplate(null);
                player.getEquipment().setLeggings(null);
                player.getEquipment().setBoots(null);

                // Giving and Removing Tags
                player.getScoreboardTags().add("mage");
                player.getScoreboardTags().remove("berserker");
                player.getScoreboardTags().remove("paladin");
                player.getScoreboardTags().remove("ranger");

                // Graphical User Interface
                player.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.LIGHT_PURPLE + " Mage");
                player.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.LIGHT_PURPLE + " Mage",
                        "Good Luck",1,50,1);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);
                player.sendMessage(ChatColor.GRAY + "The Mage uses splash potions on their target. " +
                        "They have Regen, Instant Health, Poison, and Harming potions. Unlimited uses but on a 10 second " +
                        "cooldown for each potion!");


                // Passing Variable
                BukkitTask leatherTask = new LeatherTask(player).runTask(testPlugin);

                // Giving Items
                player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));

                // Creating Potions

                // Regen Potions
                ItemStack regen = new ItemStack(Material.SPLASH_POTION);
                PotionMeta regenmeta = (PotionMeta) regen.getItemMeta();
                regenmeta.setDisplayName(ChatColor.RESET + "Potion of" + ChatColor.LIGHT_PURPLE + " Regeneration");
                regenmeta.setColor(Color.PURPLE);
                regenmeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 0), true);
                regen.setItemMeta(regenmeta);

                // Instant Health Potions
                ItemStack health = new ItemStack(Material.SPLASH_POTION);
                PotionMeta healthmeta = (PotionMeta) health.getItemMeta();
                healthmeta.setDisplayName(ChatColor.RESET + "Potion of" + ChatColor.RED + " Health");
                healthmeta.setColor(Color.RED);
                healthmeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
                health.setItemMeta(healthmeta);

                // Poison Potions
                ItemStack poison = new ItemStack(Material.SPLASH_POTION);
                PotionMeta poisonmeta = (PotionMeta) poison.getItemMeta();
                poisonmeta.setDisplayName(ChatColor.RESET + "Potion of" + ChatColor.GREEN + " Poison");
                poisonmeta.setColor(Color.GREEN);
                poisonmeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 200, 0), true);
                poison.setItemMeta(poisonmeta);

                // Harming Potions
                ItemStack harm = new ItemStack(Material.SPLASH_POTION);
                PotionMeta harmmeta = (PotionMeta) harm.getItemMeta();
                harmmeta.setDisplayName(ChatColor.RESET + "Potion of" + ChatColor.DARK_PURPLE + " Harming");
                harmmeta.setColor(Color.BLACK);
                harmmeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
                harm.setItemMeta(harmmeta);

                // Giving Players the Potions
                player.getInventory().addItem(new ItemStack(regen));
                player.getInventory().addItem(new ItemStack(health));
                player.getInventory().addItem(new ItemStack(poison));
                player.getInventory().addItem(new ItemStack(harm));

            } else {

                // If the player does not have permission
                player.sendMessage(ChatColor.RED + "You have not discovered this Rapid69 Technology.");

            }

        }
        return true;
    }
}
