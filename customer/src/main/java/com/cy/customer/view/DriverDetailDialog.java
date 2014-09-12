package com.cy.customer.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cy.customer.R;
import com.cy.customer.entity.DriverInfo;

/**
 * Created by 岩 on 2014/8/27.
 */
public class DriverDetailDialog extends Dialog {
    private Context context;
    private DriverInfo driverInfo;

    private ImageView avatar;
    private TextView name;
    private TextView phone;
    private RatingBar rating;

    public DriverDetailDialog(Context context, DriverInfo driverInfo) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.driver_detail_dialog);

        this.context = context;
        this.driverInfo = driverInfo;

        init();
    }

    private void init() {
        avatar = (ImageView)findViewById(R.id.avatar);

        if(driverInfo.avatar == null)
            avatar.setImageResource(R.drawable.avatar);
        else
            avatar.setImageBitmap(driverInfo.avatar);

        name = (TextView)findViewById(R.id.name);
        name.setText(driverInfo.name);

        phone = (TextView)findViewById(R.id.phone);
        phone.setText(driverInfo.phoneNumber);

        rating = (RatingBar)findViewById(R.id.rating);
        rating.setMax(5);
        rating.setRating(driverInfo.rating);
    }
}
