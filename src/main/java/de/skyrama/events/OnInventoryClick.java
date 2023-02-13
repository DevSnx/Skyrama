package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack item = event.getCurrentItem();
        if (event.getHotbarButton() != -1) {
            item = event.getView().getBottomInventory().getItem(event.getHotbarButton());
            if (item == null)
                item = event.getCurrentItem();
        }
        if (item == null || item.getType() == Material.AIR){
            return;
        }
        if (event.getView().getTitle().equals("§8» §bSkyrama")) {
            event.setCancelled(true);
            if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cNach Hause")){

                if(Skyrama.getIslandManager().getPlayerIsland(player) != null) {

                    player.teleport(Skyrama.getIslandManager().getPlayerIsland(player).getSpawn());

                }

            }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bMitglieder")){



            }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cStatistiken")){



            }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §ANatur Einstellungen")){



            }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cInsel Einstellungen")){



            }
            return;
        }
    }
}
