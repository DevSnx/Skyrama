package de.skyrama;

import de.skyrama.commands.CommandManager;
import de.skyrama.events.*;
import de.skyrama.objects.grids.GridManager;
import de.skyrama.objects.inventorys.InventoryManager;
import de.skyrama.objects.islands.IslandManager;
import de.skyrama.objects.locales.LocaleManager;
import de.skyrama.objects.schematics.SchematicManager;
import de.skyrama.storage.SqlManager;
import de.skyrama.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Skyrama extends JavaPlugin {

    public static Skyrama instance;
    private static GridManager gridManager;
    private static SqlManager sqlManager;
    private static IslandManager islandManager;
    private static SchematicManager schematicManager;
    private static LocaleManager localeManager;
    private static InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        // Plugin start logic
        instance = this;

        getServer().getConsoleSender().sendMessage("#############################");
        getServer().getConsoleSender().sendMessage("#                           #");
        getServer().getConsoleSender().sendMessage("#     §aSkyrama §c" + getDescription().getVersion() + "-ALPHA     §f#");
        getServer().getConsoleSender().sendMessage("#   by §bDevSnx §f& §bkozennnn    §f#");
        getServer().getConsoleSender().sendMessage("#                           #");
        this.initFiles();
        this.initObjects();
        this.initEvents();
        this.initCommands();
        getServer().getConsoleSender().sendMessage("#                           #");
        getServer().getConsoleSender().sendMessage("#############################");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    public void initFiles() {

        // Load default config when not exists
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        File file = new File(Skyrama.getInstance().getDataFolder()+"/locales",  "en_US.yml");
        File file2 = new File(Skyrama.getInstance().getDataFolder()+"/locales",  "de_DE.yml");

        if(!file.exists()){
            saveResource("locales/en_US.yml", false);
        }
        if(!file2.exists()){
            saveResource("locales/de_DE.yml", false);
        }
    }

    public void initCommands() {

        getCommand("island").setExecutor(new CommandManager());

    }

    public void initObjects() {

        gridManager = new GridManager();
        sqlManager = new SqlManager();

        initDatabase();

        islandManager = new IslandManager();
        schematicManager = new SchematicManager();
        localeManager = new LocaleManager();
        inventoryManager = new InventoryManager();

        islandManager.loadIslands();

    }

    public void initDatabase() {
        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS `islands` " +
                        "(id INT, " +
                        "biome VARCHAR(100), " +
                        "extension_level INT, " +
                        "spawn_x FLOAT, " +
                        "spawn_y FLOAT, " +
                        "spawn_z FLOAT, " +
                        "spawn_yaw FLOAT, " +
                        "spawn_pitch FLOAT, UNIQUE KEY (id))"
        )) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }
        try (Connection conn = Skyrama.getSqlManager().getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS `islands_users` " +
                        "(uuid VARCHAR(100), " +
                        "island_id VARCHAR(100)," +
                        "rank INT," +
                        " UNIQUE KEY (uuid))"
        )) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Something went wrong. " + e);
        }
    }

    public void initEvents() {

        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new OnEntityTarget(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDamage(), this);
        getServer().getPluginManager().registerEvents(new OnEntityDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new OnBlockClick(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryOpen(), this);

    }

    public static GridManager getGridManager() {

        return gridManager;

    }

    public static SqlManager getSqlManager() {

        return sqlManager;

    }

    public static IslandManager getIslandManager() {

        return islandManager;

    }

    public static SchematicManager getSchematicManager() {

        return schematicManager;

    }

    public static LocaleManager getLocaleManager() {

        return localeManager;

    }

    public static InventoryManager getInventoryManager() {

        return inventoryManager;

    }

    public static Skyrama getInstance() {

        return instance;

    }
}