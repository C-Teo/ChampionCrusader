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


public class MageCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;

    public MageCommand(ChampionCrusader testPlugin) {
        this.testPlugin = testPlugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

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
                p.getScoreboardTags().add("mage");
                p.getScoreboardTags().remove("berserker");
                p.getScoreboardTags().remove("paladin");
                p.getScoreboardTags().remove("ranger");

                // UI
                p.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.LIGHT_PURPLE + " Mage");
                p.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.LIGHT_PURPLE + " Mage","Good Luck",1,50,1);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

                p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The Mage uses splash potions on their target. They have Regen, Instant Health, Poison, and Harming potions. Unlimited uses but on a 10 second cooldown for each potion!");


                // Passing Variable
                BukkitTask leatherTask = new LeatherTask(p).runTask(testPlugin);

                // Giving Items

                p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));

                // Creating Potions
                    // Regen Potions
                ItemStack regen = new ItemStack(Material.SPLASH_POTION);
                PotionMeta regenmeta = (PotionMeta) regen.getItemMeta();
                regenmeta.setDisplayName("Splash Potion of Regeneration");
                regenmeta.setColor(Color.PURPLE);
                regenmeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 0), true);
                regen.setItemMeta(regenmeta);
                    // Instant Health Potions
                ItemStack health = new ItemStack(Material.SPLASH_POTION);
                PotionMeta healthmeta = (PotionMeta) health.getItemMeta();
                healthmeta.setDisplayName("Splash Potion of Instant Healing");
                healthmeta.setColor(Color.RED);
                healthmeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
                health.setItemMeta(healthmeta);
                    // Poison Potions
                ItemStack poison = new ItemStack(Material.SPLASH_POTION);
                PotionMeta poisonmeta = (PotionMeta) poison.getItemMeta();
                poisonmeta.setDisplayName("Splash Potion of Poison");
                poisonmeta.setColor(Color.GREEN);
                poisonmeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 200, 0), true);
                poison.setItemMeta(poisonmeta);
                    // Harming Potions
                ItemStack harm = new ItemStack(Material.SPLASH_POTION);
                PotionMeta harmmeta = (PotionMeta) harm.getItemMeta();
                harmmeta.setDisplayName("Splash Potion of Harming");
                harmmeta.setColor(Color.BLACK);
                harmmeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
                harm.setItemMeta(harmmeta);

                // Giving Players the Potions
                p.getInventory().addItem(new ItemStack(regen));
                p.getInventory().addItem(new ItemStack(health));
                p.getInventory().addItem(new ItemStack(poison));
                p.getInventory().addItem(new ItemStack(harm));
            } else {

                p.sendMessage(ChatColor.RED + "You do not have permission to do that - Rapid");

            }

        }
        return true;
    }
}
