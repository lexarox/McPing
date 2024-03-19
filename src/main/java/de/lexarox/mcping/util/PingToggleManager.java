package de.lexarox.mcping.util;

import de.lexarox.mcping.McPing;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PingToggleManager {

    public static boolean isPingEnabled(Player player){

        UUID uuid = player.getUniqueId();
        if (McPing.getCfg().contains(uuid.toString())){
            return McPing.getCfg().getConfiguration().getBoolean(uuid.toString());
        } else {
            return true;
        }

    }

    public static void togglePing(Player player){
        UUID uuid = player.getUniqueId();
        boolean currentState = isPingEnabled(player);
        McPing.getCfg().set(uuid.toString(), !currentState);
        McPing.getCfg().save();
    }

}
