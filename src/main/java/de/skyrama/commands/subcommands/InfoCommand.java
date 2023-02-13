package de.skyrama.commands.subcommands;

import de.skyrama.Skyrama;
import de.skyrama.interfaces.ISubCommand;
import de.skyrama.objects.islands.Island;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class InfoCommand implements ISubCommand {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info about island";
    }

    @Override
    public String getSyntax() {
        return "/island info";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(player.hasPermission("skyrama.*") || player.hasPermission("skyrama.info")){
            if(Skyrama.getIslandManager().getPlayerIsland(player) == null) {
                player.sendMessage("§cKeine Insel!");
            }else{
                Island is = Skyrama.getIslandManager().getPlayerIsland(player);
                player.sendMessage("######################################");
                player.sendMessage(" §cID§7: §7" + is.getId());
                player.sendMessage(" §cOWNER§7: §7" + is.getOwner());
                player.sendMessage(" §cBIOME§7: §7" + is.getBiome());
                player.sendMessage(" §cMEMBER§7: §7" + is.getPlayers());
                player.sendMessage("######################################");
            }
        }else{
            player.sendMessage(Skyrama.getLocaleManager().getString("player-noperm"));
        }
    }
}