package de.skyrama.objects.islands;

import de.skyrama.Skyrama;
import de.skyrama.types.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Biome;

import java.util.*;

public class IslandManager {

    public Set<Island> islands;

    public int islandID;

    public IslandManager() { this.initialise(); };

    public void initialise() {

        this.islands = new HashSet<>();

    }

    public void loadIslands() {

        this.islands = IslandDao.getIslands();

    }

    public void create(OfflinePlayer owner) {
        int newID = (Skyrama.getIslandManager().islands.size()+1);
        islandID = newID;

        IslandDao.addIsland();

        Location location = Skyrama.getGridManager().getCenterFromId(newID);

        Location spawn = new Location(Bukkit.getWorld(Skyrama.getPlugin(Skyrama.class).getConfig().getString("general.world")), location.getBlockX(), 100, location.getBlockZ() + 2);

        Island island = new Island(newID, Biome.PLAINS, 0, spawn);
        this.islands.add(island);
        island.addPlayer(owner, Rank.OWNER);

        island.setSpawn(spawn);
        island.save();

        Skyrama.getSchematicManager().load(Skyrama.getPlugin(Skyrama.class).getConfig().getString("island.schematic"), location.getX(), location.getY(), location.getZ());
        owner.getPlayer().teleport(spawn);

        owner.getPlayer().sendMessage(Skyrama.getLocaleManager().getString("player-create-island"));

    }

    public Island getIslandOwnedBy(OfflinePlayer player) {

        return this.getIslands().stream().filter(island -> island.getOwner().getUniqueId().equals(player.getUniqueId())).findAny().orElse(null);

    }

    public Island getPlayerIsland(OfflinePlayer player) {

        return this.getIslands().stream().filter(island -> island.getPlayers().keySet().stream().filter(offlinePlayer -> offlinePlayer.getUniqueId().equals(player.getUniqueId())).findAny().orElse(null) != null).findAny().orElse(null);


    }

    public Set<Island> getIslands() {

        return this.islands;

    }
}