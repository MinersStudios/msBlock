package github.minersStudios.msBlock.listeners.player;

import github.minersStudios.msBlock.utils.BlockUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.annotation.Nonnull;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(@Nonnull PlayerQuitEvent event) {
        BlockUtils.cancelAllTasksWithThisPlayer(event.getPlayer());
    }
}
