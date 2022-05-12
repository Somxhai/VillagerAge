package com.somxhai.events;

import com.somxhai.database.GetDataSQL;
import com.somxhai.Main;
import com.somxhai.utils.NameTagMethod;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.world.EntitiesLoadEvent;

import java.util.*;

public class EntityAge implements Listener {
    private final Set<String> worlds = new HashSet<>(Main.plugin.getConfig().getStringList("disable_worlds"));
    private final GetDataSQL data = Main.plugin.data;

    @EventHandler
    public void onChunkLoad(EntitiesLoadEvent e) {
        if (!worlds.contains(e.getWorld().toString())) {
            for (Entity entity : e.getEntities()) {
                if (entity.getType()==EntityType.VILLAGER) {
                    UUID entityID = entity.getUniqueId();
                    NameTagMethod nameTagMethod = new NameTagMethod(entity);
                    if (!data.exists(entityID)) {
                        data.createEntity(entityID, entity.getType(), 1);
                        nameTagMethod.createNameTag(1);
                    } else {
                        int database_age = data.getAge(entityID);
                        nameTagMethod.createNameTag(database_age);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onBreed(EntityBreedEvent e) {
        if (e.getEntityType()==EntityType.VILLAGER) {
            NameTagMethod nameTagMethod = new NameTagMethod(e.getEntity());
            nameTagMethod.createNameTag(1);
        }
    }
}
