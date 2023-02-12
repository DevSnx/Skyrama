package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if(Skyrama.getGridManager().isInPlayerIsland(event.getPlayer(), event.getPlayer().getLocation()) == 1) {
            if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.create") || player.isOp()){

                event.setCancelled(false);
            }else{
                event.getPlayer().sendMessage(Skyrama.getLocaleManager().getString("player-place"));
                event.setCancelled(true);
            }
        }
    }
}