package com.somxhai.utils;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TempData {

    private final List<Entity> temp = new ArrayList<>();
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
    private final HashMap<ItemMeta, Integer> itemMeta = new HashMap<>();
    public void addToItemMeta(ItemStack e, int amount) {
        ItemMeta meta = e.getItemMeta();
        if (itemMeta.containsKey(meta)) {
            itemMeta.replace(meta, amount);
        } else {
            itemMeta.put(meta, amount);
        }
    }
}
