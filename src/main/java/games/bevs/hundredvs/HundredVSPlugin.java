package games.bevs.hundredvs;

import games.bevs.hundredvs.commands.HundredVsCommand;
import games.bevs.hundredvs.commons.PluginUtils;
import games.bevs.hundredvs.commons.ticker.Ticker;
import games.bevs.hundredvs.listeners.BlockBreakListener;
import games.bevs.hundredvs.listeners.PlayerListener;
import games.bevs.hundredvs.managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;


public class HundredVSPlugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        new Ticker(this);

        PlayerDataManager playerDataManager = new PlayerDataManager(this);

        PluginUtils.registerListener(new BlockBreakListener(playerDataManager), this);
        PluginUtils.registerListener(new PlayerListener(playerDataManager, this), this);

        this.registerCommands(playerDataManager);
    }


    private CommandMap getCommandMap()
    {
        try{
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        }
        catch(Exception exception){
            exception.printStackTrace();
        }

        return null;
    }

    private void registerCommands(PlayerDataManager playerDataManager)
    {
        CommandMap commandMap = this.getCommandMap();
        if(commandMap == null)
        {
            for(int i = 0; i < 20; i++)
                Bukkit.broadcastMessage("");

            for(int i = 0; i < 20; i++)
            {
                Bukkit.broadcastMessage(" HundredVS) FUCK, I can't get access to the commandMap");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
            }
            return;
        }

        HundredVsCommand hundredVscommand = new HundredVsCommand(playerDataManager);
        commandMap.register(hundredVscommand.getName(), hundredVscommand);
    }
}
