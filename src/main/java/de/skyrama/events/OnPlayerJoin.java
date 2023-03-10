package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("chat.join-quit-messages") == true){
            event.setJoinMessage(Skyrama.getLocaleManager().getString("player-join-message").
                    replace("{0}", event.getPlayer().getName()));
        }

        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("island.spawnIslandLogin")) {
            if (Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()) != null) {
                Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn().setWorld(Bukkit.getWorld((String) Skyrama.getPlugin(Skyrama.class).getConfig().get("general.world")));
                event.getPlayer().teleport(Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()).getSpawn());
            }
        }
    }
}