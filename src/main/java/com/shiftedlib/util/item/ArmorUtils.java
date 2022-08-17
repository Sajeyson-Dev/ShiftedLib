package com.shiftedlib.util.item;

import com.shiftedlib.ModGetter;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorUtils {
    private static String armorSet;
    private static final String TEXTURE_PATH = ModGetter.getModId() + ":textures/models/armor/";

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
    public static ArmorMaterial newArmorTier(String name, int[] slotDurability, int[] slotDefense, float toughness, float resistance, int ench, SoundEvent sound, Item material) {
        armorSet = name;
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

    public static String getArmorTexture(EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD)     return TEXTURE_PATH + armorSet + "_layer_1.png";
        if (slot == EquipmentSlot.CHEST)    return TEXTURE_PATH + armorSet + "_layer_1.png";
        if (slot == EquipmentSlot.LEGS)     return TEXTURE_PATH + armorSet + "_layer_2.png";
        if (slot == EquipmentSlot.FEET)     return TEXTURE_PATH + armorSet + "_layer_1.png";
        return null;
    }

    public static boolean isFullSet(Player player, Item helmet, Item chest, Item legs, Item boots) {
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

    public static Item getArmorPiece(Player player, int slot) {
        return player.getInventory().armor.get(slot).getItem();
    }
}
