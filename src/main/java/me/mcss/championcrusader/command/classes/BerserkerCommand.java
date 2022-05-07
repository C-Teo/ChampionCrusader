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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import java.util.ArrayList;
import java.util.HashMap;


public class BerserkerCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;
    private HashMap<String, String> playerToTeam;
    private HashMap<String, String> playerToClass;
    private HashMap<String, Boolean> teamReady;

    // Pass in Plugin, Team Map and Class Map
    public BerserkerCommand(ChampionCrusader plugin) {
        this.testPlugin = plugin;
        this.playerToTeam = plugin.getPlayerToTeam();
        this.playerToClass = plugin.getPlayerToClass();
        this.teamReady = plugin.getTeamReady();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // If commander user is a player

            Player player = (Player) sender; // Player Variable

            if (player.hasPermission("champions.select")) { // Check for permission

                boolean hasTeam = playerToTeam.containsKey(player.getName());

                if (hasTeam && !teamReady.get(playerToTeam.get(player.getName()))) { // Check if player has a team

                    // This piece of code checks if someone on their team is already this class.
                    String playerTeam = playerToTeam.get(player.getName()); // Player team color

                    // Loop through every player that has a class
                    for (String subplayer : playerToClass.keySet()) {

                        // If the player has the same class, same team, and is not the same player
                        // Throw an error
                        if (playerToClass.get(subplayer).equalsIgnoreCase("BERSERKER")
                                && playerToTeam.get(subplayer).equalsIgnoreCase(playerTeam)
                                && !subplayer.equals(player.getName())) {

                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "Someone in your team is already this class!");

                            return true;
                        }
                    }

                    // Unselect the class if the player is already a part of it
                    if (playerToClass.containsKey(player.getName())
                            && playerToClass.get(player.getName()).equalsIgnoreCase("berserker")) {

                        // Remove from Map and send message
                        playerToClass.remove(player.getName());

                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                + "] " + ChatColor.GRAY + "You have unselected this class!");

                        player.getInventory().clear();
                        return true;
                    }

                    // Resetting Player health and Inventory
                    player.setHealth(20);
                    player.getInventory().clear();
                    player.getEquipment().clear();

                    // Clear each potion effect
                    for (PotionEffect effect : player.getActivePotionEffects()) {
                        player.removePotionEffect(effect.getType());
                    }

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
                            "Good Luck", 1, 50, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);

                    // Send a message to notify them they picked Berserker
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.GRAY + "The Berserker is a powerful melee combatant. " +
                            "Collect Essence of Rage on kills, save up enough and you can activate RAGE by right clicking! " +
                            "More you save the better the buffs!");

                    // Passing Variable for coloring players armor
                    BukkitTask leatherTask = new LeatherTask(player).runTask(testPlugin);

                    // Giving Items
                    player.getInventory().addItem(new ItemStack(Material.STONE_AXE));

                    // Giving Redstone (Rage Mechanic)
                    ItemStack rage = new ItemStack(Material.REDSTONE, 1);
                    ItemMeta ragemeta = (ItemMeta) rage.getItemMeta();

                    // Organize and set the lore lines
                    ArrayList<String> redLore = new ArrayList<String>();
                    redLore.add("Collect 2 and Click to activate RAGE!");
                    redLore.add("4 for SUPER RAGE! 6 for ULTRA RAGE");
                    ragemeta.setLore(redLore);

                    // Display
                    ragemeta.setDisplayName("Essence Of Rage");
                    rage.setItemMeta(ragemeta);

                    // Giving Potions
                    player.getInventory().addItem(rage);

                    // Locked Bar
                    for (int i = 2; i < 9; i++) {
                        player.getInventory().setItem(i,new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
                    }

                    // Add the player to the Class Map with this class
                    playerToClass.put(player.getName(),"berserker");

                // Player is not part of a team yet
                } else {

                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "Your team is already ready or you are not part of a team!");

                }
            // Player has no permission to use this command
            } else {

                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                        + "] " + ChatColor.DARK_RED + "You have not discovered this Rapid69 Technology.");

            }
        }
        return true; // Always end
    }
}
