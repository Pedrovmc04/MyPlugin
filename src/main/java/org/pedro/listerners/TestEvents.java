package org.pedro.listerners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static jdk.internal.org.jline.utils.Colors.s;

public class TestEvents implements Listener {

    // Join message
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String joinMessage = String.format("Welcome to the server %s",event.getPlayer().getName());
        event.setJoinMessage(ChatColor.GREEN + joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        String quitMessage = String.format("Bye bye to the server %s",event.getPlayer().getName());
        event.setQuitMessage(ChatColor.RED + quitMessage);
    }

    @EventHandler
    public void fireRod(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if(p.getItemInHand().getType().equals(Material.BLAZE_ROD) && event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            p.sendMessage(ChatColor.RED + "Wizard!!");
            Fireball fireball = p.getWorld().spawn(p.getLocation().add(0,1,0),Fireball.class);
        }
    }

    @EventHandler
    public void iceRod(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if(p.getItemInHand().getType().equals(Material.IRON_HOE) && event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            p.sendMessage(ChatColor.BLUE + "Wizard!!");
            Snowball snowball = p.getWorld().spawn(p.getLocation().add(0,1,0), Snowball.class);
            snowball.setVelocity(p.getLocation().getDirection().multiply(3));
            snowball.setCustomName("JG");
        }
    }

    @EventHandler
    public void iceRodHit(ProjectileHitEvent event){
        Player p = (Player) event.getEntity().getShooter();
        String name = event.getEntity().getCustomName();
        assert name != null;

        if(event.getEntity() instanceof Snowball && name.equals("JG")){
            System.out.println(s);

            Location local =  event.getEntity().getLocation();
            Block block = local.getBlock();
            Location blockPos = block.getLocation();

            makeCircle(blockPos,3.0,Material.ICE);
        }
    }

    public void makeCircle(Location loc, Double r, Material m)
    {
        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
            double radius = Math.sin(i);
            double y = Math.cos(i);
            for (double a = 0; a < Math.PI * 2; a+= Math.PI / 10) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                loc.add(x, y, z);
                loc.getBlock().setType(m);
                loc.subtract(x, y, z);
            }
        }
    }

}
