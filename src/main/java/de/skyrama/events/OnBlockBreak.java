package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if(Skyrama.getGridManager().isInPlayerIsland(event.getPlayer(), event.getBlock().getLocation()) == 1) {
            if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.create") || player.isOp()){

                event.setCancelled(false);
            }else{
                event.getPlayer().sendMessage(Skyrama.getLocaleManager().getString("player-break"));
                event.setCancelled(true);
            }
        }
    }
}