package com.somxhai.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class VilageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // check perm and op
        if (sender.isOp() || sender.hasPermission("vilage.*")) {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    ReloadCommand reloadCommand = new ReloadCommand();
                    reloadCommand.onCommand(sender, command, label, args);
                }
                case "egg" -> {
                    EggCommand eggCommand = new EggCommand();
                    eggCommand.onCommand(sender, command, label, args);
                }
                default -> sender.sendMessage(label + Arrays.stream(args).toList());
            }
        }
        return true;
    }
}
