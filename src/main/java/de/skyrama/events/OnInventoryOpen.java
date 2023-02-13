package de.skyrama.events;

import de.skyrama.Skyrama;
import de.skyrama.objects.islands.Island;
import de.skyrama.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.sql.Array;
import java.util.ArrayList;

public class OnInventoryOpen implements Listener {

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent event){

        Player player = (Player)event.getPlayer();
        InventoryView inv = event.getView();

        if(inv.getTitle().equalsIgnoreCase("§8» §bSkyrama")){

            if(Skyrama.getIslandManager().getPlayerIsland(player) != null) {
                Island is = Skyrama.getIslandManager().getPlayerIsland(player);

                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Owner§8: §b" + is.getOwner().getName());
                lore.add("§7Mitglieder§8: §a" + (is.getPlayers().size()-1)+"§7/§b10");
                inv.setItem(13, new ItemCreator().material(Material.CLOCK).lore(lore).displayName("§8» §cStatistiken").build());

            }
        }
    }
}
