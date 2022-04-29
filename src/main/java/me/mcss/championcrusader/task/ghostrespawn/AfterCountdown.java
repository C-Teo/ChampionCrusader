package me.mcss.championcrusader.task.ghostrespawn;

import me.mcss.championcrusader.ChampionCrusader;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AfterCountdown extends BukkitRunnable {

    // Local variables
    private final Player player;
    private final ChampionCrusader plugin;

    // Register variables to be passed in
    public AfterCountdown(Player player, ChampionCrusader plugin) {
        this.player = player;
        this.plugin = plugin;
    }

    /*
    This is the code that runs after the countdown
    to respawn the user.
     */
    @Override
    public void run() {
        // Set user to adventure mode
        player.setGameMode(GameMode.ADVENTURE);

        Location location; // Will be used in a bit

        // Check which tag the user has for team spawn
        // Location cords for team is got from the config
        if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_ONE_FIRST"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_ONE_FIRST_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_ONE_FIRST_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_ONE_FIRST_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_ONE_SECOND"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_ONE_SECOND_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_ONE_SECOND_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_ONE_SECOND_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_TWO_FIRST"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_TWO_FIRST_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_TWO_FIRST_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_TWO_FIRST_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_TWO_SECOND"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_TWO_SECOND_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_TWO_SECOND_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_TWO_SECOND_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_THREE_FIRST"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_THREE_FIRST_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_THREE_FIRST_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_THREE_FIRST_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_THREE_SECOND"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_THREE_SECOND_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_THREE_SECOND_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_THREE_SECOND_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_FOUR_FIRST"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_FOUR_FIRST_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_FOUR_FIRST_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_FOUR_FIRST_LOC").get(2));
        } else if (player.getScoreboardTags().contains(plugin.getConfig().getString("MAP_FOUR_SECOND"))) {
            location = new Location(player.getWorld(),
                    plugin.getConfig().getIntegerList("MAP_FOUR_SECOND_LOC").get(0),
                    plugin.getConfig().getIntegerList("MAP_FOUR_SECOND_LOC").get(1),
                    plugin.getConfig().getIntegerList("MAP_FOUR_SECOND_LOC").get(2));
        } else { // If the user has no tag respawn them to spawn
            location = new Location(player.getWorld(),0,0,0);
        }

        player.teleport(location); // Teleport
        player.setHealth(20d); // Reset health
        player.sendTitle(ChatColor.AQUA + "GO!",null,0,20,0); // Send display message
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME,1.0f,1.0f); // Play sound
    }
}

