package org.pedro.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        Player p = (Player) sender;

        Inventory inventory = Bukkit.createInventory(p,27);

        ItemStack item = new ItemStack(Material.IRON_HOE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Click-Me");
        item.setItemMeta(itemMeta);

        inventory.setItem(1,item);

        p.openInventory(inventory);

        return true;
    }

}
