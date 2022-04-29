package me.mcss.championcrusader.listener.arrowAbility;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class GetShot implements Listener { // Listen for a person getting shot
    @EventHandler // Handle the event
    public void onArrowHit(EntityDamageByEntityEvent event) {
        // Check if entity is an Arrow
        if (event.getDamager() instanceof Arrow) {
            // Cast
            Arrow arrow = (Arrow) event.getDamager();

            // Check if shooter is a Player
            if (arrow.getShooter() instanceof Player) {
                // Cast
                Player player = (Player) arrow.getShooter();

                // Give the player their arrow back
                player.getInventory().addItem(new ItemStack(Material.ARROW,1));
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP,1,1);
            }
        }

        /*
        Notice this can all be put in one line, but I made it more verbose.
        This also does not check if the user shot an Entity Player or just
        any Entity. This can easily be changed but currently shooting a Pig
        or a Cow gives your arrow back as well.

        You can also add a timer if you wanted by using a task to give the arrow.
         */
    }
}
