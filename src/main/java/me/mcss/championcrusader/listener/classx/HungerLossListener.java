package me.mcss.championcrusader.listener.classx;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerLossListener implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent e){

        e.setFoodLevel(20);

    }

}
