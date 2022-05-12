package com.somxhai.utils;

import com.somxhai.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CustomEgg {
    final int age;
    final int amount;
    public CustomEgg(int age, int amount) {
        this.age = age;
        this.amount = amount;
    }
    private String ChatColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public ItemStack createItemStack() {
        String lastLine = Main.plugin.getConfig().getString("custom_egg_last_line");
        ItemStack egg = new ItemStack(Material.VILLAGER_SPAWN_EGG, amount);
        ItemMeta meta = egg.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor("&7Villager &f| &a" + age + " Days"));
            meta.setLore(Arrays.asList(
                    ChatColor("&6 "),
                    ChatColor("&8Description:"),
                    ChatColor("&7Age: &6" + age),
                    ChatColor("&7 "),
                    ChatColor(lastLine)
            ));
            Main.plugin.tempData.addToItemMeta(egg, amount);
            egg.setItemMeta(meta);
        }
        return egg;
    }
}
