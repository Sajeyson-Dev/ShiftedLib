package com.shiftedlib.util.formatting;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class FormattingUtils {
    public static Style newColor(String color) {
        return Style.EMPTY.withColor(TextColor.parseColor(color));
    }
}
