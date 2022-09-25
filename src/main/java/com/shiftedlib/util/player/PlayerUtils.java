package com.shiftedlib.util.player;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PlayerUtils {

    public static boolean noCooldown(Player player, ItemStack item) {
        if (!player.getCooldowns().isOnCooldown(item.getItem())) return true;
        else return false;
    }

    public static boolean mainHandHas(Player player, ItemStack handItem) {
        if (player.getMainHandItem().getItem() == handItem.getItem()) return true;
        else return false;
    }

    public static boolean offHandHas(Player player, ItemStack handItem) {
        if (player.getOffhandItem().getItem() == handItem.getItem()) return true;
        else return false;
    }

    public static boolean isHolding(Player player, ItemStack handItem) {
        var mainHand = player.getMainHandItem().getItem();
        var offHand = player.getOffhandItem().getItem();
        if (mainHand == handItem.getItem() || offHand == handItem.getItem()) return true;
        else return false;
    }

    public static boolean isHolding(Player player, Item item1, Item item2) {
        var mainHand = player.getMainHandItem().getItem();
        var offHand = player.getOffhandItem().getItem();
        if (mainHand == item1 && offHand == item2 || mainHand == item1 && offHand == item2) return true;
        else return false;
    }

    public static boolean isHolding(Player player, ItemStack item1, ItemStack item2) {
        var mainHand = player.getMainHandItem().getItem();
        var offHand = player.getOffhandItem().getItem();
        if (mainHand == item1.getItem() && offHand == item2.getItem() || mainHand == item2.getItem() && offHand == item1.getItem()) return true;
        else return false;
    }

    public static boolean hasXpLevels(Player player, int xpLevels) {
        if (player.experienceLevel >= xpLevels) return true;
        else return false;
    }

    public static boolean lastDamageSourceIs(Player player, String damage) {
        if (player.getLastDamageSource() != null && player.hurtTime > 8 && player.getLastDamageSource().getMsgId() == damage) return true;
        else return false;
    }

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
