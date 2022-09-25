package com.shiftedlib.util.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorUtils {

    private String mod;
    private String name;

    public ArmorUtils(String mod) {
        this.mod = mod;
    }

    /**
     * @param name Armor base name
     * @param slotDurability Array of durability values [boots, legs, chest, helmet]
     * @param slotDefense Array of defence values [boots, legs, chest, helmet]
     * @param toughness Armor toughness
     * @param resistance Knockback resistance
     * @param ench Enchantment value
     * @param sound Equip sound
     * @param material Repair material
     * @return Created {@code ArmorMaterial} with given values
     */
    public ArmorMaterial newArmorTier(String name, int[] slotDurability, int[] slotDefense, float toughness, float resistance, int ench, SoundEvent sound, Item material) {
        this.name = name;
        return new ArmorMaterial() {

            @Override
            public int getDurabilityForSlot(EquipmentSlot slot) {
                return slotDurability[slot.getIndex()];
            }

            @Override
            public int getDefenseForSlot(EquipmentSlot slot) {
                return slotDefense[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return ench;
            }

            @Override
            public SoundEvent getEquipSound() {
                return sound;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(material);
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public float getToughness() {
                return toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return resistance;
            }
        };
    }

    public String getArmorTexture(EquipmentSlot slot) {
        var texture = mod + ":textures/models/armor/";
        if (slot == EquipmentSlot.HEAD)     return texture + name + "_layer_1.png";
        if (slot == EquipmentSlot.CHEST)    return texture + name + "_layer_1.png";
        if (slot == EquipmentSlot.LEGS)     return texture + name + "_layer_2.png";
        if (slot == EquipmentSlot.FEET)     return texture + name + "_layer_1.png";
        return null;
    }

    public boolean isFullSet(Player player, Item helmet, Item chest, Item legs, Item boots) {
        if (player.isAlive()) {
            if (getArmorPiece(player, 3) == helmet && 
                getArmorPiece(player, 2) == chest && 
                getArmorPiece(player, 1) == legs && 
                getArmorPiece(player, 0) == boots) {
                return true;
            }
        }
        return false;
    }

    public Item getArmorPiece(Player player, int slot) {
        return player.getInventory().armor.get(slot).getItem();
    }
}
