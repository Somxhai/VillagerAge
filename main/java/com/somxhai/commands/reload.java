package com.somxhai.commands;

import com.somxhai.main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class reload implements CommandExecutor {
    public List<World> getWorlds() {
        Set<String> disabled_worlds = new HashSet<>(main.plugin.getConfig().getStringList("disable_worlds"));
        List<World> worlds = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            if (!disabled_worlds.contains(world.toString())) {
                worlds.add(world);
            }
        }
        return worlds;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        main.plugin.tempData.removeNametag();
        if (label.equalsIgnoreCase("village") && args[0].equalsIgnoreCase("reload")) {
            Player player = (Player) sender;
            for (World world : getWorlds()) {
                for (Entity e : world.getEntities()) {
                    if (main.plugin.data.exists(e.getUniqueId())) {

                    }
                }
            }

        }
        return true;
    }
}
