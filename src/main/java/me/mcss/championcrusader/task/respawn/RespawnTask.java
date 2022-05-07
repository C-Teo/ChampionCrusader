package me.mcss.championcrusader.task.respawn;

import me.mcss.championcrusader.ChampionCrusader;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RespawnTask extends BukkitRunnable {

    // Local variables
    public final ChampionCrusader plugin;
    private final Player player;
    private final Player playerKiller;

    // Register variables to be passed in
    // Main Constructor
    public RespawnTask(ChampionCrusader plugin, Player player, Player playerKiller) {
        this.plugin = plugin;
        this.player = player;
        this.playerKiller = playerKiller;
    }

    /*
    This is the code that when a user is supposed to
    die.
     */

    @Override
    public void run() {
        // Working on this implementation.
        // Volume: 0.0 - 1.0 (Anything higher increases range)
        // Pitch: 0.0 - 1.0 (1.0 is normal pitch)

        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1.0f, 1.0f);
        if (playerKiller != null) { // If the killer was a player

            // Send messages to player in chat
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY + "] "
                    + playerKiller.getDisplayName() + " has slain you!");

            // Send message to killer in chat
            playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "]" + ChatColor.WHITE + " You have slain " + player.getDisplayName());

            // Play sound for killer as well
            playerKiller.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1.0f, 1.0f);
        } else { // Else the killer was an entity

            // Send only message to player
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY + "] " +
                    ChatColor.WHITE + "You've been slain by a monster!");

        }

        // Set player to Spectator, show message on screen
        player.setGameMode(GameMode.SPECTATOR);
        player.sendTitle(ChatColor.RED + "You have died!", null, 20, 20, 20);

        // Set as spectator to the player
        player.setSpectatorTarget(playerKiller);

        // Countdown tasks -> see Countdown for info
        BukkitTask countFive = new CountdownTask(player, ChatColor.RED + "5", 0.2f).runTaskLater(this.plugin, 100L);
        BukkitTask countFour = new CountdownTask(player, ChatColor.RED + "4", 0.4f).runTaskLater(this.plugin, 120);
        BukkitTask countThree = new CountdownTask(player, ChatColor.YELLOW + "3", 0.6f).runTaskLater(this.plugin, 140L);
        BukkitTask countTwo = new CountdownTask(player, ChatColor.YELLOW + "2", 0.8f).runTaskLater(this.plugin, 160L);
        BukkitTask countOne = new CountdownTask(player, ChatColor.GREEN + "1", 1.0f).runTaskLater(this.plugin, 180L);

        // Respawn task -> see AfterCountdown for info
        BukkitTask postCountdownTask = new PostCountdownTask(player, plugin).runTaskLater(this.plugin, 200L);
    }
}
