package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getLocation().getWorld() == Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world"))){
            if(event.getClickedBlock() != null){
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if(event.getClickedBlock().getType() == Material.CHEST
                            || event.getClickedBlock().getType() == Material.FURNACE
                            || event.getClickedBlock().getType() == Material.MINECART
                            || event.getClickedBlock().getType() == Material.LEGACY_BOAT
                            || event.getClickedBlock().getType() == Material.LEGACY_DROPPER){
                        if (Skyrama.getGridManager().isInPlayerIsland(event.getPlayer(), event.getPlayer().getLocation()) == 1) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
