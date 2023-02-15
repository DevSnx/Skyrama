package de.skyrama.events;

import com.sk89q.util.ReflectionUtil;
import de.skyrama.Skyrama;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerDeath implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event){

        Player player = event.getEntity().getPlayer();

        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("island.respawnInventorySave")) {
            event.setKeepInventory(true);
        }

        new BukkitRunnable(){
            @Override
            public void run(){
                player.spigot().respawn();
            }
        }.runTask(Skyrama.getInstance());

    }
}
