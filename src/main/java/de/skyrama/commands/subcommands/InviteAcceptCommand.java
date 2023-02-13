package de.skyrama.commands.subcommands;

import de.skyrama.objects.islands.Island;
import de.skyrama.Skyrama;
import de.skyrama.interfaces.ISubCommand;
import de.skyrama.types.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InviteAcceptCommand implements ISubCommand {

    @Override
    public String getName() {
        return "accept";
    }

    @Override
    public String getDescription() {
        return "Accept player invitation";
    }

    @Override
    public String getSyntax() {
        return "/island accept playerName";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.create")){
            if(Bukkit.getPlayer(args[1]) != null) {
                Player target = Bukkit.getPlayer(args[1]);

                Island newIsland = Skyrama.getIslandManager().getPlayerIsland(target);

                if(!newIsland.getInvites().isEmpty() && newIsland.getInvites().get(player) != null) {

                    if(Skyrama.getIslandManager().getPlayerIsland(player) != null) {

                        Island island = Skyrama.getIslandManager().getPlayerIsland(player);

                        island.removePlayer(player);
                        newIsland.addPlayer(player, Rank.fromInt(1));

                    }

                    newIsland.addPlayer(player, Rank.fromInt(1));

                    target.sendMessage(Skyrama.getLocaleManager().getString("player-join-island").replace("{0}", player.getName()));

                    player.sendMessage(Skyrama.getLocaleManager().getString("player-join-island-success").replace("{0}", target.getName()));
                    player.performCommand("is home");

                } else {
                    player.sendMessage(Skyrama.getLocaleManager().getString("player-no-invited").replace("{0}", args[1]));
                }

            } else {
                player.sendMessage(Skyrama.getLocaleManager().getString("player-offline").replace("{0}", args[1]));
            }
        }else{
            player.sendMessage(Skyrama.getLocaleManager().getString("player-noperm"));
        }
    }
}
