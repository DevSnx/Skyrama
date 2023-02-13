package de.skyrama.events;

import de.skyrama.Skyrama;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("join-quit-messages")){
            event.setQuitMessage(Skyrama.getLocaleManager().getString("player-quit-message").replace("{0}", event.getPlayer().getName()));
        }
    }
}