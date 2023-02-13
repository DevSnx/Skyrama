package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class OnEntityTarget implements Listener {

    @EventHandler
    public void onTarget(EntityTargetEvent event) {

        if(event.getTarget() instanceof Player) {

            Player player = ((Player) event.getTarget()).getPlayer();

            assert player != null;
            if(player.getLocation().getWorld() == Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world"))){
                if(Skyrama.getGridManager().isInPlayerIsland(player, player.getLocation()) == 1) {
                    event.setCancelled(true);
                }
            }
        }
    }
}