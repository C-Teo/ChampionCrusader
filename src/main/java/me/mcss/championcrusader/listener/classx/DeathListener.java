package me.mcss.championcrusader.listener.classx;

import me.mcss.championcrusader.ChampionCrusader;
import me.mcss.championcrusader.task.classx.LeatherTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collections;

public class DeathListener implements Listener {

    private final ChampionCrusader testPlugin;

    public DeathListener(ChampionCrusader testPlugin) {
        this.testPlugin = testPlugin;
    }

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent e){

        Player p = e.getEntity();

        p.getActivePotionEffects().clear();

        // Passing Variable
        BukkitTask leatherTask = new LeatherTask(p).runTask(testPlugin);

        if (p.getScoreboardTags().contains("ranger")){

            p.getInventory().clear();

            // Giving Items
            p.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
            p.getInventory().addItem(new ItemStack(Material.BOW));
            p.getInventory().addItem(new ItemStack(Material.ARROW,4));

            // Custom Arrows
                // Slowness Arrows
            ItemStack slowness = new ItemStack(Material.TIPPED_ARROW,2);
            PotionMeta slownessmeta = (PotionMeta) slowness.getItemMeta();
            slownessmeta.setBasePotionData(new PotionData(PotionType.SLOWNESS));
            slowness.setItemMeta(slownessmeta);
                // Weakness Arrows
            ItemStack weakness = new ItemStack(Material.TIPPED_ARROW,2);
            PotionMeta weaknessmeta = (PotionMeta) weakness.getItemMeta();
            weaknessmeta.setBasePotionData(new PotionData(PotionType.WEAKNESS));
            weakness.setItemMeta(weaknessmeta);


            // Giving players Custom Arrows
            p.getInventory().addItem(new ItemStack(slowness));
            p.getInventory().addItem(new ItemStack(weakness));

        }

        if (p.getScoreboardTags().contains("berserker")) {

            p.getInventory().clear();

            // Giving Items
            p.getInventory().addItem(new ItemStack(Material.STONE_AXE));

            // Giving Redstone
            ItemStack blood = new ItemStack(Material.REDSTONE,1);
            ItemMeta bloodmeta = (ItemMeta)blood.getItemMeta();
            bloodmeta.setDisplayName("Rage Ability");
            bloodmeta.setLore(Collections.singletonList("Collect 3 and Click to activate RAGE"));
            blood.setItemMeta(bloodmeta);

            // Giving Potions
            p.getInventory().addItem(blood);

        }

    }

}
