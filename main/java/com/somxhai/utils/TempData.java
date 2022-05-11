package com.somxhai.utils;

import com.somxhai.nametag.EntityNameTag;
import org.bukkit.entity.Entity;

import java.util.*;

public class TempData {
    private final List<Entity> temp = new ArrayList<>();

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
    private final HashMap<UUID, Integer> toUpdate = new HashMap<>();
    public void addToHashMap(UUID id, int age) {
        boolean hasKey = toUpdate.containsKey(id);
        if (hasKey) {
            toUpdate.replace(id, age);
        } else {
            toUpdate.put(id, age);
        }
    }

    public HashMap<UUID, Integer> getToUpdate() {
        return toUpdate;
    }
}
