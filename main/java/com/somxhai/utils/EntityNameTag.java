package com.somxhai.utils;

import com.somxhai.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class EntityNameTag {
    private final Entity entity;
    private int age;
    private final UUID id;
    public EntityNameTag(Entity entity, int defaultAge) {
        this.entity = entity;
        age = defaultAge;
        id = entity.getUniqueId();
        showNametag();
    }
    private final int max_age = Main.plugin.getConfig().getInt("age");
    private void showNametag() {
        entity.setCustomName("");
        entity.setCustomName(ChatColor.translateAlternateColorCodes('&',
                "&6"+ entity.getName() + " &6| &a" + age + " &aDays"));
    }
    private final int fifty = (int) (max_age*(50.0f/100.0f));
    private final int eighty = (int) (max_age*(80.0f/100.0f));
    private final TempData tempData = Main.plugin.tempData;
    public void addAge() {
        entity.setCustomName("");
        long time = entity.getWorld().getTime();
        if (time > 0 && time <= 170) {
            age += 1;
            if (age >= fifty) {
                entity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6"+ entity.getName() + " &6| &e" + age + " &aDays"));
            } else if (age >= eighty) {
                entity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6"+ entity.getName() + " &6| &c" + age + " &aDays"));
            } else {
                entity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6"+ entity.getName() + " &6| &a" + age + " &aDays"));
            }
        }
       tempData.addToHashMap(id, age);
    }
    public int getAge() {
        return age;
    }

    public Entity getEntity() {
        return entity;
    }
}
