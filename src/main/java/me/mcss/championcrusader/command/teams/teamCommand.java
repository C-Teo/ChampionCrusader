package me.mcss.championcrusader.command.teams;

// Imports
import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.gate.gateOpenTask;
import me.mcss.championcrusader.task.respawn.CountdownTask;
import me.mcss.championcrusader.task.tutorial.tutorialTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.ArrayList;
import java.util.HashMap;

public class teamCommand implements CommandExecutor {
    ChampionCrusader plugin;
    private final HashMap<String,String> playerToTeam; // Variable
    private final HashMap<String,String> playerToClass;
    private final HashMap<String, Boolean> teamReady;
    private final HashMap<String, Boolean> gameRunning;

    public teamCommand(ChampionCrusader plugin) {
        this.playerToTeam = plugin.getPlayerToTeam();
        this.playerToClass = plugin.getPlayerToClass();
        this.teamReady = plugin.getTeamReady();
        this.plugin = plugin;
        this.gameRunning = plugin.getGameRunning();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) { // Check if sender is player, this might be changed later
            Player player = (Player) sender; // Cast
            if (player.hasPermission("cc")) { // Check for player permission
                if (args.length >= 1) { // Check for 3 arguments
                    if (args[0].equalsIgnoreCase("setteam") && args.length >= 3) {
                        setteam(player,args);
                    } else if (args[0].equalsIgnoreCase("ready") && args.length == 1) {
                        ready(player);
                    } else if (args[0].equalsIgnoreCase("tutorial") && args.length == 1) {
                        tutorial(player);
                    } else if (args[0].equalsIgnoreCase("linkteam") && args.length == 4) {
                        linkteam(player,args);
                    } else if (args[0].equalsIgnoreCase("load") && args.length == 1) {
                        load(player);
                    } else if (args[0].equalsIgnoreCase("links") && args.length == 1) {
                        links(player);
                    } else if (args[0].equalsIgnoreCase("clear")) {
                        clear(player,args);
                    } else if (args[0].equalsIgnoreCase("help") && args.length == 1) {
                        help(player);
                    } else if (args[0].equalsIgnoreCase("status") && args.length == 1) {
                        status(player);
                    } else if (args[0].equalsIgnoreCase("teams") && args.length == 1) {
                        teams(player);
                    } else if (args[0].equalsIgnoreCase("start") && args.length == 1) {
                        start(player);
                    } else if (args[0].equalsIgnoreCase("reset") && args.length == 1) {
                        reset(player);
                    } else if (args[0].equalsIgnoreCase("running") && args.length == 1) {
                        running(player);
                    } else {
                        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                                + "] " + ChatColor.DARK_RED + "Invalid sub-command!");
                    }
                } else {
                    // If an invalid amount of arguments is given
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "Improper amount of arguements!");
                }
            } else {
                // If the player has no permission to run this command
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                        + "] " + ChatColor.DARK_RED + "Invalid permission!");
            }
        } else {
            if (args[0].equalsIgnoreCase("clear") && args.length == 2) {
                clear(null,args);
            }
        }
        return true;
    }

    /**
     * GUI Command for seeing all Teams and the Bases
     * They are linked to.
     * @param player who sent the command
     */
    public void links(Player player) {
        getHeader(player); // Header
        // Team and Arena / Side link
        for (String team : teamReady.keySet()) {
            // Send the player each line one by one
            player.sendMessage(ChatColor.WHITE + team + ChatColor.GREEN + " >> " + ChatColor.WHITE + "Arena: "
                    + plugin.getConfig().getIntegerList(team).get(0) + ChatColor.GREEN + " >> " + ChatColor.WHITE +
                    "Side: " + plugin.getConfig().getIntegerList(team).get(1));
        }
        getFooter(player); // Footer
    }

    /**
     * Clear the person's inventory, including Armor
     * @param player who sent the command
     * @param args Used for whose inventory to clear, if it is empty
     *             clear the Players inventory.
     */
    public void clear(Player player, String[] args) {
        // If the arguments are not empty
        if (args.length > 1) {
            // Use the first one to get a real player
            Player target = Bukkit.getPlayerExact(args[1]);
            // If the player exists clear their inventory
            if (target != null) {
                target.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                        + "] " + ChatColor.WHITE + "Your inventory has been reset!");
                target.getInventory().clear();
            } else {
                if (player != null) {
                    // Else the player does not exist
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "That player does not exist!");
                }
            }
        } else {
            // If the arguements are empty just clear the command sender's inventory.
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "Your inventory has been reset!");
            player.getInventory().clear();
        }
    }

    /**
     * Process for Loading Players into the Game of Champion Crusader
     * Also loading all the assets needed for the Data Pack.
     * @param player who sent the command
     */
    public void load(Player player) {
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                + "] " + ChatColor.WHITE + "Loading Players into the game!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "function champcrusaders:load");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "function champcrusaders:gamestart");
        // Turn Each Game on
        for (String game : gameRunning.keySet()) {
            gameRunning.put(game,true);
        }
    }

    /**
     * Shows the sender which games are currently playing
     * @param player who sent the command.
     */
    public void running(Player player) {
        getHeader(player);
        for (String game : gameRunning.keySet()) {
            // For Each Game get their Running Status
            if (gameRunning.get(game)) {
                player.sendMessage(game + ChatColor.GREEN + " >> " + ChatColor.DARK_GREEN + "ONGOING");
            } else {
                player.sendMessage(game + ChatColor.GREEN + " >> " + ChatColor.DARK_RED + "FINISHED");
            }
        }
        getFooter(player);
    }

    /**
     * Command used by the NPC to send a player through
     * a Tutorial for Champion Crusaders!
     * @param player who sent the command
     */
    public void tutorial(Player player) {
        BukkitTask msgOne = new tutorialTask(1,player).runTask(plugin);
        BukkitTask msgTwo = new tutorialTask(2,player).runTaskLater(plugin,200L);
        BukkitTask msgThree = new tutorialTask(3,player).runTaskLater(plugin,400L);
        BukkitTask msgFour = new tutorialTask(4,player).runTaskLater(plugin,600L);
        BukkitTask msgFive = new tutorialTask(5,player).runTaskLater(plugin,800L);
        BukkitTask msgSix = new tutorialTask(6,player).runTaskLater(plugin,1000L);
    }

    /**
     * Start the Champion Crusader Game,
     * This command is used after load.
     * @param player who sent the command
     */
    public void start(Player player) {
        // Start the Data Pack Timer.
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "function champcrusaders:gamestarttimer");
        // Send countdown to each Player.
        for (Player user : player.getWorld().getPlayers()) {
            user.setGameMode(GameMode.ADVENTURE);
            BukkitTask countThree = new CountdownTask(user, ChatColor.RED + "3", 0.4f).runTaskLater(this.plugin,15L);
            BukkitTask countTwo = new CountdownTask(user, ChatColor.YELLOW + "2", 0.6f).runTaskLater(this.plugin, 30L);
            BukkitTask countOne = new CountdownTask(user, ChatColor.GREEN + "1", 0.8f).runTaskLater(this.plugin, 45L);
            BukkitTask countGO = new CountdownTask(user, ChatColor.BLUE + "GO", 1.0f).runTaskLater(this.plugin, 60L);
            BukkitTask doorOpenSound = new gateOpenTask(user).runTaskLater(this.plugin, 60L);
        }
    }

    /**
     * Send the person who ran this command
     * All current players, their teams, and their
     * classes if chosen.
     * @param player who sent the command.
     */
    public void teams(Player player) {
        getHeader(player); // Header
        // Print every player with their team and class if they have a class
        for (String person : playerToTeam.keySet()) {
            if (playerToClass.containsKey(person)) {
                // If they have a class chosen
                player.sendMessage("N: " + person + ChatColor.GREEN + " >> " + ChatColor.WHITE + "T: "
                        + playerToTeam.get(person) + ChatColor.GREEN + " >> " + ChatColor.WHITE
                        + "C: " + playerToClass.get(person));
            } else {
                player.sendMessage(person + ChatColor.GREEN + " >> " + ChatColor.WHITE + playerToTeam.get(person));
            }
        }
        getFooter(player); // Footer
    }

    /**
     * Reset everything after a game is over.
     * @param player who sent the command.
     */
    public void reset(Player player) {
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                + "] " + ChatColor.WHITE + "Resetting everything for you!");
        // Clear every players inventory and Classes
        for (Player user : player.getWorld().getPlayers()) {
            user.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "Clearing your inventory!");
            user.getInventory().clear();
            user.setHealth(20f);
        }
        // Reset all mobs and doors
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "minecraft:kill @e[type=minecraft:iron_golem]");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "minecraft:kill @e[type=minecraft:armor_stand]");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "function champcrusaders:resetalldoors");
        // Reset all Classes
        playerToClass.clear();
        // Reset the Ready Status of each Team
        for (String team : teamReady.keySet()) {
            teamReady.put(team,false);
        }
        // Reset the Game Running of each Game
        for (String game : gameRunning.keySet()) {
            gameRunning.put(game,false);
        }
    }

    /**
     * Ready your currently chosen Team
     * if you are a part of a team.
     * @param player who sent the command.
     */
    public void ready(Player player) {
        String team = getTeam(player, plugin); // Get the team who ran the command
        // Check if the person is part of a team
        if (team == null) {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.DARK_RED + "You are not part of a team!");
            return;
        }
        // Check if the Team is not already readied
        if (teamReady.get(team)) {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.DARK_GREEN + "Your team is already ready!");
            return;
        }
        // All flags to check
        boolean berserker = false;
        boolean mage = false;
        boolean paladin = false;
        boolean ranger = false;
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
        // At the end check if we have all the classes needed to Ready Team
        if (berserker && mage && paladin && ranger) {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.DARK_GREEN + "Your team is now ready!");
            teamReady.put(team,true);
        } else {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.DARK_RED + "Your whole team has not picked a class!");
        }
    }

    /**
     * Send the player every Team and their
     * Ready Status.
     * @param player who sent the command.
     */
    public void status(Player player) {
        getHeader(player); // Header
        // Team and Ready Status
        for (String team : teamReady.keySet()) {
            // For Each Team get their Ready Status
            if (teamReady.get(team)) {
                player.sendMessage(team + ChatColor.GREEN + " >> " + ChatColor.DARK_GREEN + "READY");
            } else {
                player.sendMessage(team + ChatColor.GREEN + " >> " + ChatColor.DARK_RED + "NOT READY");
            }
        }
        getFooter(player); // Footer
    }

    /**
     * Send the Sender all Commands
     * currently registered by CC
     * @param player who sent the command.
     */
    public void help(Player player) {
        getHeader(player); // Header
        player.sendMessage(ChatColor.YELLOW + "/cc reset: "
                + ChatColor.WHITE + "Reset everything needed for Champion Crusaders.");
        player.sendMessage(ChatColor.YELLOW + "/cc load: "
                + ChatColor.WHITE + "Load everything needed for Champion Crusaders.");
        player.sendMessage(ChatColor.YELLOW + "/cc start: "
                + ChatColor.WHITE + "Start all loaded games of Champion Crusaders.");
        player.sendMessage(ChatColor.YELLOW + "/cc teams: "
                + ChatColor.WHITE + "Return a list of all players in each Team and Class.");
        player.sendMessage(ChatColor.YELLOW + "/cc links: "
                + ChatColor.WHITE + "Returns a list of all Teams and Arena / Sides linked.");
        player.sendMessage(ChatColor.YELLOW + "/cc running: "
                + ChatColor.WHITE + "Returns a list of all Games and running Status");
        player.sendMessage(ChatColor.YELLOW + "/cc status: "
                + ChatColor.WHITE + "Returns a list of all Teams and their Ready status.");
        player.sendMessage(ChatColor.YELLOW + "/cc ready: "
                + ChatColor.WHITE + "Set your team to ready!");
        player.sendMessage(ChatColor.YELLOW + "/cc setteam "
                + ChatColor.GREEN + "[TEAM] [PLAYER1] [PLAYER2] ... [PLAYERn]: " + ChatColor.WHITE + "Set a player to a team.");
        player.sendMessage(ChatColor.YELLOW + "/cc linkteam " +
                ChatColor.GREEN + "[TEAM] [ARENA] [SIDE]: " + ChatColor.WHITE + "Link a Team to an Arena and Side.");
        player.sendMessage(ChatColor.YELLOW + "/cc clear " + ChatColor.GREEN + "[PLAYER]: "
                + ChatColor.WHITE + "Clear the inventory of the given player.");
        player.sendMessage(ChatColor.YELLOW + "/cc tutorial: "
                + ChatColor.WHITE + "Sends the player through a tutorial.");
        getFooter(player); // Footer
    }

    /**
     Provided with a Team color, Sender, and Target this method
     will set the Target's tag to the team color and add them
     to the LP role and Team Map.
     @param team String that contains the color
     @param sender who sent the command.
     @param target This is the person who is being targeted
     */
    public void setTeamFunction(String team, Player sender, Player target) {
        // Set player's tag
        target.getScoreboardTags().clear();
        target.getScoreboardTags().add(team);
        // If the player is becoming a Staff
        if (team.equalsIgnoreCase("STAFF")) {
            // Set the player to the LP role
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "lp user " + target.getName() + " parent set " + team.toLowerCase());
            // Send a message to both the sender
            target.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "Your team is now " + team);
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
            // The player is joining a Team
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "lp user " + target.getName() + " parent set " + team.toLowerCase() + "-team");
            // Put them into the Map
            playerToTeam.put(target.getName(), team);
            // Put them into the actual Team
            sender.getScoreboard().getTeam(team).addEntry(target.getName());
            // Send a message to both the sender and the target
            target.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "Your team is now " + team);
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.WHITE + "The player " + target.getName()
                    + " has their team set to " + team);
        }
    }

    /**
     * Given a player, return their team if they have one
     * @param player Player to check teams
     * @param plugin Main plugin storing Hash Maps
     * @return Team if they have one, Null if they dont
     */
    public static String getTeam(Player player, ChampionCrusader plugin) {
        HashMap<String,String> playerToTeam = plugin.getPlayerToTeam();
        if (playerToTeam.containsKey(player.getName())) {
            return playerToTeam.get(player.getName());
        }
        return null;
    }

    /**
     * Header for the command
     * @param player Player that gets the message
     */
    public static void getHeader(Player player) {
        // Send the Header
        player.sendMessage(ChatColor.GRAY + "-=<{[" + ChatColor.YELLOW + " Champion" + ChatColor.GOLD + " Crusader "
                + ChatColor.GRAY + "]}>=-");
    }

    /**
     * Footer for the command
     * @param player Player that gets the message
     */
    public static void getFooter(Player player) {
        // Send the Footer // Currently Empty
    }

    /**
     * Change the Team and Arena/Side links
     * @param player who sent the command.
     * @param args Team , Arena , Side
     */
    public void linkteam(Player player, String args[]) {
            String team = args[1];
            team = team.toUpperCase();
            // Check if the team exists
            if (team.equalsIgnoreCase("RED") || team.equalsIgnoreCase("BLUE")
                    || team.equalsIgnoreCase("GREEN") || team.equalsIgnoreCase("YELLOW")
                    || team.equalsIgnoreCase("PINK") || team.equalsIgnoreCase("PURPLE")
                    || team.equalsIgnoreCase("CYAN") || team.equalsIgnoreCase("ORANGE")) {
                // Arena and Side
                String arena = args[2];
                String side = args[3];
                // Check if the Arena and Side exists
                if ((arena.equals("1") || arena.equals("2") || arena.equals("3") || arena.equals("4"))
                        && (side.equals("1") || side.equals("2"))) {
                    // First Index is Arena and Second Index is Side
                    ArrayList<Integer> pass = new ArrayList<Integer>();
                    pass.add(Integer.parseInt(arena));
                    pass.add(Integer.parseInt(side));
                    // Update the Config temp at the Team
                    plugin.getConfig().set(team,pass);
                    // Tell the sender
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.WHITE + "Team " + team + " was assigned to Arena " + arena + " and Side " + side);
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
    }

    /**
     * Runs the setteam Function by first
     * checking if the Team given is real
     * and the players exists.
     * @param player who sent the command.
     * @param args The Player and Team
     */
    public void setteam(Player player, String[] args) {
        String team = null;
        if (args[1].equalsIgnoreCase("red")) {
            team = "RED";
        } else if (args[1].equalsIgnoreCase("blue")) {
            team = "BLUE";
        } else if (args[1].equalsIgnoreCase("green")) {
            team = "GREEN";
        } else if (args[1].equalsIgnoreCase("yellow")) {
            team = "YELLOW";
        } else if (args[1].equalsIgnoreCase("purple")) {
            team = "PURPLE";
        } else if (args[1].equalsIgnoreCase("pink")) {
            team = "PINK";
        } else if (args[1].equalsIgnoreCase("orange")) {
            team = "ORANGE";
        } else if (args[1].equalsIgnoreCase("cyan")) {
            team = "CYAN";
        } else if (args[1].equalsIgnoreCase("staff")) {
            team = "STAFF";
        }
        if (team != null) {
            // For every argument after the first check to see if it is a player
            for (int i = 2; i < args.length; i++) {
                // Retrieve the player from the argument
                Player target = Bukkit.getPlayerExact(args[i]);
                if (target != null) { // Check if we found a player
                    // Check if we can find that Player and run setTeam method
                    setTeamFunction(team, player, target);
                } else {
                    // If the player was not found
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                            + "] " + ChatColor.DARK_RED + "The player " + args[i] + " is not online or does not exist!");
                }
            }
        } else {
            // The Team provided does not exist
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Champion Crusader" + ChatColor.GRAY
                    + "] " + ChatColor.DARK_RED + "The team color " + args[1] + " does not exist!");
        }
    }
}
