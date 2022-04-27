package me.mcss.championcrusader.command.classx;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classx.LeatherTask;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collection;

public class PaladinCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;

    public PaladinCommand(ChampionCrusader testPlugin) {
        this.testPlugin = testPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player p = (Player) sender;

            if (p.hasPermission("champions.select")) {
//
//                ArrayList<Player> players = new ArrayList(Bukkit.getOnlinePlayers());
//
//                for (Player all : players){
//
//                    if (all.getScoreboardTags().contains("berserker")){
//
//                        all.sendMessage("uhhhhhh... meow?");
//
//
//                    }
//
//                }



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
            p.getScoreboardTags().add("paladin");
            p.getScoreboardTags().remove("mage");
            p.getScoreboardTags().remove("berserker");
            p.getScoreboardTags().remove("ranger");

            // UI
                p.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.AQUA + " Paladin");
            p.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.AQUA + " Paladin","Good Luck",1,50,1);
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

            p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The most armored champion, the Paladin is tough as nails. Receive a 10 second resistance buff every time you get a kill!");

                // Giving Items and Armor
            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
            p.getEquipment().setChestplate(null);
            p.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));

            // Passing Variable For Armor
            BukkitTask leatherTask = new LeatherTask(p).runTask(testPlugin);

            } else {

                p.sendMessage(ChatColor.RED + "You do not have permission to do that - Rapid");

            }
        }

        return true;
    }
}
