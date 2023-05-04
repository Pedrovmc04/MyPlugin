package org.pedro.listerners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.LinkedList;
import java.util.Queue;

public class SwapWand implements Listener{
    @EventHandler
    public void swapWand(PlayerInteractEvent event){
        Player p = event.getPlayer();

        if(p.getItemInHand().getType().equals(Material.NETHERITE_AXE) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){

            Block block = event.getClickedBlock();

            bfs(p,block,3,Material.DIAMOND_BLOCK);
        }

    }

    public void bfs(Player p, Block block,int d_max,Material change){
        if(block.getType().equals(change))return;

        int[] dx = {1,-1,0,0,0,0};
        int[] dy = {0,0,1,-1,0,0};
        int[] dz = {0,0,0,0,1,-1};

        Location loc = block.getLocation();
        Material material = block.getType();

        block.setType(change);

        // Start BFS

        Queue<Location> queue = new LinkedList<>();
        queue.add(loc);

        while(!queue.isEmpty()){
            Location l = queue.remove();

            for(int i = 0 ; i < 6 ; i++){
                Location next_block = l.clone().add(dx[i],dy[i],dz[i]);
                Location d = next_block.clone().subtract(loc);
                int x = (d.getBlockX() >= 0) ? d.getBlockX() : -d.getBlockX();
                int y = (d.getBlockY() >= 0) ? d.getBlockY() : -d.getBlockY();
                int z = (d.getBlockZ() >= 0) ? d.getBlockZ() : -d.getBlockZ();
                int dist = x+y+z;

                if(dist <= d_max && next_block.getBlock().getType().equals(material)){
                    next_block.getBlock().setType(change);
                    queue.add(next_block);
                }
            }

        }
    }

}


