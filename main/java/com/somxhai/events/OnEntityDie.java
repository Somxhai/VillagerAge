package com.somxhai.events;

import com.somxhai.database.GetDataSQL;
import com.somxhai.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;


public class OnEntityDie implements Listener {
    private final GetDataSQL data = Main.plugin.data;
    @EventHandler
    public void onEntityDie(EntityDeathEvent e) {
        if (data.exists(e.getEntity().getUniqueId())) {
            UUID id = e.getEntity().getUniqueId();
            if (data.exists(id)){
                data.remove(id);
            }
        }
    }
}