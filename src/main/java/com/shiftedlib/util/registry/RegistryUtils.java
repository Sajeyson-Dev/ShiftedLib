package com.shiftedlib.util.registry;

import com.google.common.base.Supplier;
import com.shiftedlib.ModGetter;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryUtils {
    private static final String MOD = ModGetter.getModId();

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD);
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD);

    public static void registerAll(IEventBus bus) {
        RegistryUtils.ITEMS.register(bus);
        RegistryUtils.BLOCKS.register(bus);
    }

    public static void registerItems(IEventBus bus) {
        RegistryUtils.ITEMS.register(bus);
    }

    public static void registerBlocks(IEventBus bus) {
        RegistryUtils.BLOCKS.register(bus);
    }

    public static RegistryObject<Item> newItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static RegistryObject<Block> newBlock(String name, Supplier<Block> supplier) {
        return BLOCKS.register(name, supplier);
    }

    public static RegistryObject<Block> newBlockItem(String name, Supplier<Block> supplier, Properties properties) {
        var block = BLOCKS.register(name, supplier);
        newItem(name, ()-> new BlockItem(block.get(), properties));
        return block;
    }
}
