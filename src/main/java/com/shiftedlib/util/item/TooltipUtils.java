package com.shiftedlib.util.item;

import java.util.List;

import com.shiftedlib.ModGetter;
import com.shiftedlib.util.formatting.UsefulColors;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TooltipUtils {

    private static final String MOD = ModGetter.getModId();

    public static void buildTooltip(List<Component> list, String tooltip, Style color) {
        list.add(Component.translatable("tooltip." + MOD + "." + tooltip).withStyle(color));
    }

    public static void buildTooltip(List<Component> list, String tooltip, ChatFormatting color) {
        list.add(Component.translatable("tooltip." + MOD + "." + tooltip).withStyle(color));
    }

    public static void buildShiftTooltip(List<Component> list, String tooltip, Style color) {
        if (Screen.hasShiftDown()) list.add(Component.translatable("tooltip." + MOD + "." + tooltip + ".shift").withStyle(color));
        else list.add(Component.translatable("tooltip." + MOD + ".shift").withStyle(ChatFormatting.DARK_GRAY));
    }

    public static void buildShiftTooltip(List<Component> list, String tooltip, int count, Style color) {
        if (Screen.hasShiftDown()) for (int index = 0; index < count; index++) list.add(Component.translatable("tooltip." + MOD + "." + tooltip + ".shift_" + index).withStyle(color));
        else list.add(Component.translatable("tooltip." + MOD + ".shift").withStyle(ChatFormatting.DARK_GRAY));
    }

    public static void buildToolActionTooltip(List<Component> list, String action, String tooltip, int count, Style color) {
        list.add(Component.translatable("tooltip." + MOD + "." + action).withStyle(UsefulColors.ACTUAL_GREEN).withStyle(ChatFormatting.UNDERLINE));
        for (int index = 0; index < count; index++) list.add(Component.translatable("tooltip." + MOD + "." + action + "." + tooltip + "_" + index).withStyle(color));
        list.add(Component.literal(""));
    }

    public static void buildSetBonusTooltip(List<Component> list, String tooltip, int count, Style color) {
        list.add(Component.translatable("tooltip." + MOD + ".fullset").withStyle(UsefulColors.ACTUAL_GREEN).withStyle(ChatFormatting.UNDERLINE));
        for (int index = 0; index < count; index++) list.add(Component.translatable("tooltip." + MOD + ".fullset." + tooltip + "_" + index).withStyle(color));
        list.add(Component.literal(""));
    }
}
