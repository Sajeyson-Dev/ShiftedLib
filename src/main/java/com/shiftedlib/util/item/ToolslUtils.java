package com.shiftedlib.util.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ToolslUtils {
    /**
     * @param durability Durability of the tool tier
     * @param miningSpeed Mining speed of the tool tier
     * @param harvestLevel Harvest level of the tool tier
     * @param ench Enchantment value
     * @param material Repair material
     * @return Created {@code Tier} with given values
     */
    public static Tier newToolTier(int durability, float miningSpeed, int harvestLevel, int ench, Item material) {
        return new Tier() {

            @Override
            public int getUses() {
                return durability;
            }

            @Override
            public float getSpeed() {
                return miningSpeed;
            }

            @Override
            public float getAttackDamageBonus() {
                return 0;
            }

            @Override
            public int getLevel() {
                return harvestLevel;
            }

            @Override
            public int getEnchantmentValue() {
                return ench;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(material);
            }  
        };
    }

    public static void damageOnUse(LivingEntity player, EquipmentSlot slot, ItemStack stack, int damage) {
        if (!((Player)player).isCreative() && player.isAlive()){
            if (stack.getDamageValue() > stack.getMaxDamage() - 2 | damage >= stack.getMaxDamage() - stack.getDamageValue()) { player.broadcastBreakEvent(slot); stack.shrink(1); } 
            else stack.setDamageValue(stack.getDamageValue() + damage);
        }
    }
}
