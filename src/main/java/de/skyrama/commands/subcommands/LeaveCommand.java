package de.skyrama.commands.subcommands;

import de.skyrama.objects.islands.Island;
import de.skyrama.Skyrama;
import de.skyrama.interfaces.ISubCommand;
import de.skyrama.types.Rank;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class LeaveCommand implements ISubCommand {

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "Leave the current island";
    }

    @Override
    public String getSyntax() {
        return "/island leave";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.leave")){
            Island island = Skyrama.getIslandManager().getPlayerIsland(player);

            if(island != null) {
                if(island.getRank(player).equals(Rank.OWNER)) {
                    island.removePlayer(player);

                    //player.sendMessage(ChatColor.GREEN + "You left your island and it was deleted.");
                    player.sendMessage(Skyrama.getLocaleManager().getString("player-leave-and-delete-island"));

                    for(OfflinePlayer offlinePlayer : island.getPlayers().keySet()) {
                        if(offlinePlayer.isOnline()) {
                            Player member = offlinePlayer.getPlayer();
                            //member.sendMessage(ChatColor.RED + "" + player.getName() + " deleted the island.");
                            member.sendMessage(Skyrama.getLocaleManager().getString("target-leave-and-delete-island").replace("{0}", player.getName()));
                            island.removePlayer(member);
                        }
                    }
                } else {
                    island.removePlayer(player);
                    //player.sendMessage(ChatColor.GREEN + "You left the island with success.");
                    player.sendMessage(Skyrama.getLocaleManager().getString("player-leave-island"));

                    for(OfflinePlayer offlinePlayer : island.getPlayers().keySet()) {
                        if(offlinePlayer.isOnline()) {
                            Player member = offlinePlayer.getPlayer();
                            //member.sendMessage(ChatColor.RED + "" + player.getName() + " left your island.");
                            member.sendMessage(Skyrama.getLocaleManager().getString("target-leave-island").replace("{1}", player.getName()));
                        }
                    }
                }
            } else {
                player.sendMessage(Skyrama.getLocaleManager().getString("player-no-island"));
            }
        }else{
            player.sendMessage(Skyrama.getLocaleManager().getString("player-noperm"));
        }
    }
}