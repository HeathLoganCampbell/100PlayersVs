package games.bevs.hundredvs.listeners;

import games.bevs.hundredvs.commons.BevsScoreboard;
import games.bevs.hundredvs.commons.CC;
import games.bevs.hundredvs.commons.ticker.TickEvent;
import games.bevs.hundredvs.commons.ticker.TimeType;
import games.bevs.hundredvs.managers.PlayerDataManager;
import games.bevs.hundredvs.types.PlayerData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class PlayerListener implements Listener
{
    @NonNull
    private PlayerDataManager playerDataManager;
    @NonNull
    private JavaPlugin plugin;
    public static List<PlayerData> playerDatas = new ArrayList<>();


    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        this.playerDataManager.registerPlayer(player.getUniqueId(), player.getName(), player.getAddress().getAddress());

        PlayerData playerData = this.playerDataManager.getPlayer(player);

        //remap
        playerDatas = new ArrayList(this.playerDataManager.getPlayerDatas());
    }

    @EventHandler
    public void onTick(TickEvent e)
    {
        if(e.getType() != TimeType.SECOND) return;


        Collections.sort(this.playerDatas, new Comparator<PlayerData>() {
            @Override
            public int compare(PlayerData p1, PlayerData p2) {
                return Integer.compare(p1.getDiamondsMined(), p2.getDiamondsMined());
            }
        });

        for (Player player : Bukkit.getOnlinePlayers())
        {
            PlayerData playerData = this.playerDataManager.getPlayer(player);
            if(playerData == null) continue;
            BevsScoreboard scoreboard = playerData.getBevsScoreboard();

            scoreboard.reset();
            int i = 0;
            for(i = 0; i < 10; i++)
            {
                if(this.playerDatas.size() <= i) break;
                PlayerData entryPlayerdata = this.playerDatas.get(i);
                scoreboard.setLine(i, CC.aqua +
                                            entryPlayerdata.getDiamondsMined() + " "
                                            + ( playerData == entryPlayerdata ? CC.bGray : CC.gray) + entryPlayerdata.getUsername());
            }
            scoreboard.setLine(i + 0, CC.bGray + "==============");
            scoreboard.setLine(i + 1, CC.bGray + playerData.getDiamondsMined() + " You");
        }
    }
}
