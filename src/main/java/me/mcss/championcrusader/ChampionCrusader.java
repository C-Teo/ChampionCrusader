package me.mcss.championcrusader; // Main package

// ClassX Imports
import me.mcss.championcrusader.command.classes.*;
import me.mcss.championcrusader.command.teams.showTeamsCommand;
import me.mcss.championcrusader.command.teams.teamCommand;
import me.mcss.championcrusader.listener.classes.*;

// Ghost Spawn Imports
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

    public HashMap<String, String> playerToTeam = new HashMap<>();
    public HashMap<String, String> playerToClass = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        // -= PLENTIFUL ARROW =-
        /*
        Return the arrow shot to the player
        if they hit their target.
         */
        getServer().getPluginManager().registerEvents(new GetShotListener(),this);

        // -= GHOST SPAWN =-
        /*
        Properly respawn the player with a countdown
        back at their respective base. (USES TAGS AND CONFIG)
         */
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(this),this);

        // -= ITEM RELOAD =-
        /*
        Cool down mechanic for the potions given to the
        mage. Potion has to be exactly the same as the
        check to allow for multiple cool downs.
         */
        getServer().getPluginManager().registerEvents(new PotionSplashListener(this),this);

        // -= CLASS X =-
        /*
        Handles all class selection commands and
        utility.
         */

        // Listeners
        getServer().getPluginManager().registerEvents(new ItemDropListener(),this);
        getServer().getPluginManager().registerEvents(new UnequipListener(),this);
        getServer().getPluginManager().registerEvents(new RageListener(),this);

        // Commands
        getCommand("berserker").setExecutor(new BerserkerCommand(this,playerToTeam,playerToClass));
        getCommand("paladin").setExecutor(new PaladinCommand(this,playerToTeam,playerToClass));
        getCommand("mage").setExecutor(new MageCommand(this,playerToTeam,playerToClass));
        getCommand("ranger").setExecutor(new RangerCommand(this,playerToTeam,playerToClass));
        getCommand("cc").setExecutor(new teamCommand(playerToTeam));
        getCommand("teams").setExecutor(new showTeamsCommand(playerToTeam,playerToClass));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
