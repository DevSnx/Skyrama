package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlaces(BlockPlaceEvent event) {

        Player player = event.getPlayer();
        if(event.getBlock().getLocation().getWorld() == Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world"))){
            if(Skyrama.getGridManager().isInPlayerIsland(event.getPlayer(), event.getBlock().getLocation()) == 2) {
                event.setCancelled(false);
            }else{
                event.getPlayer().sendMessage(Skyrama.getLocaleManager().getString("player-place"));
                event.setCancelled(true);
            }
        }
    }
}