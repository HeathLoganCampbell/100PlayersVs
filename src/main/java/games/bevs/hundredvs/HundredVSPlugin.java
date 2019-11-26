package games.bevs.hundredvs;

import games.bevs.hundredvs.commons.PluginUtils;
import games.bevs.hundredvs.commons.ticker.Ticker;
import games.bevs.hundredvs.listeners.BlockBreakListener;
import games.bevs.hundredvs.listeners.PlayerListener;
import games.bevs.hundredvs.managers.PlayerDataManager;
import org.bukkit.plugin.java.JavaPlugin;


public class HundredVSPlugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        new Ticker(this);

        PlayerDataManager playerDataManager = new PlayerDataManager(this);

        PluginUtils.registerListener(new BlockBreakListener(playerDataManager), this);
        PluginUtils.registerListener(new PlayerListener(playerDataManager, this), this);
    }
}
