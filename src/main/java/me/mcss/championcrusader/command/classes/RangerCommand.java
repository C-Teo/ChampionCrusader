package me.mcss.championcrusader.command.classes;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classes.LeatherTask;
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

import java.util.HashMap;

public class RangerCommand implements CommandExecutor {

    private final ChampionCrusader plugin;
    private HashMap<String, String> playerToTeam;
    private HashMap<String, String> playerToClass;

    // Pass in Plugin, Team Map and Class Map
    public RangerCommand(ChampionCrusader plugin, HashMap<String,String> playerToTeam,
                         HashMap<String,String> playerToClass) {
        this.plugin = plugin;
        this.playerToTeam = playerToTeam;
        this.playerToClass = playerToClass;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // If commander user is player

            Player player = (Player) sender; // Player variable

            if (player.hasPermission("champions.select")) {

                if (playerToTeam.containsKey(player.getName())) { // Check if player has a team

                    // This piece of code checks if someone on their team is already this class.
                    String playerTeam = playerToTeam.get(player.getName()); // Player team color

                    // Loop through every player that has a class
                    for (String subplayer : playerToClass.keySet()) {

                        // If the player has the same class, same team, and is not the same player
                        // Throw an error
                        if (playerToClass.get(subplayer).equalsIgnoreCase("ranger")
                                && playerToTeam.get(subplayer).equalsIgnoreCase(playerTeam)
                                && !subplayer.equals(player.getName())) {

                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "Someone in your team is already this class!");

                            return true;
                        }
                    }

                    // Resetting Character and Inventory
                    player.setHealth(20);
                    player.getInventory().clear();
                    player.getEquipment().clear();

                    // Reset each potion effect on player
                    // (player.getActivePotionEffects().clear() <- Might Work)
                    for (PotionEffect effect : player.getActivePotionEffects()) {
                        player.removePotionEffect(effect.getType());
                    }

                    // Set player equipped armor empty
                    player.getEquipment().setHelmet(null);
                    player.getEquipment().setChestplate(null);
                    player.getEquipment().setLeggings(null);
                    player.getEquipment().setBoots(null);

                    // Giving and Removing Tags
                    player.getScoreboardTags().add("ranger");
                    player.getScoreboardTags().remove("mage");
                    player.getScoreboardTags().remove("paladin");
                    player.getScoreboardTags().remove("berserker");

                    // Graphical User Interface
                    player.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.GOLD + " Ranger");
                    player.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.GOLD + " Ranger", "Good Luck", 1, 50, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

                    player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "The Ranger relies on their quick aim for reliable damage. Get your arrows back for unlimited arrows, but ONLY when you hit your target! Make sure not to miss and lose your arrows!");


                    // Passing Variable
                    BukkitTask leatherTask = new LeatherTask(player).runTask(plugin);

                    // Giving Items
                    player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.BOW));
                    player.getInventory().addItem(new ItemStack(Material.ARROW, 4));


                    // Custom Arrows

                    // Slowness Arrows
                    ItemStack slowness = new ItemStack(Material.TIPPED_ARROW, 2);
                    PotionMeta slownessmeta = (PotionMeta) slowness.getItemMeta();
                    slownessmeta.setBasePotionData(new PotionData(PotionType.SLOWNESS));
                    slowness.setItemMeta(slownessmeta);

                    // Weakness Arrows
                    ItemStack weakness = new ItemStack(Material.TIPPED_ARROW, 2);
                    PotionMeta weaknessmeta = (PotionMeta) weakness.getItemMeta();
                    weaknessmeta.setBasePotionData(new PotionData(PotionType.WEAKNESS));
                    weakness.setItemMeta(weaknessmeta);

                    // Giving players Custom Arrows
                    player.getInventory().addItem(new ItemStack(slowness));
                    player.getInventory().addItem(new ItemStack(weakness));

                    // Add the player to the Class Map with this class
                    playerToClass.put(player.getName(),"ranger");

                } else {

                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "You must be a part of a team first!");

                }
            } else {

                // If the player does not have permission
                player.sendMessage(ChatColor.DARK_RED + "You have not discovered this Rapid69 Technology.");

            }
        }
        return true;
    }
}
