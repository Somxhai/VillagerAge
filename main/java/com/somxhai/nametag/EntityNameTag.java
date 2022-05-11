package com.somxhai.nametag;

import com.somxhai.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import java.util.UUID;

public class EntityNameTag {

    private final Entity entity;
    private final UUID id;
    private int day;
    public EntityNameTag(Entity entity, UUID ID, int defaultAge) {
        this.entity = entity;
        id = ID;
        day = defaultAge;
        spawnArmorStand();
    }
    private final int max_age = main.plugin.getConfig().getInt("age");

    private ArmorStand armorStand;
    private void spawnArmorStand() {
        Entity nameTag = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
        ArmorStand ams = (ArmorStand) nameTag;
        ams.setCustomNameVisible(true);
        ams.setInvisible(true);
        ams.setInvulnerable(true);
        ams.setGravity(false);
        ams.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6Villager &6| &e"+ day + " &aDay"));
        ams.setSmall(true);
        this.armorStand = ams;
        entity.addPassenger(this.armorStand);
        main.plugin.tempData.putToTemp(armorStand);
    }
    private final int fifty = (int) (max_age*(50.0f/100.0f));
    private final int eighty = (int) (max_age*(80.0f/100.0f));
    public void addAge() {
        long time = entity.getWorld().getTime();
        if (time > 0 && time <= 170) {
            day += 1;
            if (day >= fifty) {
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6Villager &6| &e" + day + " &aDay"));
            } else if (day >= eighty) {
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6Villager &6| &c" + day + " &aDay"));
            } else {
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6Villager &6| &a" + day + " &aDay"));
            }
        }
        main.plugin.data.setAge(id, day);
    }

    public int getAge() {
        return day;
    }
}
