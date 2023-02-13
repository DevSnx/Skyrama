package de.skyrama.commands.subcommands;

import de.skyrama.Skyrama;
import de.skyrama.interfaces.ISubCommand;
import org.bukkit.entity.Player;

public class CreateCommand implements ISubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create an island";
    }

    @Override
    public String getSyntax() {
        return "/island create";
    }

    @Override
    public void perform(Player player, String[] args) {

        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.create")){
            if(Skyrama.getIslandManager().getPlayerIsland(player) == null) {
                Skyrama.getIslandManager().create(player);
            } else {
                player.sendMessage(Skyrama.getLocaleManager().getString("player-already-have-island"));
            }
        }else{
            player.sendMessage(Skyrama.getLocaleManager().getString("player-noperm"));
        }
    }
}
