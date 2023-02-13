package de.skyrama.events;

import de.skyrama.Skyrama;
import de.skyrama.objects.islands.Island;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnAsychrnPlayerChat implements Listener {

    @EventHandler
    public void onAsynchronPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(event.getMessage().startsWith("@is")){
            if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("chat.island-chat") == true){
                event.setCancelled(true);
                Island is = Skyrama.getIslandManager().getPlayerIsland(event.getPlayer());
                String message = Skyrama.getPlugin(Skyrama.class).getConfig().getString("chat.island-chat-message").
                        replace("{message}", event.getMessage().replace("{0}", event.getPlayer().getName()));;

                for(Player all : Bukkit.getOnlinePlayers()){
                    if(is.getPlayers().containsKey(all.getPlayer())){
                        all.sendMessage(message);
                    }
                }
            }
        }
    }
}