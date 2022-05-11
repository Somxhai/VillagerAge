package com.somxhai;

import com.somxhai.database.GetDataSQL;
import com.somxhai.database.MySQL;
import com.somxhai.entity_age.EntityAge;
import com.somxhai.entity_age.OnEntityDie;
import com.somxhai.nametag.EntityNameTag;
import com.somxhai.utils.TempData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.List;

public final class main extends JavaPlugin{

    public static main plugin;
    public TempData tempData = new TempData();
    public MySQL MySQL = new MySQL("villagerhasage");
    public GetDataSQL data = new GetDataSQL();;
    @Override
    public void onEnable() {
        plugin = this;
        databaseConnect();
        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EntityAge(), this);
        getServer().getPluginManager().registerEvents(new OnEntityDie(), this);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MySQL.disconnect();
        tempData.removeNametag();

    }

    public void databaseConnect() {
        try {
            MySQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("Database not connected");
        }
        if (MySQL.isConnected()) {
            getLogger().info("Connected to the database");
            data.createTable();
        }
    }
}
