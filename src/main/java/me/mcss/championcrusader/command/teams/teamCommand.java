package me.mcss.championcrusader.command.teams;

// Imports
import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.tutorial.tutorialTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class teamCommand implements CommandExecutor {

    ChampionCrusader plugin;
    private final HashMap<String,String> playerToTeam; // Variable
    private final HashMap<String,String> playerToClass;
    private final HashMap<String, Boolean> teamReady;

    // Pass the Map of all players and their Team Color
    public teamCommand(ChampionCrusader plugin, HashMap<String,String> playerToTeam,
                       HashMap<String,String> playerToClass, HashMap<String, Boolean> teamReady) {
        this.playerToTeam = playerToTeam;
        this.playerToClass = playerToClass;
        this.teamReady = teamReady;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) { // Check if sender is player, this might be changed later

            Player player = (Player) sender; // Cast

            if (player.hasPermission("cc")) { // Check for player permission

                if (args.length >= 1) { // Check for 3 arguments

                    // If the command is setteam [/cc setteam]
                    if (args[0].equalsIgnoreCase("setteam") && args.length == 3) {

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

                                setTeam("CYAN", player, target);

                            } else if (args[2].equalsIgnoreCase("staff")) {

                                setTeam("STAFF",player,target);

                            // If the team color provided does not exist
                            } else {

                                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                        + "] " + ChatColor.DARK_RED + "That team color " + args[2] + " does not exist!");

                            }
                        // If the player was not found throw error
                        } else {

                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "The player " + args[1] + " is not online or does not exist!");

                        }
                    // If the first argument subcommand is invalid
                    } else if (args[0].equalsIgnoreCase("ready") && args.length == 1) {

                        if (teamReady.get(getTeam(player))) {
                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_GREEN + "Your team is already ready!");
                            return true;
                        }

                        // All flags to check
                        boolean berserker = false;
                        boolean mage = false;
                        boolean paladin = false;
                        boolean ranger = false;

                        String team = getTeam(player); // Get the team who ran the command

                        for (String name : playerToTeam.keySet()) {
                            // Check if the player is part of the right team
                            if (playerToTeam.get(name).equalsIgnoreCase(team)) {
                                // Check if the player has a class
                                if (playerToClass.containsKey(name)) {
                                    // Check for the class we need to flip
                                    if (playerToClass.get(name).equalsIgnoreCase("berserker")) {
                                        berserker = true;
                                    } else if (playerToClass.get(name).equalsIgnoreCase("mage")) {
                                        mage = true;
                                    } else if (playerToClass.get(name).equalsIgnoreCase("paladin")) {
                                        paladin = true;
                                    } else if (playerToClass.get(name).equalsIgnoreCase("ranger")) {
                                        ranger = true;
                                    }
                                }
                            }
                        }

                        if (berserker && mage && paladin && ranger) {
                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_GREEN + "Your team is now ready!");
                            teamReady.put(team,true);
                        } else {
                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "Your whole team has not picked a class!");
                        }

                    } else if (args[0].equalsIgnoreCase("tutorial") && args.length == 1) {

                        BukkitTask msgOne = new tutorialTask(1,player).runTask(plugin);
                        BukkitTask msgTwo = new tutorialTask(2,player).runTaskLater(plugin,140);
                        BukkitTask msgThree = new tutorialTask(3,player).runTaskLater(plugin,280);
                        BukkitTask msgFour = new tutorialTask(4,player).runTaskLater(plugin,420);
                        BukkitTask msgFive = new tutorialTask(5,player).runTaskLater(plugin,560);
                        BukkitTask msgSix = new tutorialTask(6,player).runTaskLater(plugin,600);


                    } else if (args[0].equalsIgnoreCase("linkteam") && args.length == 4) {

                        if (args[0].equalsIgnoreCase("linkteam")) {

                            String team = args[1];
                            team = team.toUpperCase();

                            // Disgusting but effective if statement to check a proper team was passed
                            if (team.equalsIgnoreCase("RED") || team.equalsIgnoreCase("BLUE")
                            || team.equalsIgnoreCase("GREEN") || team.equalsIgnoreCase("YELLOW")
                            || team.equalsIgnoreCase("PINK") || team.equalsIgnoreCase("PURPLE")
                            || team.equalsIgnoreCase("CYAN") || team.equalsIgnoreCase("ORANGE")) {

                                String arena = args[2];
                                String side = args[3];

                                if ((arena.equals("1") || arena.equals("2") || arena.equals("3") || arena.equals("4"))
                                        && (side.equals("1") || side.equals("2"))) {
                                    // First Index is Arena and Second Index is Side
//                                    plugin.getConfig().getIntegerList(team).set(0,Integer.parseInt(arena));
//                                    plugin.getConfig().getIntegerList(team).set(1,Integer.parseInt(side));

                                    ArrayList<Integer> pass = new ArrayList<Integer>();
                                    pass.add(Integer.parseInt(arena));
                                    pass.add(Integer.parseInt(side));

                                    plugin.getConfig().set(team,pass);


                                } else {
                                    // Invalid Arena
                                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                            + "] " + ChatColor.DARK_RED + "Invalid Arena");

                                }

                            } else {
                                // Invalid Team
                                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                        + "] " + ChatColor.DARK_RED + "Invalid Team!");

                            }
                        } else {
                            // Improper Command
                            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                    + "] " + ChatColor.DARK_RED + "Invalid sub-command!");

                        }

                    } else if (args[0].equalsIgnoreCase("links") && args.length == 1) {

                        getHeader(player);

                        // Team and Arena / Side link
                        for (String team : teamReady.keySet()) {
                            player.sendMessage(ChatColor.WHITE + team + ChatColor.GREEN + " >> " + ChatColor.WHITE + "Arena: "
                                    + plugin.getConfig().getIntegerList(team).get(0) + ChatColor.GREEN + " >> " + ChatColor.WHITE +
                                    "Side: " + plugin.getConfig().getIntegerList(team).get(1));
                        }

                        getFooter(player);

                    } else if (args[0].equalsIgnoreCase("clear") && args.length == 1) {

                        player.sendMessage(ChatColor.GREEN + "Your inventory has been cleared! Munched by Rapid");
                        player.getEquipment().setHelmet(null);
                        player.getEquipment().setChestplate(null);
                        player.getEquipment().setChestplate(null);
                        player.getEquipment().setBoots(null);
                        player.getInventory().clear();
=======

>>>>>>> Stashed changes

                    } else if (args[0].equalsIgnoreCase("help") && args.length == 1) {

                        getHeader(player);

                        player.sendMessage(ChatColor.GOLD + "/cc teams + " + ChatColor.WHITE + "Returns all players in each team");

                        getFooter(player);

                    } else if (args[0].equalsIgnoreCase("status") && args.length == 1) {

                        // Send the Header
                        player.sendMessage(ChatColor.GRAY + "-=<{[" + ChatColor.YELLOW + " Champion" + ChatColor.GOLD + " Crusader"
                                + ChatColor.WHITE + " Team Ready " + ChatColor.GRAY + "]}>=-");
                        player.sendMessage(ChatColor.GOLD + "========================================");

                        // Team and Ready Status
                        for (String team : teamReady.keySet()) {
                            if (teamReady.get(team)) {

                                player.sendMessage(team + ChatColor.GREEN + " >> " + ChatColor.DARK_GREEN + "READY");

                            } else {

                                player.sendMessage(team + ChatColor.GREEN + " >> " + ChatColor.DARK_RED + "NOT READY");

                            }
                        }

                        getFooter(player);

                    } else if (args[0].equalsIgnoreCase("teams") && args.length == 1) {

                        getHeader(player);

                        // Print every player with their team and class if they have a class
                        for (String person : playerToTeam.keySet()) {
                            if (playerToClass.containsKey(person)) {
                                player.sendMessage("N: " + person + ChatColor.GREEN + " >>" + ChatColor.WHITE + " T: "
                                        + playerToTeam.get(person) + ChatColor.GREEN + " >>" + ChatColor.WHITE
                                        + " C: " + playerToClass.get(person));
                            } else {
                                player.sendMessage(person + ChatColor.GREEN + " >> " + ChatColor.WHITE + playerToTeam.get(person));
                            }
                        }

                        getFooter(player);

                    } else {

                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                + "] " + ChatColor.DARK_RED + "Invalid sub-command!");

                    }
                // If an invalid amount of arguments is given
                } else {

                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "Improper amount of arguements!");

                }
            // If the player has no permission to run this command
            } else {

                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                        + "] " + ChatColor.DARK_RED + "Invalid permission!");

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
        if (team.equalsIgnoreCase("STAFF")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "lp user " + target.getName() + " parent set " + team.toLowerCase());

            // Send a message to both the sender
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "The player " + target.getName()
                    + " has their team set to " + team);

            // Remove staff member from all the maps
            if (playerToTeam.containsKey(target.getName())) {
                playerToTeam.remove(target.getName());
            }

            if (playerToClass.containsKey(target.getName())) {
                playerToClass.remove(target.getName());
            }

        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "lp user " + target.getName() + " parent set " + team.toLowerCase() + "-team");

            playerToTeam.put(target.getName(), team); // Put them into the Map

            // Send a message to both the sender and the target
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "The player " + target.getName()
                    + " has their team set to " + team);

            target.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "Your team is now " + team);
        }
    }

    /**
     * Given a player, return their team if they have one
     * @param player Player to check teams
     * @return Team or null if no Team
     */
    public static String getTeam(Player player) {
        if (player.getScoreboardTags().contains("RED")) {
            return "RED";
        } else if (player.getScoreboardTags().contains("BLUE")) {
            return "BLUE";
        } else if (player.getScoreboardTags().contains("GREEN")) {
            return "GREEN";
        } else if (player.getScoreboardTags().contains("YELLOW")) {
            return "YELLOW";
        } else if (player.getScoreboardTags().contains("PURPLE")) {
            return "PURPLE";
        } else if (player.getScoreboardTags().contains("PINK")) {
            return "PINK";
        } else if (player.getScoreboardTags().contains("CYAN")) {
            return "CYAN";
        } else if (player.getScoreboardTags().contains("ORANGE")) {
            return "ORANGE";
        }
        return null;
    }

    /**
     * Header for the command
     * @param player Player that gets the message
     */
    public static void getHeader(Player player) {
        // Send the Header
        player.sendMessage(ChatColor.GRAY + "-=<{[" + ChatColor.YELLOW + " Champion" + ChatColor.GOLD + " Crusader"
                + ChatColor.WHITE + " Registered Players " + ChatColor.GRAY + "]}>=-");
        player.sendMessage(ChatColor.GOLD + "========================================");
    }

    /**
     * Footer for the command
     * @param player Player that gets the message
     */
    public static void getFooter(Player player) {
        // Send the Footer
        player.sendMessage(ChatColor.GOLD + "========================================");
    }
}
