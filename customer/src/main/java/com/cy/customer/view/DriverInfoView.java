package com.cy.customer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cy.customer.R;
import com.cy.customer.entity.DriverInfo;

/**
 * Created by 岩 on 2014/8/13.
 */
public class DriverInfoView extends LinearLayout{

    private Context context;
    private DriverInfo driverInfo;

    public DriverInfoView(Context context, DriverInfo driverInfo) {
        super(context);

        this.context = context;
        this.driverInfo = driverInfo;

        init();
    }

    public DriverInfo getDriver() { return this.driverInfo; }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.driver_popup_layout, this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);

        ((ImageView) findViewById(R.id.avatar)).setImageBitmap(driverInfo.avatar == null ? bitmap : driverInfo.avatar);
        ((TextView)findViewById(R.id.name)).setText(driverInfo.name);
        ((TextView)findViewById(R.id.phoneNumber)).setText(driverInfo.phoneNumber);
        ((RatingBar)findViewById(R.id.rating)).setRating(driverInfo.rating);
    }
}
