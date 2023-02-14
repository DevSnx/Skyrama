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
        String message = event.getMessage();

        if(message.startsWith("@is")){

            message.replace("@is", "§f");
            if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("chat.island-chat") == true){
                event.setCancelled(true);
                if(Skyrama.getIslandManager().getPlayerIsland(event.getPlayer()) != null){
                    Island is = Skyrama.getIslandManager().getPlayerIsland(event.getPlayer());
                    String messages = Skyrama.getPlugin(Skyrama.class).getConfig().getString("chat.island-chat-message");
                    messages = messages.replace("{msg}", event.getMessage());
                    messages = messages.replace("{0}", event.getPlayer().getName());
                    messages = messages.replace("&", "§");
                    for(Player all : Bukkit.getOnlinePlayers()){
                        if(is.getPlayers().containsKey(all.getPlayer()) || is.getOwner().getPlayer().equals(all)){
                            all.sendMessage(messages);
                        }
                    }
                }else{
                    player.sendMessage(Skyrama.getPlugin(Skyrama.class).getConfig().getString("player-no-island"));
                }
            }
        }
    }
}