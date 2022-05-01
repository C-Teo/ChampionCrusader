package me.mcss.championcrusader.task.respawn;
import me.mcss.championcrusader.command.teams.teamCommand;
import me.mcss.championcrusader.ChampionCrusader;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PostCountdownTask extends BukkitRunnable {

    // Local variables
    private final Player player;
    private final ChampionCrusader plugin;

    // Register variables to be passed in
    public PostCountdownTask(Player player, ChampionCrusader plugin) {
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

        Location location = null; // Will be used in a bit
        final int DISPLACEMENT = 102;
        final int HEIGHT = 9;

        // Check which tag the user has for team spawn
        // Location cords for team is got from the config
        String team = teamCommand.getTeam(player);

        if (team != null) { // Check if we found a team
            // Get the arena and side linked
            int arena = plugin.getConfig().getIntegerList(team).get(0);
            int side = plugin.getConfig().getIntegerList(team).get(1);

            // If they are on the first side
            if (side == 1) {

                location = new Location(player.getWorld(),
                        plugin.getConfig().getIntegerList("" + arena).get(0),
                        plugin.getConfig().getIntegerList("" + arena).get(1) + HEIGHT,
                        plugin.getConfig().getIntegerList("" + arena).get(2) + DISPLACEMENT);
                location.setYaw(180f);

            // If they are on the second side
            } else if (side == 2) {

                location = new Location(player.getWorld(),
                        plugin.getConfig().getIntegerList("" + arena).get(0),
                        plugin.getConfig().getIntegerList("" + arena).get(1) + HEIGHT,
                        plugin.getConfig().getIntegerList("" + arena).get(2) - DISPLACEMENT);
            }
        } else {
            // Hub Spawn location
            location = new Location(player.getWorld(),-530,115,448);
        }

        player.teleport(location); // Teleport
        player.setHealth(20d); // Reset health
        player.sendTitle(ChatColor.AQUA + "GO!",null,0,20,0); // Send display message
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME,1.0f,1.0f); // Play sound
        BukkitTask respawnClassTask = new RespawnClassTask(player,plugin).runTask(plugin);
    }
}

