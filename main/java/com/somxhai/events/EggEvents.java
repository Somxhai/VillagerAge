package com.somxhai.events;

import com.somxhai.database.GetDataSQL;
import com.somxhai.Main;
import com.somxhai.utils.NameTagMethod;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.List;

public class EggEvents implements Listener {
    private final GetDataSQL data = Main.plugin.data;
    private String lastLine = Main.plugin.getConfig().getString("custom_egg_last_line");
    // lore at index 2
    private int getAge(List<String> lore) {
        String loreToString = String.join(" ", lore.get(2));
        List<String> ageLore = Arrays.asList(loreToString.split(" "));
        return Integer.parseInt(ageLore.get(1).replace("§6",""));
    }
    @EventHandler
    public void onSpawnEgg(PlayerInteractEvent e) {
        // get amount of age

        ItemStack usingItem = e.getItem();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && usingItem != null) {
            if (usingItem.getItemMeta() != null && usingItem.getItemMeta().getLore() != null) {
                ItemMeta thisItemMeta = usingItem.getItemMeta();
                int age = getAge(thisItemMeta.getLore());
                String toCheckLine = thisItemMeta.getLore().get(thisItemMeta.getLore().size()-1);
                if (lastLine == null) {
                    lastLine = toCheckLine;
                }
                if (ChatColor.translateAlternateColorCodes('&', lastLine).equals(toCheckLine)) {
                    e.setCancelled(true); // cancel spawning normal villager
                    Location location = e.getClickedBlock().getLocation();
                    location.setY(location.getY()+1); // spawn on the block
                    Player player = e.getPlayer();
                    // do not remove item from creative mode
                    Entity entity = e.getPlayer().getWorld().spawnEntity(location, EntityType.VILLAGER);
                    NameTagMethod nameTagMethod = new NameTagMethod(entity);
                    nameTagMethod.createNameTag(age);
                    data.createEntity(nameTagMethod.getEntity().getUniqueId(), entity.getType(), age);
                    if (player.getGameMode() != GameMode.CREATIVE && player.getItemInUse() != null) {
                        int item_amount = player.getItemInUse().getAmount();
                        player.getInventory().getItemInMainHand().setAmount(item_amount-1);
                    }
                }
            }
        }
    }
    // prevent from player using dispenser omegalul
    @EventHandler
    public void onDispenser(BlockDispenseEvent e) {
        ItemMeta thisItemMeta = e.getItem().getItemMeta();
        if (thisItemMeta != null && thisItemMeta.getLore() != null) {
            String toCheckLine = thisItemMeta.getLore().get(thisItemMeta.getLore().size()-1);
            if (ChatColor.translateAlternateColorCodes('&', lastLine).equals(toCheckLine)) {
                e.setCancelled(true);
            }
        }
    }
}
