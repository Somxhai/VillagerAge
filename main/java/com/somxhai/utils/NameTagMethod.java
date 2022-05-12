package com.somxhai.utils;

import com.somxhai.database.GetDataSQL;
import com.somxhai.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class NameTagMethod {
    private final int max_age = Main.plugin.getConfig().getInt("age");
    private final GetDataSQL data = Main.plugin.data;
    private Entity Entity;
    public NameTagMethod(Entity Entity) {
        this.Entity = Entity;
    }
    public void createNameTag(int age) {
        EntityNameTag nameTag = new EntityNameTag(Entity, age);
        LivingEntity livingEntity = (LivingEntity) Entity;
        this.Entity = nameTag.getEntity();
        RepeatingTask repeatingTask = new RepeatingTask(Main.plugin, 0, 200) {
            @Override
            public void run() {
                long time = Entity.getWorld().getTime();
                if (time > 0 && time <= 160) {
                    nameTag.addAge();
                }
                if (nameTag.getAge() == max_age) {
                    livingEntity.setHealth(0.0);
                    data.remove(Entity.getUniqueId());
                    cancel();
                }
            }
        };
        Bukkit.getScheduler().runTask(Main.plugin, repeatingTask);
    }

    public Entity getEntity() {
        return Entity;
    }
}
