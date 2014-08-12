package com.cy.customer.entity;

/**
 * Created by 岩 on 2014/8/12.
 */
public class MainItem {
    public String item;
    public String value;
    public Class naviTo;

    public MainItem(String item, String value, Class naviTo) {
        this.item = item;
        this.value = value;
        this.naviTo = naviTo;
    }
}
