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

        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.create") || player.isOp()){
            Island island = Skyrama.getIslandManager().getPlayerIsland(player);

            if(island != null) {
                if(island.getRank(player).equals(Rank.OWNER)) {
                    island.removePlayer(player);
                    player.sendMessage(ChatColor.GREEN + "You left your island and it was deleted.");
                    for(OfflinePlayer offlinePlayer : island.getPlayers().keySet()) {
                        if(offlinePlayer.isOnline()) {
                            Player member = offlinePlayer.getPlayer();
                            member.sendMessage(ChatColor.RED + "" + player.getName() + " deleted the island.");
                            island.removePlayer(member);
                        }
                    }
                } else {
                    island.removePlayer(player);
                    player.sendMessage(ChatColor.GREEN + "You left the island with success.");
                    for(OfflinePlayer offlinePlayer : island.getPlayers().keySet()) {
                        if(offlinePlayer.isOnline()) {
                            Player member = offlinePlayer.getPlayer();
                            member.sendMessage(ChatColor.RED + "" + player.getName() + " left your island.");
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