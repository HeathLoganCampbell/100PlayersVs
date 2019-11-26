package games.bevs.hundredvs.listeners;

import games.bevs.hundredvs.commons.CC;
import games.bevs.hundredvs.managers.PlayerDataManager;
import games.bevs.hundredvs.types.PlayerData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class BlockBreakListener implements Listener
{
    public static final Material TARGET_MATERIAL = Material.DIAMOND_ORE;

    @NonNull
    private PlayerDataManager playerDataManager;

    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if(block == null) return;
        if(block.getType() != TARGET_MATERIAL) return;

        PlayerData playerData = this.playerDataManager.getPlayer(player);
        playerData.increaseDiamondsMinded();
        player.sendMessage( CC.bAqua + "Bevs ) " + CC.gray + "You have mined " + playerData.getDiamondsMined() + " diamonds!");
        //mined a diamond
    }
}
