package games.bevs.hundredvs.managers;

import games.bevs.hundredvs.commons.PlayerManager;
import games.bevs.hundredvs.types.PlayerData;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.util.Collection;
import java.util.UUID;

public class PlayerDataManager extends PlayerManager<PlayerData>
{
    public PlayerDataManager(JavaPlugin plugin)
    {
        super(plugin, false);//We don't have a database, so we'll just keep stats in memory
    }

    public Collection<PlayerData> getPlayerDatas()
    {
        return this.getAll();
    }

    @Override
    public PlayerData onPlayerCreation(UUID uniquieId, String username, InetAddress ipAddress)
    {
        PlayerData playerData = new PlayerData(uniquieId, username);
        return playerData;
    }

    @Override
    public boolean onPlayerDestruction(PlayerData playerObj)
    {
        return true;
    }
}

