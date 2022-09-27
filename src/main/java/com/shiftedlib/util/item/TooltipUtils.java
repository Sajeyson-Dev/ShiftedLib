package com.shiftedlib.util.item;

import java.util.List;

import com.shiftedlib.ShiftedLib;
import com.shiftedlib.util.formatting.UsefulColors;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class TooltipUtils {
    
    private String mod;
    private static String mod_id = ShiftedLib.MOD_ID;

    static final Component EMPTY = Component.literal("");
    static final Component HOLD_SHIFT = Component.translatable("tooltip." + mod_id + ".shift").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.UNDERLINE);

    public TooltipUtils(String mod) {
        this.mod = mod;
    }

    public void buildTooltip(List<Component> list, String tooltip, Style color) {
        list.add(Component.translatable("tooltip." + mod + "." + tooltip).withStyle(color));
    }

    public void buildTooltip(List<Component> list, String tooltip, ChatFormatting color) {
        list.add(Component.translatable("tooltip." + mod + "." + tooltip).withStyle(color));
    }

    public void buildShiftTooltip(List<Component> list, String tooltip, Style color) {
        if (Screen.hasShiftDown()) list.add(Component.translatable("tooltip." + mod + "." + tooltip + ".shift").withStyle(color));
        else list.add(HOLD_SHIFT);
    }

    public void buildShiftTooltip(List<Component> list, String tooltip, int count, Style color) {
        if (Screen.hasShiftDown()) for (int index = 0; index < count; index++) list.add(Component.translatable("tooltip." + mod + "." + tooltip + ".shift_" + index).withStyle(color));
        else list.add(HOLD_SHIFT);
    }

    public void buildToolActionTooltip(List<Component> list, String action, String tooltip, int count, Style color) {
        if (Screen.hasShiftDown()) {
            list.add(Component.translatable("tooltip." + mod_id + "." + action).withStyle(UsefulColors.ACTUAL_GREEN).withStyle(ChatFormatting.UNDERLINE));
            for (int index = 0; index < count; index++) list.add(Component.translatable("tooltip." + mod + "." + action + "." + tooltip + "_" + index).withStyle(color));
        } else list.add(HOLD_SHIFT);
    }

    public void buildSetBonusTooltip(List<Component> list, String tooltip, int count, Style color) {
        if (Screen.hasShiftDown()) {
            list.add(Component.translatable("tooltip." + mod_id + ".fullset").withStyle(UsefulColors.ACTUAL_GREEN).withStyle(ChatFormatting.UNDERLINE));
            for (int index = 0; index < count; index++) list.add(Component.translatable("tooltip." + mod + ".fullset." + tooltip + "_" + index).withStyle(color));
        } else list.add(HOLD_SHIFT);
    }
}
