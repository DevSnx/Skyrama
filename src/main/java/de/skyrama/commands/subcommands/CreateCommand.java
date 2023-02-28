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
                if(Skyrama.getVaultManager().isVault()){
                    if(Skyrama.getVaultManager().getMoney(player) >= Skyrama.getPlugin(Skyrama.class).getConfig().getDouble("vault.island-costs")){
                        Skyrama.getVaultManager().removeMoney(player, Skyrama.getPlugin(Skyrama.class).getConfig().getDouble("vault.island-costs"));
                        Skyrama.getIslandManager().create(player);
                    }else{
                        player.sendMessage(Skyrama.getLocaleManager().getString("player-create-island-no-money"));
                    }
                }else{
                    Skyrama.getIslandManager().create(player);
                }
            } else {
                player.sendMessage(Skyrama.getLocaleManager().getString("player-already-have-island"));
            }
        }else{
            player.sendMessage(Skyrama.getLocaleManager().getString("player-noperm"));
        }
    }
}
