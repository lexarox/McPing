package de.lexarox.mcping.commands;

import de.lexarox.mcping.McPing;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;


public class pingReloadCMD implements CommandExecutor {

    private Plugin plugin = McPing.getPlugin(McPing.class);
    private Configuration config = plugin.getConfig();



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        //reload the plugin:

        PluginManager pm = Bukkit.getPluginManager();

        Plugin pl = pm.getPlugin(plugin.getName());

        pm.disablePlugin(pl);
        pm.getPlugin(plugin.getName()).reloadConfig();
        pm.enablePlugin(pl);

        String reload = ChatColor.translateAlternateColorCodes('&', config.getString("plugin-reload-message"));


        sender.sendMessage(reload);


        return false;
    }
}
