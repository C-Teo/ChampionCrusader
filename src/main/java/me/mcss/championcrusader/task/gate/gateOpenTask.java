package me.mcss.championcrusader.task.gate;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class gateOpenTask extends BukkitRunnable {

    Player player;

    public gateOpenTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_OPEN,1.0f,1.0f);
    }
}
