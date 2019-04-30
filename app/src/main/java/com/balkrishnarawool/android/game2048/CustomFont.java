package com.balkrishnarawool.android.game2048;

import android.content.Context;
import android.graphics.Typeface;

public class CustomFont {

    private static Typeface typeface;

    public static Typeface getTypeFace(Context context) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getResources().getAssets(), "ClearSans-Bold.ttf");
        }
        return typeface;
    }
}
