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

        if(Skyrama.getGridManager().isInPlayerIsland(event.getPlayer(), event.getBlock().getLocation()) == 2) {
            event.setCancelled(false);
            player.sendMessage("DEINE INSEL!");
        }else{
            if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.place") || player.isOp()){
                event.setCancelled(false);
                player.sendMessage("NICHT DEINE INSEL, ABER RECHTE!");
            }else{
                event.getPlayer().sendMessage(Skyrama.getLocaleManager().getString("player-place"));
                event.setCancelled(true);
                player.sendMessage("keine Rechte!");
            }
        }
    }
}