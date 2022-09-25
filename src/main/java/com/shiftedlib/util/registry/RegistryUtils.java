package com.shiftedlib.util.registry;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryUtils {
    public DeferredRegister<Item> ITEMS;
    public DeferredRegister<Block> BLOCKS;
    public IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    
    public RegistryUtils(String mod) {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, mod);
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, mod);
    }

    public void register() {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }

    public void registerItems() {
        ITEMS.register(eventBus);
    }

    public void registerBlocks() {
        BLOCKS.register(eventBus);
    }

    /*
     * Items
     */

    public RegistryObject<Item> newItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    public RegistryObject<Item> newItem(String name, Properties properties) {
        return ITEMS.register(name, ()-> new Item(properties));
    }

    /*
     * Blocks
     */

    public RegistryObject<Block> newBlock(String name, Supplier<Block> supplier) {
        return BLOCKS.register(name, supplier);
    }

    public RegistryObject<Block> newBlock(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.register(name, ()-> new Block(properties));
    }

    /*
     * BlockItems
     */

    public RegistryObject<Block> newBlockItem(String name, Supplier<Block> supplier, Properties properties) {
        var block = BLOCKS.register(name, supplier);
        ITEMS.register(name, ()-> new BlockItem(block.get(), properties));
        return block;
    }

    public RegistryObject<Block> newBlockItem(String name, BlockBehaviour.Properties blockProperties, Properties itemProperties) {
        var block = BLOCKS.register(name, ()-> new Block(blockProperties));
        ITEMS.register(name, ()-> new BlockItem(block.get(), itemProperties));
        return block;
    }

    /*
     * Colored BlockItems
     */

    public List<RegistryObject<Block>> newColoredBlockItem(String baseName, String[] colors, Supplier<Block> blockSupplier, Supplier<Item> itemSupplier) {
        List<RegistryObject<Block>> blocks = new ArrayList<RegistryObject<Block>>();
        for (String color : colors) {
            var block = BLOCKS.register(color + "_" + baseName, blockSupplier);
            blocks.add(block);
            ITEMS.register(color + "_" + baseName, itemSupplier);
        }
        return blocks;
    }

    public List<RegistryObject<Block>> newColoredBlockItem(String baseName, String[] colors, Supplier<Block> blockSupplier, Properties itemProperties) {
        List<RegistryObject<Block>> blocks = new ArrayList<RegistryObject<Block>>();
        for (String color : colors) {
            var block = BLOCKS.register(color + "_" + baseName, blockSupplier);
            blocks.add(block);
            ITEMS.register(color + "_" + baseName, ()-> new BlockItem(block.get(), itemProperties));
        }
        return blocks;
    }

    public List<RegistryObject<Block>> newColoredBlockItem(String baseName, String[] colors, BlockBehaviour.Properties blockProperties, Properties itemProperties) {
        List<RegistryObject<Block>> blocks = new ArrayList<RegistryObject<Block>>();
        for (String color : colors) {
            var block = BLOCKS.register(color + "_" + baseName, ()-> new Block(blockProperties));
            blocks.add(block);
            ITEMS.register(color + "_" + baseName, ()-> new BlockItem(block.get(), itemProperties));
        }
        return blocks;
    }
}
