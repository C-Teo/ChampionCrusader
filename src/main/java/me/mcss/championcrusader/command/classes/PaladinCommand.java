package me.mcss.championcrusader.command.classes;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classes.LeatherTask;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import java.util.HashMap;

public class PaladinCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;
    private HashMap<String, String> playerToTeam;
    private HashMap<String, String> playerToClass;
    private HashMap<String, Boolean> teamReady;

    // Pass in Plugin, Team Map and Class Map
    public PaladinCommand(ChampionCrusader plugin) {
        this.testPlugin = plugin;
        this.playerToTeam = plugin.getPlayerToTeam();
        this.playerToClass = plugin.getPlayerToClass();
        this.teamReady = plugin.getTeamReady();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("champions.select")) { // Check for permission

                boolean hasTeam = playerToTeam.containsKey(player.getName());

                if (hasTeam && !teamReady.get(playerToTeam.get(player.getName()))) { // Check if player has a team

                    // This piece of code checks if someone on their team is already this class.
                    String playerTeam = playerToTeam.get(player.getName()); // Player team color

                    // Loop through every player that has a class
                    for (String subplayer : playerToClass.keySet()) {

                        // If the player has the same class, same team, and is not the same player
                        // Throw an error
                        if (playerToClass.get(subplayer).equalsIgnoreCase("paladin")
                                && playerToTeam.get(subplayer).equalsIgnoreCase(playerTeam)
                                && !subplayer.equals(player.getName())) {

                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "Someone in your team is already this class!");

                            return true;
                        }
                    }

                    // Unselect the class if the player is already a part of it
                    if (playerToClass.containsKey(player.getName())
                            && playerToClass.get(player.getName()).equalsIgnoreCase("paladin")) {

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
                    for (PotionEffect effect : player.getActivePotionEffects()) {
                        player.removePotionEffect(effect.getType());
                    }

                    // Set player equipped armor empty
                    player.getEquipment().setHelmet(null);
                    player.getEquipment().setChestplate(null);
                    player.getEquipment().setLeggings(null);
                    player.getEquipment().setBoots(null);

                    // Giving and Removing Tags
                    player.getScoreboardTags().add("paladin");
                    player.getScoreboardTags().remove("mage");
                    player.getScoreboardTags().remove("berserker");
                    player.getScoreboardTags().remove("ranger");

                    // Graphical User Interface
                    player.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.AQUA + " Paladin");
                    player.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.AQUA + " Paladin", "Good Luck", 1, 50, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

                    player.sendMessage(ChatColor.GRAY + "" + "The most armored champion," +
                            " the Paladin is tough as nails. Receive a 10 second resistance buff every time you get a kill!");

                    // Giving Items and Armor
                    player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                    player.getEquipment().setChestplate(null);
                    player.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));


                    // Locked Bar
                    // Locked Bar
                    for (int i = 1; i < 9; i++) {
                        player.getInventory().setItem(i,new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
                    }

                    // Passing Variable
                    BukkitTask leatherTask = new LeatherTask(player).runTask(testPlugin);

                    // Add the player to the Class Map with this class
                    playerToClass.put(player.getName(),"paladin");

                // Player is not part of a team yet
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
