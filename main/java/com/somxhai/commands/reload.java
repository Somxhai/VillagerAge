package com.somxhai.commands;

import com.somxhai.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        main.plugin.tempData.removeNametag();
        if (label.equalsIgnoreCase("village") && args[0].equalsIgnoreCase("reload")) {

        }
        return true;
    }
}
