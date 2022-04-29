package me.mcss.championcrusader.listener.classes;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RageListener implements Listener {

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)||(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {

            if (p.getInventory().getItemInMainHand().getType().equals(Material.REDSTONE)){

                int amount = p.getInventory().getItemInMainHand().getAmount();

                ItemStack hand = p.getInventory().getItemInMainHand();

                if (amount >= 6) {

                    hand.setAmount(amount - 6);
                    p.sendMessage(ChatColor.GREEN + "You have activated your ULTRA RAGE ability");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 2));
                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE,1,1);

                } else if (amount >= 4) {

                    hand.setAmount(amount - 4);
                    p.sendMessage(ChatColor.GREEN + "You have activated your SUPER RAGE ability");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
                    p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_ROAR,1,1);

                } else if (amount >= 2) {

                    hand.setAmount(amount - 2);
                    p.sendMessage(ChatColor.GREEN + "You have activated your RAGE ability");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
                    p.playSound(p.getLocation(), Sound.ENTITY_RAVAGER_ROAR,1,1);

                } else {

                    p.sendMessage(ChatColor.RED + "You don't have enough to Rage! You need at least 2!");
                    p.playSound(p.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
                }
            }
        }
    }
}