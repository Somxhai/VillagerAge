package com.somxhai;

import com.somxhai.commands.*;

import com.somxhai.database.GetDataSQL;
import com.somxhai.database.MySQL;
import com.somxhai.events.EggEvents;
import com.somxhai.events.EntityAge;
import com.somxhai.events.OnEntityDie;
import com.somxhai.utils.TempData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Objects;

public final class Main extends JavaPlugin{

    public static Main plugin;
    public TempData tempData = new TempData();
    public MySQL MySQL = new MySQL("villagerhasage");

    public GetDataSQL data = new GetDataSQL();


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
        getServer().getPluginManager().registerEvents(new EggEvents(), this);

        Objects.requireNonNull(getCommand("vilage")).setExecutor(new VilageCommand());
        Objects.requireNonNull(getCommand("vilage")).setTabCompleter(new TabCompleter());

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        tempData.removeNametag();
        data.onServerCloseUpdate();
        MySQL.disconnect();
    }
    public void databaseConnect() {
        try {
            MySQL.connect();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Database not connected");
        }
        if (MySQL.isConnected()) {
            getLogger().info("Connected to the database");
            data.createTable();
        }
    }
}
