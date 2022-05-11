package com.somxhai.entity_age;

import com.somxhai.main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;


public class OnEntityDie implements Listener {

    @EventHandler
    public void onEntityDie(EntityDeathEvent e) {
        if (e.getEntityType() == EntityType.VILLAGER) {
            UUID id = e.getEntity().getUniqueId();
            if (main.plugin.data.exists(id)){
                for (Entity ent : e.getEntity().getPassengers()) {
                    ent.remove();
                }
                main.plugin.data.remove(id);
            }
        }
    }
}