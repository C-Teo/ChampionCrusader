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
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import org.bukkit.scheduler.BukkitTask;

public class RangerCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;

    public RangerCommand(ChampionCrusader testPlugin) {
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
            p.getScoreboardTags().add("ranger");
            p.getScoreboardTags().remove("mage");
            p.getScoreboardTags().remove("paladin");
            p.getScoreboardTags().remove("berserker");

            // UI
                p.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.DARK_GREEN + " Ranger");
            p.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.DARK_GREEN + " Ranger","Good Luck",1,50,1);
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

            p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The Ranger relies on their quick aim for reliable damage. Get your arrows back for unlimited arrows, but ONLY when you hit your target! Make sure not to miss and lose your arrows!");


            // Passing Variable
            BukkitTask leatherTask = new LeatherTask(p).runTask(testPlugin);

            // Giving Items
            p.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
            p.getInventory().addItem(new ItemStack(Material.BOW));
            p.getInventory().addItem(new ItemStack(Material.ARROW,4));


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
            p.getInventory().addItem(new ItemStack(slowness));
            p.getInventory().addItem(new ItemStack(weakness));
            } else {

                p.sendMessage(ChatColor.RED + "You do not have permission to do that - Rapid");

            }
        }
        return true;
    }
}
