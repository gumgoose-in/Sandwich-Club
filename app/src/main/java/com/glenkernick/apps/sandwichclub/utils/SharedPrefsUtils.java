package com.glenkernick.apps.sandwichclub.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {

    public static final int LIST_LAYOUT_ID = 1;
    public static final int GRID_LAYOUT_ID = 2;
    private static final String CURRENT_LAYOUT_ID = "CURRENT_LAYOUT_ID";

    public static void saveItemType(Context context, int selectedLayout) {
        SharedPreferences preferences = context.getSharedPreferences(CURRENT_LAYOUT_ID, selectedLayout);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CURRENT_LAYOUT_ID, selectedLayout);
        editor.apply();
    }

    public static int getItemType(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(CURRENT_LAYOUT_ID, 0);
        return preferences.getInt(CURRENT_LAYOUT_ID, LIST_LAYOUT_ID);
    }
}