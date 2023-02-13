package de.skyrama.commands.subcommands;

import de.skyrama.objects.islands.Island;
import de.skyrama.Skyrama;
import de.skyrama.interfaces.ISubCommand;
import de.skyrama.types.Rank;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InviteCommand implements ISubCommand {

    @Override
    public String getName() {
        return "invite";
    }

    @Override
    public String getDescription() {
        return "Invite player to join your island";
    }

    @Override
    public String getSyntax() {
        return "/island invite";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.invite")){
            Island island = Skyrama.getIslandManager().getPlayerIsland(player);

            if(island != null) {
                if(island.getRank(player) == Rank.OWNER) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (Skyrama.getIslandManager().getPlayerIsland(target) != null && Skyrama.getIslandManager().getPlayerIsland(target) == island) {
                            player.sendMessage(Skyrama.getLocaleManager().getString("player-already-on-island").replace("{0}", target.getName()));
                            return;
                        }

                        if (island.getInvites() != null && island.getInvites().get(target) == null) {

                            player.sendMessage(Skyrama.getLocaleManager().getString("player-invite-island").replace("{0}", target.getName()));
                            target.sendMessage(Skyrama.getLocaleManager().getString("target-invite-island").replace("{1}", player.getName()));

                            island.getInvites().put(target, player);
                        } else {
                            player.sendMessage(Skyrama.getLocaleManager().getString("player-already-invited").replace("{0}", island.getInvites().get(target).getName()).replace("{1}", target.getName()));
                        }
                    } else {
                        player.sendMessage(Skyrama.getLocaleManager().getString("player-offline").replace("{0}", args[1]));
                    }
                } else {
                    player.sendMessage(Skyrama.getLocaleManager().getString("player-no-owner"));
                }
            } else {
                player.sendMessage(Skyrama.getLocaleManager().getString("player-no-island"));
            }
        }else{
            player.sendMessage(Skyrama.getLocaleManager().getString("player-noperm"));
        }
    }
}
