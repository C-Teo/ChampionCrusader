package me.mcss.championcrusader.command.teams;

// Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class teamCommand implements CommandExecutor {

    private final HashMap<String,String> playerToTeam; // Variable

    // Pass the Map of all players and their Team Color
    public teamCommand(HashMap<String,String> playerToTeam) {
        this.playerToTeam = playerToTeam;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // Check if sender is player, this might be changed later

            Player player = (Player) sender; // Cast

            if (player.hasPermission("cc")) { // Check for player permission

                if (args.length == 3) { // Check for 3 arguments

                    // If the command is setteam [/cc setteam]
                    if (args[0].equalsIgnoreCase("setteam")) {

                        // Retrieve the player from the second argument [/cc setteam player]
                        Player target = Bukkit.getPlayerExact(args[1]);

                        if (target != null) { // Check if we found a player

                            // Check if we can find that team color and run setTeam method
                            if (args[2].equalsIgnoreCase("red")) {

                                setTeam("RED",player,target);

                            } else if (args[2].equalsIgnoreCase("blue")) {

                                setTeam("BLUE",player,target);

                            } else if (args[2].equalsIgnoreCase("green")) {

                                setTeam("GREEN",player,target);

                            } else if (args[2].equalsIgnoreCase("yellow")) {

                                setTeam("YELLOW",player,target);

                            } else if (args[2].equalsIgnoreCase("purple")) {

                                setTeam("PURPLE",player,target);

                            } else if (args[2].equalsIgnoreCase("pink")) {

                                setTeam("PINK",player,target);

                            } else if (args[2].equalsIgnoreCase("orange")) {

                                setTeam("ORANGE",player,target);

                            } else if (args[2].equalsIgnoreCase("cyan")) {

                                setTeam("CYAN",player,target);

                            // If the team color provided does not exist
                            } else {

                                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                        + "] " + ChatColor.RED + "That team color " + args[2] + " does not exist!");

                            }
                        // If the player was not found throw error
                        } else {

                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.RED + "The player " + args[1] + " is not online or does not exist!");

                        }
                    // If the first argument subcommand is invalid
                    } else {

                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                + "] " + ChatColor.RED + "Invalid sub-command!");

                    }
                // If an invalid amount of arguments is given
                } else {

                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.RED + "Not enough arguments!");

                }
            // If the player has no permission to run this command
            } else {

                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                        + "] " + ChatColor.RED + "Invalid permission!");

            }
        }
        return true;
    }

    /**
     Provided with a Team color, Sender, and Target this method
     will set the Target's tag to the team color and add them
     to the LP role and Team Map.
     @param team String that contains the color
     @param sender This is the sender of the command
     @param target This is the person who is being targeted
     */
    public void setTeam(String team, Player sender, Player target) {

        // Set player's tag
        target.getScoreboardTags().clear();
        target.getScoreboardTags().add(team);

        // Set the player to the LP role
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "lp user " + target.getName() + " parent set " + team.toLowerCase() + "-team");

        playerToTeam.put(target.getName(),team); // Put them into the Map

        // Send a message to both the sender and the target
        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                + "] " + ChatColor.WHITE + "The player " + target.getName()
                + " has their team set to " + team);

        target.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                + "] " + ChatColor.WHITE + "Your team is now " + team);
    }
}
