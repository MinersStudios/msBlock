package github.minersStudios.objects;

import github.minersStudios.enumerators.CustomBlockMaterial;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;

import static github.minersStudios.Main.coreProtectAPI;
import static github.minersStudios.Main.plugin;

public class CustomBlock {
    private Block block;
    private CustomBlockMaterial customBlockMaterial;
    private Player player;

    public CustomBlock(){
    }

    public CustomBlock(Block block, Player player) {
        this.player = player;
        this.block = block;

        if(block.getType() != Material.NOTE_BLOCK) return;
        NoteBlock noteBlock = (NoteBlock) block.getBlockData();
        setCustomBlockMaterial(CustomBlockMaterial.getCustomBlockMaterial(noteBlock.getNote(), noteBlock.getInstrument(), noteBlock.isPowered()));
    }

    /**
     * @return CustomBlockMaterial param of a custom block
     */
    public CustomBlockMaterial getCustomBlockMaterial(){
        return customBlockMaterial;
    }

    /**
     * Sets CustomBlockMaterial param of a custom block
     */
    public void setCustomBlockMaterial(@Nullable CustomBlockMaterial customBlockMaterial){
        this.customBlockMaterial = customBlockMaterial;
    }

    // CustomBlock
    /**
     * @return CustomBlock
     */
    public CustomBlock getCustomBlock(Block block, Player player){
        if(block.getType() == Material.NOTE_BLOCK)
            return new CustomBlock(block, player);
        return null;
    }

    /**
     * Sets custom block not with CustomBlockMaterial
     */
    public void setCustomBlock(CustomBlockMaterial customBlockMaterial) {
        new BukkitRunnable(){
            @Override
            public void run() {
                if(customBlockMaterial == null) return;
                block.setType(Material.NOTE_BLOCK);

                NoteBlock noteBlock = (NoteBlock) block.getBlockData();
                noteBlock.setInstrument(customBlockMaterial.getInstrument());
                noteBlock.setNote(customBlockMaterial.getNote());
                noteBlock.setPowered(customBlockMaterial.isPowered());
                block.setBlockData(noteBlock);

                setCustomBlockMaterial(CustomBlockMaterial.getCustomBlockMaterial(noteBlock.getNote(), noteBlock.getInstrument(), noteBlock.isPowered()));
                block.getWorld().playSound(block.getLocation(), customBlockMaterial.getSoundPlace(), 1.0f, 1.0f);
                coreProtectAPI.logPlacement(player.getName(), block.getLocation(), Material.NOTE_BLOCK, block.getBlockData());

                if (player.getGameMode() == GameMode.SURVIVAL)
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }
        }.runTask(plugin);
    }
}
