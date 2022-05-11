package com.somxhai.entity_age;

import com.somxhai.database.GetDataSQL;
import com.somxhai.main;
import com.somxhai.nametag.EntityNameTag;
import com.somxhai.utils.RepeatingTask;
import com.somxhai.utils.TempData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class EntityAge implements Listener {
    /*
    get EntityID add to hashmap
        key: entityID, value: age

    Nametag above entity -> Entity.TYPE ( AGE )
    TODO: safe reload, reload cmd
    * */
    private final Set<String> worlds = new HashSet<>(main.plugin.getConfig().getStringList("disable_worlds"));
    private final GetDataSQL data = main.plugin.data;
    private final TempData tempData = main.plugin.tempData;

    private final int max_age = main.plugin.getConfig().getInt("age");

    @EventHandler
    public void onChunkLoad(EntitiesLoadEvent e) {
        if (!worlds.contains(e.getWorld().toString())) {
            for (Entity entity : e.getEntities()) {
                if (entity.getType()==EntityType.VILLAGER) {
                    UUID entityID = entity.getUniqueId();
                    if (!main.plugin.data.exists(entityID)) {
                        data.createEntity(entityID, entity.getType(), 1);
                        createNameTag(entity, 1);
                    } else {
                        int database_age = data.getAge(entityID);
                        createNameTag(entity, database_age);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onSpawn(EntityBreedEvent e) {
        boolean dad = e.getFather().getType()==EntityType.VILLAGER;
        boolean mom = e.getMother().getType()==EntityType.VILLAGER;
        if (e.getEntityType()==EntityType.VILLAGER && dad && mom) {
            createNameTag(e.getEntity(), 1);
        }
    }
    private void createNameTag(Entity entity, int age) {
        EntityNameTag nameTag = new EntityNameTag(entity, entity.getUniqueId(), age);

        LivingEntity livingEntity = (LivingEntity) entity;
        RepeatingTask repeatingTask = new RepeatingTask(main.plugin, 0, 200) {
            @Override
            public void run() {
                long time = entity.getWorld().getTime();
                if (time > 0 && time <= 160) {
                    nameTag.addAge();
                }
                if (nameTag.getAge() >= max_age) {
                    for (Entity i: entity.getPassengers()) {
                        i.remove();
                    }
                    livingEntity.setHealth(0.0);
                    data.remove(entity.getUniqueId());
                    cancel();
                }
            }
        };
        Bukkit.getScheduler().runTask(main.plugin, repeatingTask);
    }
}
