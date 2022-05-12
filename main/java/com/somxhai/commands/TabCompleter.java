package com.somxhai.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    private final List<String> commands = Arrays.asList("reload", "egg");
    private final List<String> player = new ArrayList<>();
    private final List<String> age = List.of("<age>");
    private final List<String> amount = List.of("<amount>");
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (sender instanceof Player && alias.equalsIgnoreCase("vilage")) {
            if (args.length == 1) {
                return commands;
            }
            // /vilage egg <age> <player> <amount>
            if (args.length==2 && args[0].equalsIgnoreCase("egg")) {
                return age;
            }
            else if (!args[1].isEmpty() && args.length == 3) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    player.add(p.getName());
                }
                return player;
            } else if (!args[1].isEmpty() && args.length==4) {
                return amount;
            } else {
                return List.of();
            }
        }
        return null;
    }
}
