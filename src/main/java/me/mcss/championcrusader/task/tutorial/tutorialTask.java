package me.mcss.championcrusader.task.tutorial;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class tutorialTask extends BukkitRunnable {

    private int num;
    private Player player;

    public tutorialTask(int num, Player player) {
        this.num = num;
        this.player = player;
    }

    @Override
    public void run() {

        Location location = null;

        if (num == 1) {

            player.setInvisible(true);
            player.setWalkSpeed(0);
            player.setLevel(1);
            player.setExp(0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,600,150));

            // Making Location the Objective coords
            Location objective = new Location(player.getWorld(),-1883,103,165);
            // Teleports people to objective
            player.teleport(objective);

            player.sendMessage(ChatColor.GRAY + "\n[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.GOLD + ChatColor.BOLD + "Objective");
            player.sendMessage(ChatColor.GRAY +
                    "The main purpose of Champion Crusaders is to kill the other team's Iron Golem. " +
                    "To do this you must first capture the point and siege the enemy team's base! " +
                    "Bonus points are also awarded for top killer in the lobby.");
        } else if (num == 2) {

            player.setLevel(2);
            player.setExp(0.2f);

            Location capturePoint = new Location(player.getWorld(),-1888,103,132);
            capturePoint.setYaw(270f);
            player.teleport(capturePoint);

            player.sendMessage(ChatColor.GRAY + "\n[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.GOLD + ChatColor.BOLD + "Capture Point");
            player.sendMessage(ChatColor.GRAY +
                    "The capture point is a simple concept, all you have to do is stand on the enemies " +
                    "point for 30 seconds! It will not speed up if more people are on it but it will " +
                    "save process if you get off! Imagine like battering down the enemies gate.");
        } else if (num == 3) {

            player.setLevel(3);
            player.setExp(0.4f);

            Location classes = new Location(player.getWorld(),-1882,108,214);
            player.teleport(classes);

            player.sendMessage(ChatColor.GRAY + "\n[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.GOLD + ChatColor.BOLD + "Classes");
            player.sendMessage(ChatColor.GRAY +
                    "Classes are also a huge part of Champion Crusader, you will be able to pick from 4 " +
                    "different custom and unique classes in your team's base. These classes are as followed:\n" +
                    ChatColor.GOLD + "\nPaladin" + ChatColor.GRAY + " -> Heals on player kills" +
                    ChatColor.GOLD + "\nBerserker" + ChatColor.GRAY + " -> Gains rage as they kill players to buff themselves." +
                    ChatColor.GOLD + "\nRanger" + ChatColor.GRAY + " -> Start off with minimal arrows, each direct hit will reward the arrow back." +
                    ChatColor.GOLD + "\nMage" + ChatColor.GRAY + " -> 4 Potions that spawn back 10 seconds after being thrown.");
        } else if (num == 4) {

            player.setLevel(4);
            player.setExp(0.6f);

            Location middle = new Location(player.getWorld(),-1872,103,84);
            player.teleport(middle);

            player.sendMessage(ChatColor.GRAY + "\n[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.GOLD + ChatColor.BOLD + "Middle");
            player.sendMessage(ChatColor.GRAY + "The middle is also a crucial point to control in Champion Crusader " +
                    "The middle is sourrounded by livestock which respawn when killed. These animals will award health, " +
                    "4 Hearts for a Cow and 2 Hearts for a Pig! The middle will also have three bosses spawn:\n" +
                    ChatColor.GOLD + "\nRavager" + ChatColor.GRAY + " -> Reward the killing team swiftness!" +
                    ChatColor.GOLD + "\nPillager" + ChatColor.GRAY + " -> Reward the killing team a door repair!" +
                    ChatColor.GOLD + "\nBrute" + ChatColor.GRAY + " -> Reward the killing team strength!" +
                    "\n\nThe Ravager will spawn 3 minutes in, the Pillager 5, and finally the Brute 8.");
        } else if (num == 5) {

            player.setLevel(5);
            player.setExp(0.8f);

            Location tiebreaker = new Location(player.getWorld(),-1883,108,103);
            player.teleport(tiebreaker);

            player.sendMessage(ChatColor.GRAY + "\n[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.GOLD + ChatColor.BOLD + "Tie Game");
            player.sendMessage(ChatColor.GRAY + "Remember to work together, this game has a longer respawn timer " +
                    "so it is very important to not waste the time you have! At 10 minutes both teams will spawn " +
                    "in their base and have their gates shattered. Their Iron Golems will also be set to 1 hp. " +
                    "First team to land a blow wins. At 15 minutes the game is declared a tie.");
        } else if (num == 6){

            player.setLevel(0);
            player.setExp(1.0f);
            player.setInvisible(false);
            player.setWalkSpeed(0.2f);

            player.sendMessage(ChatColor.GRAY + "\n[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.GOLD + ChatColor.BOLD + "Thank you!");
            player.sendMessage(ChatColor.GRAY + "Thank you for reading the tutorial! Goodluck in your game! Here" +
                    " is 200 gems. not really.");

            Location hub = new Location(player.getWorld(),-530,118,481);
            player.teleport(hub);

        }
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0f,1.0f);
    }
}
