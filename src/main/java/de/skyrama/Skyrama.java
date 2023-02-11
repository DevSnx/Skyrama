package de.skyrama;

import de.skyrama.commands.CommandManager;
import de.skyrama.events.*;
import de.skyrama.objects.grids.GridManager;
import de.skyrama.objects.inventorys.InventoryManager;
import de.skyrama.objects.islands.IslandManager;
import de.skyrama.objects.locales.LocaleManager;
import de.skyrama.objects.permissions.PermissionsManager;
import de.skyrama.objects.schematics.SchematicManager;
import de.skyrama.storage.SqlManager;
import de.skyrama.events.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skyrama extends JavaPlugin {

    private static GridManager gridManager;
    private static SqlManager sqlManager;
    private static IslandManager islandManager;
    private static SchematicManager schematicManager;
    private static LocaleManager localeManager;

    private static InventoryManager inventoryManager;

    private static PermissionsManager permissionsManager;



    @Override
    public void onEnable() {

        this.initConfig();
        this.initObjects();
        this.initEvents();
        this.initCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initConfig() {

        // Load default config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        // Load default locales
        saveResource("locales/en_US.yml", false);
        saveResource("locales/de_DE.yml", false);
        // Load default schematics
        saveResource("schematics/island.schematic", false);
        // Load default Permissions
        saveResource("permissions.yml", false);
    }

    public void initCommands() {

        getCommand("island").setExecutor(new CommandManager());

    }

    public void initObjects() {

        gridManager = new GridManager();
        sqlManager = new SqlManager();
        islandManager = new IslandManager();
        schematicManager = new SchematicManager();
        localeManager = new LocaleManager();
        permissionsManager = new PermissionsManager();
        inventoryManager = new InventoryManager();

        islandManager.loadIslands();

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

    public static PermissionsManager getPermissionsManager() {

        return permissionsManager;

    }

    public static InventoryManager getInventoryManager() {

        return inventoryManager;
        
    }
}