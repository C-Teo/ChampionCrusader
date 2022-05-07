package me.mcss.championcrusader.task.cooldown;

import me.mcss.championcrusader.ChampionCrusader;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class GivePotionTask extends BukkitRunnable {

    // Local Variables
    public final Player player;
    public final ItemStack potion;
    ChampionCrusader plugin;
    private final HashMap<String, String> playerToTeam;
    private final HashMap<String, Boolean> gameRunning;

    // Main Constructor
    public GivePotionTask(Player player, ItemStack potion, ChampionCrusader plugin) {
        this.player = player;
        this.potion = potion;
        this.plugin = plugin;
        this.playerToTeam = plugin.getPlayerToTeam();
        this.gameRunning = plugin.getGameRunning();
    }

    /*
    Task for giving the user the potion they threw
    X amount of time later.
     */
    @Override
    public void run() {
        // If the player has the mage tag
        if (player.getScoreboardTags().contains("mage") && playerToTeam.containsKey(player.getName())) {
            // Check if their game is currently running
            int arena = plugin.getConfig().getIntegerList(playerToTeam.get(player.getName())).get(0);
            if (gameRunning.get("A"+arena)) {
                // Give them the potion, sound, and message
                player.getInventory().addItem(potion);
                player.playSound(player.getLocation(), Sound.ENTITY_WITCH_DRINK, 0.5f, 1.0f);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                        + "] " + ChatColor.WHITE + "A potion has refreshed!");
            }
        }
    }
}

