package org.pedro;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.pedro.commands.Die;
import org.pedro.commands.Gui;
import org.pedro.listerners.Entitys;
import org.pedro.listerners.SwapWand;
import org.pedro.listerners.TestEvents;

public final class Pedro extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("My Plugin started");

        getServer().getPluginManager().registerEvents(new TestEvents(), this);
        getServer().getPluginManager().registerEvents(new Entitys(), this);
        getServer().getPluginManager().registerEvents(new SwapWand(), this);

        getCommand("die").setExecutor(new Die());
        getCommand("gui").setExecutor(new Gui());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("My Plugin stopped");
    }
}
