package de.skyrama.utils;

import de.skyrama.Skyrama;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author DevSnx
 * @since 26.02.2023
 */
public class VaultManager {

    private boolean isVault = false;
    private static Economy econ = null;

    public VaultManager(){
        setupEconomy();
    }

    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getServer().getConsoleSender().sendMessage("#     §cVault not Found!     #");
            return false;
        }
        if(Skyrama.getPlugin(Skyrama.class).getConfig().getBoolean("vault.enable") == false){
            Bukkit.getServer().getConsoleSender().sendMessage("#       §aVault Found!       #");
            Bukkit.getServer().getConsoleSender().sendMessage("#  §bVault Support disabled  #");
            return econ == null;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        Bukkit.getServer().getConsoleSender().sendMessage("#       §aVault Found!       #");
        Bukkit.getServer().getConsoleSender().sendMessage("#  §bVault Support enabled   #");
        econ = rsp.getProvider();
        Skyrama.getVaultManager().isVault = true;
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public double getMoney(Player player){
        return econ.getBalance(player);
    }

    public void removeMoney(Player player, double money){
       econ.withdrawPlayer(player, money);
    }

    public void addMoney(Player player, double money){
        econ.depositPlayer(player, money);
    }

    public boolean isVault() {
        return isVault;
    }
}