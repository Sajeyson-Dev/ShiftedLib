package com.shiftedlib.util.player;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PlayerUtils {
    public static Block getBlockStandingOn(Player player) {
        var block = player.getLevel().getBlockState(player.getOnPos()).getBlock();
        if (block != null) return block;
        else return Blocks.AIR;
    }

    public static Block getBlockStandingIn(Player player) {
        var block = player.getLevel().getBlockState(player.getOnPos().above()).getBlock();
        if (block != null) return block;
        else return Blocks.AIR;
    }

    public static boolean isStandingOn(Player player, Block block) {
        if (player.getLevel().getBlockState(player.getOnPos()).getBlock() == block) return true;
        else return false;
    }

    public static boolean isStandingIn(Player player, Block block) {
        if (player.getLevel().getBlockState(player.getOnPos().above()).getBlock() == block) return true;
        else return false;
    }
}
