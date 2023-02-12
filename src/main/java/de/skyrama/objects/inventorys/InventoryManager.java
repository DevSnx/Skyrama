package de.skyrama.objects.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    private Inventory inv;

    public InventoryManager(){
        inv = Bukkit.createInventory(null, 5*9, "Skyrama");
        for(int i = 0; i < 5*9; i++) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
    }

    public Inventory getInv() {
        return inv;
    }
}