package me.mcss.championcrusader.command.teams;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class showTeamsCommand implements CommandExecutor {

    private HashMap<String,String> playerToTeam;
    private HashMap<String,String> playerToClass;

    public showTeamsCommand(HashMap<String,String> playerToTeam, HashMap<String,String> playerToClass) {
        this.playerToTeam = playerToTeam;
        this.playerToClass = playerToClass;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            // Cast the sender into a player
            Player user = (Player) sender;

            // Send the Header
            user.sendMessage(ChatColor.GRAY + "-=<{[" + ChatColor.YELLOW + " Champion" + ChatColor.GOLD + " Crusader"
                    + ChatColor.WHITE + " Registered Players " + ChatColor.GRAY + "]}>=-");
            user.sendMessage(ChatColor.GOLD + "========================================");

            // Print every player with their team and class if they have a class
            for (String player : playerToTeam.keySet()) {
                if (playerToClass.containsKey(player)) {
                    user.sendMessage("N: " + player + ChatColor.GREEN + " >>" + ChatColor.WHITE + " T: "
                            + playerToTeam.get(player) + ChatColor.GREEN + " >>" + ChatColor.WHITE
                            + " C: " + playerToClass.get(player));
                } else {
                    user.sendMessage(player + ChatColor.GREEN + " >> " + ChatColor.WHITE + playerToTeam.get(player));
                }
            }

            // Send the Footer
            user.sendMessage(ChatColor.GOLD + "========================================");
        } else {

            // Might change to be runnable in console as well
            System.out.println("Please use this command on the server!");

        }
        return true;
    }
}
