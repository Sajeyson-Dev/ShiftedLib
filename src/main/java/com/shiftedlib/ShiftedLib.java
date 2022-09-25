package com.shiftedlib;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.fml.common.Mod;

@Mod(ShiftedLib.MOD_ID)
public class ShiftedLib {
    public static final String MOD_ID = "shiftedlib";
    public final static Logger LOGGER = LogUtils.getLogger();

    public ShiftedLib() {
        LOGGER.info("Loading Shifted Lib...");
    }
}
