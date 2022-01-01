package github.minersStudios.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import javax.annotation.Nonnull;

public class PlaySwingAnimation {
    public PlaySwingAnimation(@Nonnull Player player, @Nonnull EquipmentSlot equipmentSlot) {
        switch (equipmentSlot) {
            case HAND:
                player.swingMainHand();
                break;
            case OFF_HAND:
                player.swingOffHand();
                break;
        }
    }
}
