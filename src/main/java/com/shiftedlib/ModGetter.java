package com.shiftedlib;

public class ModGetter {
    public static String mod_id;

    public ModGetter(String mod_id) {
        ModGetter.mod_id = mod_id;
    }

    public static String getModId() {
        return mod_id;
    }
}
