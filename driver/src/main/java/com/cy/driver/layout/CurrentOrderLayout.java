package com.cy.driver.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.cy.driver.R;

/**
 * TODO: document your custom view class.
 */
public class CurrentOrderLayout extends LinearLayout {
    private Context context;

    public CurrentOrderLayout(Context context) {
        super(context);

        this.context = context;
        init();
    }

    private void init() {

    }
}
