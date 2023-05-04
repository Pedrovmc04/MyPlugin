package org.pedro.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Die implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){

        if(sender instanceof Player){
            Player p = (Player)sender;
            p.setHealth(0.0);
            p.sendMessage(ChatColor.RED + "Welcome to the Mato");
        }else{
            System.out.println("You are not a player");
        }

        return true;
    }

}
