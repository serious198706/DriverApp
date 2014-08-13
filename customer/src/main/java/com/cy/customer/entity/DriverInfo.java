package com.cy.customer.entity;

import android.graphics.Bitmap;

/**
 * Created by 岩 on 2014/8/13.
 */
public class DriverInfo {
    public Bitmap avatar;
    public String name;
    public String phoneNumber;
    public Float rating;

    public DriverInfo(Bitmap avatar, String name, String phoneNumber, Float rating) {
        this.avatar = avatar;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }
}
