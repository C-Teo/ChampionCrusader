package me.mcss.championcrusader; // Main package

// ClassX Imports
import me.mcss.championcrusader.command.classes.*;
import me.mcss.championcrusader.command.teams.teamCommand;
import me.mcss.championcrusader.listener.classes.*;

// Ghost Spawn Imports
import me.mcss.championcrusader.listener.mobkills.EntityHealListener;
import me.mcss.championcrusader.listener.mobkills.EntityDamageListener;
import me.mcss.championcrusader.listener.mobkills.MobSpawnListener;
import me.mcss.championcrusader.listener.mobkills.PotionEffectListener;
import me.mcss.championcrusader.listener.respawn.*;

// Item Reload imports
import me.mcss.championcrusader.listener.cooldown.*;

// Plentiful Arrow Imports
import me.mcss.championcrusader.listener.arrowAbility.*;

// Java Plugin Import
import org.bukkit.plugin.java.JavaPlugin;

// Other Imports
import java.util.HashMap;

public final class ChampionCrusader extends JavaPlugin {

    private static final HashMap<String, String> playerToTeam = new HashMap<>();
    private static final HashMap<String, String> playerToClass = new HashMap<>();
    private static final HashMap<String, Boolean> teamReady = new HashMap<>();
    private static final HashMap<String, Boolean> gameRunning = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        teamReady.put("RED",false);
        teamReady.put("BLUE",false);
        teamReady.put("GREEN",false);
        teamReady.put("YELLOW",false);
        teamReady.put("PINK",true);
        teamReady.put("PURPLE",true);
        teamReady.put("CYAN",true);
        teamReady.put("ORANGE",true);

        gameRunning.put("A1",false);
        gameRunning.put("A2",false);
        gameRunning.put("A3",false);
        gameRunning.put("A4",false);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // -= Arrow =-
        /*
        Return the arrow shot to the player
        if they hit their target.
         */
        getServer().getPluginManager().registerEvents(new GetShotListener(),this);

        // -= Respawn =-
        /*
        Properly respawn the player with a countdown
        back at their respective base. (USES TAGS AND CONFIG)
         */
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(this),this);

        // -= Cooldown =-
        /*
        Cool down mechanic for the potions given to the
        mage. Potion has to be exactly the same as the
        check to allow for multiple cool downs.
         */
        getServer().getPluginManager().registerEvents(new PotionSplashListener(this),this);
        getServer().getPluginManager().registerEvents(new InventoryInteractListener(),this);

        // -= Mobs =-
        /*
        Checks the mobs that are killed and gives the
        players rewards for killing them
         */
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this),this);
        getServer().getPluginManager().registerEvents(new PotionEffectListener(),this);
        getServer().getPluginManager().registerEvents(new EntityHealListener(),this);
        getServer().getPluginManager().registerEvents(new MobSpawnListener(),this);

        // -= Classes =-
        /*
        Handles all class selection commands and
        utility. poop
         */

        // Listeners
        getServer().getPluginManager().registerEvents(new ItemDropListener(),this);
        getServer().getPluginManager().registerEvents(new UnequipListener(),this);
        getServer().getPluginManager().registerEvents(new RageListener(),this);

        // Commands
        getCommand("berserker").setExecutor(new BerserkerCommand(this));
        getCommand("paladin").setExecutor(new PaladinCommand(this));
        getCommand("mage").setExecutor(new MageCommand(this));
        getCommand("ranger").setExecutor(new RangerCommand(this));
        getCommand("cc").setExecutor(new teamCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HashMap<String, String> getPlayerToTeam() { return playerToTeam; }

    public HashMap<String, String> getPlayerToClass() { return playerToClass; }

    public HashMap<String, Boolean> getTeamReady() { return teamReady; }

    public HashMap<String, Boolean> getGameRunning() { return gameRunning; }
}
