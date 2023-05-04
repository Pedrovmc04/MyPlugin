package org.pedro.listerners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Entitys implements Listener {

    @EventHandler
    public void horseStats(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if(p.getVehicle() instanceof Horse) {

            Horse horse = (Horse)p.getVehicle();

            if (p.getItemInHand().getType().equals(Material.DIAMOND_HOE)){
                if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
                    double test = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
                    p.sendMessage(ChatColor.AQUA + String.format("%f",test));
                }else if(event.getAction().equals(Action.LEFT_CLICK_AIR)){
                    horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
                    p.sendMessage(ChatColor.AQUA + "Set speed to 0.25");
                }
            }

            if (p.getItemInHand().getType().equals(Material.NETHERITE_HOE)) {
                double speed = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();

                if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
                    speed += 0.25;
                }else if(event.getAction().equals(Action.LEFT_CLICK_AIR)){
                    speed -= 0.25;
                }

                horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);

                p.sendMessage(ChatColor.AQUA + String.format("New Speed is : %f",speed));
            }
        }
    }
}
