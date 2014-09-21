package com.cy.driver.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
        LayoutInflater.from(context).inflate(R.layout.current_order_layout, this);

        findViewById(R.id.cancelOrderButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle(R.string.alert)
                        .setMessage(R.string.cancel_order_confirm)
                        .setPositiveButton(R.string.cancel_order, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // TODO 向服务器发送销单指令

                                showContent(false);
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create();

                dialog.show();
            }
        });
    }

    private void showContent(boolean show) {
        findViewById(R.id.noOrderNow).setVisibility(show ? GONE : VISIBLE);
        findViewById(R.id.orderContent).setVisibility(show ? VISIBLE : GONE);
    }
}
