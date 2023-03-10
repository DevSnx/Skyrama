package de.skyrama.commands.subcommands;

import de.skyrama.objects.islands.Island;
import de.skyrama.Skyrama;
import de.skyrama.interfaces.ISubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InviteDenyCommand implements ISubCommand {

    @Override
    public String getName() {
        return "deny";
    }

    @Override
    public String getDescription() {
        return "Decline player invitation";
    }

    @Override
    public String getSyntax() {
        return "/island deny playerName";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.deny")){
            if(Bukkit.getPlayer(args[1]) != null) {
                Player target = Bukkit.getPlayer(args[1]);

                Island island = Skyrama.getIslandManager().getPlayerIsland(target);

                if(!island.getInvites().isEmpty() && island.getInvites().get(player) != null) {

                    island.getInvites().remove(player);
                    player.sendMessage(Skyrama.getLocaleManager().getString("player-decline-invitation").replace("{0}", target.getName()));
                    target.sendMessage(Skyrama.getLocaleManager().getString("player-decline-your-invitation").replace("{0}", player.getName()));

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