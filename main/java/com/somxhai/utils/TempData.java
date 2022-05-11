package com.somxhai.utils;

import com.somxhai.entity_age.EntityAge;
import com.somxhai.main;
import com.somxhai.nametag.EntityNameTag;
import org.bukkit.entity.Entity;

import java.util.*;

public class TempData {
    private final List<Entity> temp = new ArrayList<>();
    private final List<EntityNameTag> toReload = new ArrayList<>();
    public void putToTemp(Entity a) {
        temp.add(a);
    }
    public List<Entity> getTemp() {
        return temp;
    }
    public void removeNametag() {
        for (Entity i : temp) {
            i.remove();
        }
    }
    public void addToReload(EntityNameTag e) {
        toReload.add(e);
    }
    public void removeToreload(EntityNameTag e) {
        toReload.remove(e);
    }
}
