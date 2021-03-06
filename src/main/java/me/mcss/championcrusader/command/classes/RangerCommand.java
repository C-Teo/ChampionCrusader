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
    private HashMap<String, Boolean> teamReady;

    // Pass in Plugin, Team Map and Class Map
    public RangerCommand(ChampionCrusader plugin) {
        this.plugin = plugin;
        this.playerToTeam = plugin.getPlayerToTeam();
        this.playerToClass = plugin.getPlayerToClass();
        this.teamReady = plugin.getTeamReady();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // If commander user is player

            Player player = (Player) sender; // Player variable

            if (player.hasPermission("champions.select")) {

                boolean hasTeam = playerToTeam.containsKey(player.getName());

                if (hasTeam && !teamReady.get(playerToTeam.get(player.getName()))) { // Check if player has a team

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

                            player.getInventory().clear();
                            return true;
                        }
                    }

                    // Unselect the class if the player is already a part of it
                    if (playerToClass.containsKey(player.getName())
                            && playerToClass.get(player.getName()).equalsIgnoreCase("ranger")) {

                        // Remove from Map and send message
                        playerToClass.remove(player.getName());

                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                + "] " + ChatColor.GRAY + "You have unselected this class!");

                        player.getInventory().clear();
                        return true;
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

                    player.sendMessage(ChatColor.GRAY + "The Ranger relies on their quick aim for reliable damage." +
                            " Get your arrows back for unlimited arrows, but ONLY when you hit your target!" +
                            " Make sure not to miss and lose your arrows!");


                    // Passing Variable
                    BukkitTask leatherTask = new LeatherTask(player).runTask(plugin);

                    // Giving Items
                    player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.BOW));
                    player.getInventory().addItem(new ItemStack(Material.ARROW, 8));

                    // Locked Bar
                    for (int i = 3; i < 9; i++) {
                        player.getInventory().setItem(i,new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
                    }

                    // Add the player to the Class Map with this class
                    playerToClass.put(player.getName(),"ranger");

                } else {

                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "Your team is already ready or you are not part of a team!");

                }
            } else {

                // If the player does not have permission
                player.sendMessage(ChatColor.DARK_RED + "You have not discovered this Rapid69 Technology.");

            }
        }
        return true;
    }
}
