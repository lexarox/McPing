package de.lexarox.mcping.listeners;


import de.lexarox.mcping.McPing;
import de.lexarox.mcping.util.PingToggleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;


public class chatListener implements Listener {

    private Plugin plugin = McPing.getPlugin(McPing.class);
    private Configuration config = plugin.getConfig();

    private final String color = ChatColor.translateAlternateColorCodes('&', config.getString("color"));

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();
        Player eventPlayer = event.getPlayer();
        if (message.contains("@a") && eventPlayer.hasPermission("ping.everyone")) {

            message = message.replace("@a", color + "@a" + ChatColor.RESET);
            event.setMessage(message);
            for (Player player : Bukkit.getOnlinePlayers()){
                if (PingToggleManager.isPingEnabled(player)) {
                    Sound sound = Sound.valueOf(config.getString("ping-sound"));
                    float volume = Float.valueOf(config.getString("volume"));
                    float pitch = Float.valueOf(config.getString("pitch"));

                    player.playSound(player.getLocation(), sound, volume, pitch);
                }
            }
            return;
        }



        if (eventPlayer.hasPermission("ping.use")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (message.contains(player.getName())) {


                    message = message.replace(player.getName(), color + player.getName() + ChatColor.RESET);

                    if (PingToggleManager.isPingEnabled(player)) {

                        Sound sound = Sound.valueOf(config.getString("ping-sound"));
                        float volume = Float.valueOf(config.getString("volume"));
                        float pitch = Float.valueOf(config.getString("pitch"));

                        player.playSound(player.getLocation(), sound, volume, pitch);

                    }
                }
            }

            event.setMessage(message);
        }
    }
}