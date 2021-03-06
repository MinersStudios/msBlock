package github.minersStudios.msBlock.listeners.block;

import github.minersStudios.msBlock.enums.CustomBlockMaterial;
import github.minersStudios.msBlock.utils.BlockUtils;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class BlockDamageListener implements Listener {

    @EventHandler
    public void onBlockDamage(@Nonnull BlockDamageEvent event) {
        Block block = event.getBlock();
        if (BlockUtils.isWoodenSound(block.getType()))
            block.getWorld().playSound(block.getLocation().clone().add(0.5d, 0.5d, 0.5d), "custom.block.wood.hit", 0.5f, 0.5f);
        if (block.getBlockData() instanceof NoteBlock noteBlock) {
            CustomBlockMaterial.getCustomBlockMaterial(noteBlock.getNote(), noteBlock.getInstrument(), noteBlock.isPowered()).playHitSound(block);
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, -1, true, false, false));
        }
    }
}
