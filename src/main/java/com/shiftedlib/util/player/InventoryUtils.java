package com.shiftedlib.util.player;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InventoryUtils {
    public static boolean canBeInInventory(Player player, ItemStack stack) {
        var inv = player.getInventory();
        if (inv.getFreeSlot() != -1 | inv.getSlotWithRemainingSpace(stack) != -1) return true;
        else return false;
    }
}
