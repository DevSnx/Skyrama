package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = ((Player) event.getDamager()).getPlayer();
            if(player.getLocation().getWorld() == Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world"))){
                assert player != null;
                if(Skyrama.getGridManager().isInPlayerIsland(player, player.getLocation()) == 1) {
                    event.setCancelled(true);
                }
            }
        }
    }
}