package games.bevs.hundredvs.managers;

import games.bevs.hundredvs.HundredVSPlugin;
import games.bevs.hundredvs.commons.BevsScoreboard;
import games.bevs.hundredvs.commons.CC;
import games.bevs.hundredvs.commons.PlayerManager;
import games.bevs.hundredvs.types.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    public void reset()
    {
        this.getPlayerDatas().clear();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            this.registerPlayer(onlinePlayer.getUniqueId(), onlinePlayer.getName(), onlinePlayer.getAddress().getAddress());
    }

    @Override
    public PlayerData onPlayerCreation(UUID uniquieId, String username, InetAddress ipAddress)
    {
        PlayerData playerData = new PlayerData(uniquieId, username);

        Player player = Bukkit.getPlayer(uniquieId);
        BevsScoreboard scoreboard = new BevsScoreboard(JavaPlugin.getPlugin(HundredVSPlugin.class), player);
        scoreboard.open(player);
        scoreboard.setTitle(CC.bAqua + "TOP MINERS");
        scoreboard.setLine(1, " Sprock was");
        scoreboard.setLine(2, " here");
        playerData.setBevsScoreboard(scoreboard);

        return playerData;
    }

    @Override
    public boolean onPlayerDestruction(PlayerData playerObj)
    {
        return true;
    }
}

