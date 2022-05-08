package me.mcss.championcrusader.task.respawn;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classes.LeatherTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class RespawnClassTask extends BukkitRunnable {

    private Player player;
    private final ChampionCrusader plugin;
    public final HashMap<String, String> playerToTeam;
    public final HashMap<String, Boolean> gameRunning;

    public RespawnClassTask(Player player, ChampionCrusader plugin) {
        this.player = player;
        this.plugin = plugin;
        this.playerToTeam = plugin.getPlayerToTeam();
        this.gameRunning = plugin.getGameRunning();
    }

    @Override
    public void run() {
        if (playerToTeam.containsKey(player.getName())) {

            int arena = plugin.getConfig().getIntegerList(plugin.getPlayerToTeam().get(player.getName())).get(0);

            if (gameRunning.get("A" + arena)) {
                if (player.getScoreboardTags().contains("ranger")) {

                    player.getInventory().clear();

                    // Giving Items
                    player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
                    player.getInventory().addItem(new ItemStack(Material.BOW));
                    player.getInventory().addItem(new ItemStack(Material.ARROW, 8));

                    // Locked Bar
                    for (int i = 3; i < 9; i++) {
                        player.getInventory().setItem(i, new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
                    }

                    // Leather Task
                    BukkitTask leatherTask = new LeatherTask(player).runTask(plugin);

                } else if (player.getScoreboardTags().contains("berserker")) {

                    player.getInventory().clear();

                    // Giving Items
                    player.getInventory().addItem(new ItemStack(Material.STONE_AXE));

                    // Giving Redstone
                    ItemStack rage = new ItemStack(Material.REDSTONE, 1);
                    ItemMeta ragemeta = (ItemMeta) rage.getItemMeta();

                    // Set Display and Lore
                    ArrayList<String> redLore = new ArrayList<String>(); // Lore list
                    redLore.add("Collect 2 and Click to activate RAGE!");
                    redLore.add("4 for SUPER RAGE! 6 for ULTRA RAGE");
                    ragemeta.setLore(redLore);

                    // Display
                    ragemeta.setDisplayName(ChatColor.RESET + "Essence Of Rage");
                    rage.setItemMeta(ragemeta);

                    // Giving Potions
                    player.getInventory().addItem(rage);

                    // Leather Task
                    BukkitTask leatherTask = new LeatherTask(player).runTask(plugin);

                }
            }
        }
    }
}
