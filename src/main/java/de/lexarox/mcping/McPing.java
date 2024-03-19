package de.lexarox.mcping;

import de.lexarox.mcping.commands.pingReloadCMD;
import de.lexarox.mcping.commands.togglePingCMD;
import de.lexarox.mcping.listeners.chatListener;
import de.lexarox.mcping.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPing extends JavaPlugin {

    private static Config cfg;


    @Override
    public void onEnable() {
        //Plugin startup logic
        getServer().getPluginManager().registerEvents(new chatListener(), this);
        getCommand("toggleping").setExecutor(new togglePingCMD());
        getCommand("pingreload").setExecutor(new pingReloadCMD());

        cfg = new Config("TogglePing.yml", getDataFolder());



        getConfig().options().copyDefaults(true);
        saveConfig();



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    public static Config getCfg(){
        return cfg;
    }



}
