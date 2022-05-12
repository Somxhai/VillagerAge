package com.somxhai.utils;

import com.somxhai.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetWorlds {
    public static List<World> getWorlds() {
        Set<String> disabled_worlds = new HashSet<>(Main.plugin.getConfig().getStringList("disable_worlds"));
        List<World> worlds = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            if (!disabled_worlds.contains(world.toString())) {
                worlds.add(world);
            }
        }
        return worlds;
    }
}
