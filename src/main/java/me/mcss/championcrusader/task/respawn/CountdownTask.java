package me.mcss.championcrusader.task.respawn;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTask extends BukkitRunnable {

    // Local variables
    private final Player player;
    private final String message;
    private final float pitch;

    // Register variables to be passed in
    public CountdownTask(Player player, String message, float pitch) {
        this.player = player;
        this.message = message;
        this.pitch = pitch;
    }

    @Override
    // Just send the player a message with the String passed in
    public void run() {
        player.sendTitle(message,null,0,19,0);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO,1.0f,pitch);
    }

}
