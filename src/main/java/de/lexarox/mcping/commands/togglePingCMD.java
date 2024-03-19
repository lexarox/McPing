package de.lexarox.mcping.commands;

import de.lexarox.mcping.McPing;
import de.lexarox.mcping.util.PingToggleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class togglePingCMD implements CommandExecutor {

    private Plugin plugin = McPing.getPlugin(McPing.class);
    private Configuration config = plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String notplayer = ChatColor.translateAlternateColorCodes('&', config.getString("only-for-players-message"));
        if (!(sender instanceof Player)) { // §8[§2✔ §aSuccess§8] §f⌁ // §8[§4✘ §cError§8] §f⌁ // §8[§8¡ §7Info§8] §f⌁
            sender.sendMessage(notplayer);
            return true;
        }

        String pingon = ChatColor.translateAlternateColorCodes('&', config.getString("ping-enable-message"));
        String pingoff = ChatColor.translateAlternateColorCodes('&', config.getString("ping-disable-message"));

        Player player = (Player) sender;

        // Toggle the ping state for the player
        PingToggleManager.togglePing(player);

        // Inform the player about the current state
        boolean pingEnabled = PingToggleManager.isPingEnabled(player);


        if (pingEnabled) {
            player.sendMessage(pingon);
        } else {
            player.sendMessage(pingoff);
        }


        return true;
    }
}
