package games.bevs.hundredvs.types;

import games.bevs.hundredvs.commons.BevsScoreboard;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class PlayerData
{
    @Getter
    private @NonNull UUID uniqueId;
    @Getter
    private @NonNull String username;

    @Getter
    @Setter
    private BevsScoreboard bevsScoreboard;
    @Getter
    @Setter
    private int diamondsMined = 0;

    public Player getPlayer()
    {
        return Bukkit.getPlayer(uniqueId);
    }

    public boolean isOnline()
    {
        return this.getPlayer() != null;
    }

    public void increaseDiamondsMinded()
    {
        this.diamondsMined += 1;
    }
}
