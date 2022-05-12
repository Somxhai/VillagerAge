package com.somxhai.commands;

import com.somxhai.utils.CustomEgg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EggCommand implements CommandExecutor {
    private boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("vilage.commands.egg")) {
            if (label.equalsIgnoreCase("vilage") && args[0].equalsIgnoreCase("egg")) {
                if (!args[1].isEmpty() && !args[2].isEmpty()) {
                    Player p;
                    int amount, age;
                    try {
                        p = Bukkit.getPlayer(args[2]);
                        amount = Integer.parseInt(args[3]);
                        age = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        p = (Player) sender;
                        amount = 1;
                        age = 1;
                    }
                    CustomEgg customEgg = new CustomEgg(age, amount);
                    // Egg
                    ItemStack toGiveEgg = customEgg.createItemStack();
                    // Give Method
                    if (p != null && hasAvaliableSlot(p)) {
                        p.getInventory().addItem(toGiveEgg);
                    }
                }
            }
        }
        return true;
    }
}
