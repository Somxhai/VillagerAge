package com.somxhai.commands;

import com.somxhai.database.GetDataSQL;
import com.somxhai.Main;
import com.somxhai.utils.GetWorlds;
import com.somxhai.utils.NameTagMethod;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

public class ReloadCommand implements CommandExecutor {
    private final GetDataSQL data = Main.plugin.data;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Main.plugin.tempData.removeNametag();
        if (sender.hasPermission("vilage.commands.reload") || sender.isOp()) {
            if (label.equalsIgnoreCase("vilage") && args[0].equalsIgnoreCase("reload")) {
                for (World world : GetWorlds.getWorlds()) {
                    for (Entity e : world.getEntities()) {
                        if (data.exists(e.getUniqueId())) {
                            e.setCustomName("");
                            data.onServerCloseUpdate();
                            int age = data.getAge(e.getUniqueId());
                            NameTagMethod nameTagMethod = new NameTagMethod(e);
                            nameTagMethod.createNameTag(age);
                        }
                    }
                }
            }
        }
        return true;
    }
}
