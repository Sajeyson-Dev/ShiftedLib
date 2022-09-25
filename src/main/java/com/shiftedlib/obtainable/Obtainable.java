package com.shiftedlib.obtainable;

import javax.annotation.Nullable;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;

import static com.shiftedlib.util.player.InventoryUtils.*;
import static com.shiftedlib.util.player.PlayerUtils.*;

public class Obtainable {

    public static void onUse(RightClickItem event, ItemStack[] items, int xpLevels, boolean condition, ItemStack result, @Nullable DamageSource damageSrc, @Nullable Float damage, @Nullable SoundEvent sound, @Nullable Float pitch) {
        var player = event.getEntity();
        var inv = player.getInventory();
        var level = player.getLevel();
        if (!player.level.isClientSide) {
            if (items.length == 1) {
                if (isHolding(player, items[0]) && hasXpLevels(player, xpLevels) && noCooldown(player, items[0]) && condition && canBeInInventory(player, result)) {
                    if (event.getItemStack().getItem() == items[0].getItem()) {
                        if (!player.isCreative()) {
                            event.getItemStack().shrink(1);
                            player.giveExperienceLevels(-xpLevels); 
                        }
                        inv.add(result);
                        player.getCooldowns().addCooldown(items[0].getItem(), 32);
                        if (damageSrc != null) player.hurt(damageSrc, damage);
                        if (sound != null) level.playSound(null, player.getOnPos(), sound, SoundSource.PLAYERS, 10, pitch);
                    }
                }
            }
            if (items.length == 2) {
                if (isHolding(player, items[0], items[1]) && hasXpLevels(player, xpLevels) && noCooldown(player, items[0]) && noCooldown(player, items[1]) && condition && canBeInInventory(player, result)) {
                    if (!player.isCreative()) {
                        player.getMainHandItem().shrink(1);
                        player.getOffhandItem().shrink(1);
                        player.giveExperienceLevels(-xpLevels);
                    }
                    inv.add(result);
                    player.getCooldowns().addCooldown(items[0].getItem(), 32);
                    player.getCooldowns().addCooldown(items[1].getItem(), 32);
                    if (damageSrc != null) player.hurt(damageSrc, damage);
                    if (sound != null) level.playSound(null, player.getOnPos(), sound, SoundSource.PLAYERS, 10, pitch);
                }
            }
        }
    }

    public static void onDamage(PlayerTickEvent event, ItemStack[] ingredients, String damageSrc, ItemStack result, @Nullable SoundEvent sound, @Nullable Float pitch) {
        var player = event.player;
        if (event.phase != Phase.START && !event.player.level.isClientSide) {
            if (ingredients.length == 1) {
                if (lastDamageSourceIs(player, damageSrc) && isHolding(player, ingredients[0]) && canBeInInventory(player, result)) {
                    if (!player.isCreative()) {
                        boolean consumeMain = true, consumeOff = true;
                        if (consumeMain && mainHandHas(player, ingredients[0])) { consumeOff = false; player.getMainHandItem().shrink(1); }
                        if (consumeOff && offHandHas(player, ingredients[0])) { consumeMain = false; player.getOffhandItem().shrink(1); }
                    }
                    player.getInventory().add(result);
                    if (sound != null) player.getLevel().playSound(null, player.getOnPos(), sound, SoundSource.PLAYERS, 10, pitch);
                }
            }
            if (ingredients.length == 2) {
                if (lastDamageSourceIs(player, damageSrc) && isHolding(player, ingredients[0], ingredients[1]) && canBeInInventory(player, result)) {
                    if (!player.isCreative()) {
                        player.getMainHandItem().shrink(1);
                        player.getOffhandItem().shrink(1);
                    }
                    player.getInventory().add(result);
                    if (sound != null) player.getLevel().playSound(null, player.getOnPos(), sound, SoundSource.PLAYERS, 10, pitch);
                }
            }
        }
    }
}
