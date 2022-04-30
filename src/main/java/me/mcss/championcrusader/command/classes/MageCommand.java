package me.mcss.championcrusader.command.classes;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classes.LeatherTask;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;


public class MageCommand implements CommandExecutor {

    private final ChampionCrusader testPlugin;
    private HashMap<String, String> playerToTeam;
    private HashMap<String, String> playerToClass;

    // Pass in Plugin, Team Map and Class Map
    public MageCommand(ChampionCrusader plugin, HashMap<String,String> playerToTeam,
                       HashMap<String,String> playerToClass) {
        this.testPlugin = plugin;
        this.playerToTeam = playerToTeam;
        this.playerToClass = playerToClass;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){ // If commander user is player

            Player player = (Player) sender; // Player variable

            if (player.hasPermission("champions.select")) { // Check for permission

                if (playerToTeam.containsKey(player.getName())) { // Check if player has a team

                    // This piece of code checks if someone on their team is already this class.
                    String playerTeam = playerToTeam.get(player.getName()); // Player team color

                    // Loop through every player that has a class
                    for (String subplayer : playerToClass.keySet()) {

                        // If the player has the same class, same team, and is not the same player
                        // Throw an error
                        if (playerToClass.get(subplayer).equalsIgnoreCase("mage")
                                && playerToTeam.get(subplayer).equalsIgnoreCase(playerTeam)
                                && !subplayer.equals(player.getName())) {

                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "Someone in your team is already this class!");

                            return true;
                        }
                    }

                    // Unselect the class if the player is already a part of it
                    if (playerToClass.containsKey(player.getName())
                            && playerToClass.get(player.getName()).equalsIgnoreCase("mage")) {

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
                    player.getScoreboardTags().add("mage");
                    player.getScoreboardTags().remove("berserker");
                    player.getScoreboardTags().remove("paladin");
                    player.getScoreboardTags().remove("ranger");

                    // Graphical User Interface
                    player.sendMessage(ChatColor.GREEN + "You have picked" + ChatColor.LIGHT_PURPLE + " Mage");
                    player.sendTitle(ChatColor.GREEN + "You have picked" + ChatColor.LIGHT_PURPLE + " Mage",
                            "Good Luck", 1, 50, 1);
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

                    // Add the player to the Class Map with this class
                    playerToClass.put(player.getName(),"mage");

                // Player is not part of a team yet
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
