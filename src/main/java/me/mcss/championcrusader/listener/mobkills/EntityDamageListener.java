package me.mcss.championcrusader.listener.mobkills;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.mobkills.HealthMobRespawn;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitTask;

public class EntityDamageListener implements Listener {

    ChampionCrusader plugin;

    public EntityDamageListener(ChampionCrusader plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobHurt(EntityDamageByEntityEvent event) {

        // Checks if the entity killed is a Cow
        if (event.getEntity() instanceof Cow) {

            // Checks if the cow is killed
            if (((Cow) event.getEntity()).getHealth() <= event.getFinalDamage()) {

                // Checks if the killer is a player
                if (event.getDamager() instanceof Player) {

                    // Saves the player as playerkiller
                    Player playerKiller = (Player) event.getDamager();

                    // Graphical User Interface
                    playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" +
                            ChatColor.GRAY + "] " + ChatColor.BLUE
                            + "You killed a Mob! Have some heals!");
                    playerKiller.playSound(playerKiller.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                    // Healing the killer
                    if (playerKiller.getHealth() + 6 > 20.0) {
                        playerKiller.setHealth(20.0);
                    } else {
                        playerKiller.setHealth(playerKiller.getHealth() + 6);
                    }

                    // Checks if the killer is an arrow
                } else if (event.getDamager() instanceof Arrow) {

                    // Saves the arrow
                    Arrow arrow = (Arrow) event.getDamager();

                    // If the Shooter is a player
                    if (arrow.getShooter() instanceof Player) {

                        // Saves the player as playerkiller
                        Player playerKiller = (Player) arrow.getShooter();

                        // Graphical User Interface
                        playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" +
                                ChatColor.GRAY + "] " + ChatColor.BLUE
                                + "You killed a Mob! Have some heals!");
                        playerKiller.playSound(playerKiller.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                        // Healing the killer
                        if (playerKiller.getHealth() + 6 > 20.0) {
                            playerKiller.setHealth(20.0);
                        } else {
                            playerKiller.setHealth(playerKiller.getHealth() + 6);
                        }

                    }
                    // Checks if the killer is a potion
                } else if (event.getDamager() instanceof ThrownPotion) {

                    // Saves the potion
                    ThrownPotion potion = (ThrownPotion) event.getDamager();

                    // If the shooter is a player
                    if (potion.getShooter() instanceof Player) {

                        // Saves the player as playerkiller
                        Player playerKiller = (Player) potion.getShooter();

                        // Graphical User Interface
                        playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" +
                                ChatColor.GRAY + "] " + ChatColor.BLUE
                                + "You killed a Mob! Have some heals!");
                        playerKiller.playSound(playerKiller.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                        // Healing the killer
                        if (playerKiller.getHealth() + 6 > 20.0) {
                            playerKiller.setHealth(20.0);
                        } else {
                            playerKiller.setHealth(playerKiller.getHealth() + 6);
                        }
                    }
                }
                BukkitTask respawnTask = new HealthMobRespawn(event.getEntityType(),
                        event.getEntity().getLocation()).runTaskLater(this.plugin,300);
            }
        } else if (event.getEntity() instanceof Pig) {
            // Checks if the pig is killed
            if (((Pig) event.getEntity()).getHealth() <= event.getFinalDamage()) {

                // Checks if the killer is a player
                if (event.getDamager() instanceof Player) {

                    // Saves the player as playerkiller
                    Player playerKiller = (Player) event.getDamager();

                    // Graphical User Interface
                    playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" +
                            ChatColor.GRAY + "] " + ChatColor.BLUE
                            + "You killed a Mob! Have some heals!");
                    playerKiller.playSound(playerKiller.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                    // Healing the killer
                    if (playerKiller.getHealth() + 4 > 20.0) {
                        playerKiller.setHealth(20.0);
                    } else {
                        playerKiller.setHealth(playerKiller.getHealth() + 4);
                    }

                    // Checks if the killer is an arrow
                } else if (event.getDamager() instanceof Arrow) {

                    // Saves the arrow
                    Arrow arrow = (Arrow) event.getDamager();

                    // If the Shooter is a player
                    if (arrow.getShooter() instanceof Player) {

                        // Saves the player as playerkiller
                        Player playerKiller = (Player) arrow.getShooter();

                        // Graphical User Interface
                        playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" +
                                ChatColor.GRAY + "] " + ChatColor.BLUE
                                + "You killed a Mob! Have some heals!");
                        playerKiller.playSound(playerKiller.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                        // Healing the killer
                        if (playerKiller.getHealth() + 4 > 20.0) {
                            playerKiller.setHealth(20.0);
                        } else {
                            playerKiller.setHealth(playerKiller.getHealth() + 4);
                        }
                    }
                    // Checks if the killer is a potion
                } else if (event.getDamager() instanceof ThrownPotion) {

                    // Saves the potion
                    ThrownPotion potion = (ThrownPotion) event.getDamager();

                    // If the shooter is a player
                    if (potion.getShooter() instanceof Player) {

                        // Saves the player as playerkiller
                        Player playerKiller = (Player) potion.getShooter();

                        // Graphical User Interface
                        playerKiller.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" +
                                ChatColor.GRAY + "] " + ChatColor.BLUE
                                + "You killed a Mob! Have some heals!");
                        playerKiller.playSound(playerKiller.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

                        // Healing the killer
                        if (playerKiller.getHealth() + 4 > 20.0) {
                            playerKiller.setHealth(20.0);
                        } else {
                            playerKiller.setHealth(playerKiller.getHealth() + 4);
                        }
                    }
                }
                BukkitTask respawnTask = new HealthMobRespawn(event.getEntityType(),
                        event.getEntity().getLocation()).runTaskLater(this.plugin, 300);
            }
        } else if (event.getEntity() instanceof IronGolem) {
            // Checks if entity is an Iron Golem
            if (event.getDamager() instanceof Player) {
                // Saves player
                Player player = (Player) event.getDamager();

                // Checks what Iron Golem it is
                if (event.getEntity().getName().contains("RED BUCKET")) {
                    // Checks what team tag the player has
                    if (player.getScoreboardTags().contains("RED")) {
                        // Cancels the damage if they are the same
                        event.setCancelled(true);
                    }
                    // Repeats for all the other teams
                } else if (event.getEntity().getName().contains("ORANGE BUCKET")) {
                    if (player.getScoreboardTags().contains("YELLOW")) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity().getName().contains("YELLOW BUCKET")) {
                    if (player.getScoreboardTags().contains("YELLOW")) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity().getName().contains("GREEN BUCKET")) {
                    if (player.getScoreboardTags().contains("GREEN")) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity().getName().contains("CYAN BUCKET")) {
                    if (player.getScoreboardTags().contains("CYAN")) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity().getName().contains("BLUE BUCKET")) {
                    if (player.getScoreboardTags().contains("BLUE")) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity().getName().contains("PURPLE BUCKET")) {
                    if (player.getScoreboardTags().contains("PURPLE")) {
                        event.setCancelled(true);
                    }
                } else if (event.getEntity().getName().contains("PINK BUCKET")) {
                    if (player.getScoreboardTags().contains("PINK")) {
                        event.setCancelled(true);
                    }
                }
                IronGolem boss = (IronGolem) event.getEntity();

                if (boss.getHealth() - event.getFinalDamage() <= 0) {
                    // TODO This needs to be changed later
                    if (boss.getScoreboardTags().contains("redBucket") || boss.getScoreboardTags().contains("blueBucket")) {
                        plugin.getGameRunning().put("A1",false);
                    }
                }

            } else {
                event.setCancelled(true);
            }
        }
    }
}

