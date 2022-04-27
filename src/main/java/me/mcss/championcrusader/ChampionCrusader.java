package me.mcss.championcrusader; // Main package

// ClassX Imports
import me.mcss.championcrusader.command.classx.*;
import me.mcss.championcrusader.listener.classx.*;

// Ghost Spawn Imports
import me.mcss.championcrusader.listener.ghostrespawn.*;

// Item Reload imports
import me.mcss.championcrusader.listener.itemreload.*;

// Plentiful Arrow Imports
import me.mcss.championcrusader.listener.plentifularrow.*;

// Java Plugin Import
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChampionCrusader extends JavaPlugin {

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
        getCommand("berserker").setExecutor(new BerserkerCommand(this));
        getCommand("paladin").setExecutor(new PaladinCommand(this));
        getCommand("mage").setExecutor(new MageCommand(this));
        getCommand("ranger").setExecutor(new RangerCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
