package de.skyrama.objects.grids;

import de.skyrama.objects.islands.Island;
import de.skyrama.Skyrama;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GridManager {

    public GridManager() { this.initialise(); }

    public void initialise() {}

    public Location getCenterFromId(int id) {

        int x = (id*1000);
        int z = (int) (Math.floor(x / 384500)*1000);

        return new Location(Bukkit.getWorld(Skyrama.getPlugin(Skyrama.class).getConfig().getString("general.world")), x, 100, z);

    }

    public int isInPlayerIsland(Player player, Location location) {

        if(location.getWorld() == Bukkit.getWorld(Skyrama.getPlugin(Skyrama.class).getConfig().getString("general.world"))) {
            if(Skyrama.getIslandManager().getPlayerIsland(player) != null) {
                Island island = Skyrama.getIslandManager().getPlayerIsland(player);
                Location center = getCenterFromId(island.getId());

                int minX = center.getBlockX() - 256;
                int maxX = center.getBlockX() + 256;

                int minZ = center.getBlockZ() - 256;
                int maxZ = center.getBlockZ() + 256;

                if (location.getBlockX() >= minX && location.getBlockX() <= maxX) {
                    if(location.getBlockZ() >= minZ && location.getBlockZ() <= maxZ) {
                        return 2;
                    }
                }
                return 1;
            }
            return 1;
        }
        return 0;
    }
}