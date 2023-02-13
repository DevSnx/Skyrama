package de.skyrama.objects.inventorys;

import de.skyrama.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    private Inventory Maininv;

    public InventoryManager(){
        Maininv = Bukkit.createInventory(null, 3*9, "§8» §bSkyrama");

        getInv().setItem(10, new ItemCreator().material(Material.DARK_OAK_DOOR).displayName("§8» §cNach Hause").build());
        getInv().setItem(11, new ItemCreator().material(Material.PLAYER_HEAD).displayName("§8» §bMitglieder").build());
        //getInv().setItem(13, new ItemCreator().material(Material.CLOCK).displayName("§8» §cStatistiken").build());
        getInv().setItem(15, new ItemCreator().material(Material.OAK_SAPLING).displayName("§8» §ANatur Einstellungen").build());
        getInv().setItem(15, new ItemCreator().material(Material.OAK_SAPLING).displayName("§8» §ANatur Einstellungen").build());
        getInv().setItem(16, new ItemCreator().material(Material.LEGACY_REDSTONE_COMPARATOR).displayName("§8» §cInsel Einstellungen").build());

    }

    public Inventory getInv() {
        return Maininv;
    }
}