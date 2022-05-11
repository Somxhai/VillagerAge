package com.somxhai.database;

import com.somxhai.main;
import org.bukkit.entity.EntityType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GetDataSQL {

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = main.plugin.MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS entityhasage "
                    + "(ENTITYID VARCHAR(100), ENTITYTYPE VARCHAR(100), AGE INT(100), PRIMARY KEY (ENTITYID))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createEntity(UUID ID, EntityType entityType, int age) {
        try {
            if (!exists(ID)) {
                PreparedStatement ps2 = main.plugin.MySQL.getConnection().prepareStatement("INSERT IGNORE INTO entityhasage (ENTITYID, ENTITYTYPE, AGE) VALUES (?,?,?)");
                ps2.setString(1, ID.toString());
                ps2.setString(2, entityType.toString());
                ps2.setInt(3, age);
                ps2.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setAge(UUID ID, int age) {
        try {
            PreparedStatement ps = main.plugin.MySQL.getConnection().prepareStatement("UPDATE entityhasage SET AGE=? WHERE ENTITYID=?");
            ps.setInt(1, age);
            ps.setString(2, ID.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getAge(UUID id) {
        try {
            PreparedStatement ps = main.plugin.MySQL.getConnection().prepareStatement("SELECT AGE FROM entityhasage WHERE ENTITYID=?");
            ps.setString(1, id.toString());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return result.getInt("AGE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public boolean exists(UUID ID) {
        try {
            PreparedStatement ps = main.plugin.MySQL.getConnection().prepareStatement("SELECT * FROM entityhasage WHERE ENTITYID=?");
            ps.setString(1, ID.toString());
            ResultSet result = ps.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void remove(UUID ID) {
        try {
            PreparedStatement ps = main.plugin.MySQL.getConnection().prepareStatement("DELETE FROM entityhasage WHERE ENTITYID=?");
            ps.setString(1, ID.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void onServerCloseUpdate(HashMap<UUID, Integer> data) {
        if (!data.isEmpty()) {
            for (Map.Entry<UUID, Integer> entry : data.entrySet()) {
                setAge(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("saving data to database");
    }
}
