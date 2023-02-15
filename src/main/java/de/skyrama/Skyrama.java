package de.skyrama;

import de.skyrama.commands.CommandManager;
import de.skyrama.events.*;
import de.skyrama.objects.grids.GridManager;
import de.skyrama.objects.inventorys.InventoryManager;
import de.skyrama.objects.islands.IslandManager;
import de.skyrama.objects.locales.LocaleManager;
import de.skyrama.objects.schematics.SchematicManager;
import de.skyrama.storage.SqlManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
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
    public static String prefix;

    @Override
    public void onEnable() {
        // Plugin start logic
        instance = this;

        getServer().getConsoleSender().sendMessage("#############################");
        getServer().getConsoleSender().sendMessage("#                           #");
        getServer().getConsoleSender().sendMessage("#   §aSkyrama §c" + getDescription().getVersion() + "-ALPHA     §f#");
        getServer().getConsoleSender().sendMessage("#   by §bDevSnx §f& §bkozennnn    §f#");
        getServer().getConsoleSender().sendMessage("#                           #");
        getServer().getConsoleSender().sendMessage("#         §cLoading...        §f#");
        this.initFiles();
        this.initObjects();
        this.initEvents();
        this.initCommands();
        getServer().getConsoleSender().sendMessage("#     §aSucsess Loading!      §f#");
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

        // Load Languages  when not exists
        File file = new File(Skyrama.getInstance().getDataFolder()+"/locales",  "en_US.yml");
        File file2 = new File(Skyrama.getInstance().getDataFolder()+"/locales",  "de_DE.yml");

        if(!file.exists()){
            saveResource("locales/en_US.yml", false);
        }
        if(!file2.exists()){
            saveResource("locales/de_DE.yml", false);
        }

        saveResource("schematics/island.schem", false);
    }

    public void initCommands() {

        getCommand("island").setExecutor(new CommandManager());

    }

    public void initObjects() {

        gridManager = new GridManager();
        sqlManager = new SqlManager();

        sqlManager.create();

        islandManager = new IslandManager();
        schematicManager = new SchematicManager();
        localeManager = new LocaleManager();
        inventoryManager = new InventoryManager();

        islandManager.loadIslands();

    }

    public void initEvents() {

        PluginManager load = getServer().getPluginManager();

        load.registerEvents(new OnEntityTarget(), this);
        load.registerEvents(new OnEntityDamage(), this);
        load.registerEvents(new OnEntityDamageByEntity(), this);

        load.registerEvents(new OnInventoryClick(), this);
        load.registerEvents(new OnInventoryOpen(), this);

        load.registerEvents(new OnBlockPlace(), this);
        load.registerEvents(new OnBlockBreak(), this);

        load.registerEvents(new OnAsychrnPlayerChat(), this);

        load.registerEvents(new OnPlayerInteract(), this);
        load.registerEvents(new OnPlayerRespawn(), this);
        load.registerEvents(new OnPlayerJoin(), this);
        load.registerEvents(new OnPlayerQuit(), this);
        load.registerEvents(new OnPlayerDeath(), this);

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

    public static String getPrefix() {
        return prefix;
    }
}