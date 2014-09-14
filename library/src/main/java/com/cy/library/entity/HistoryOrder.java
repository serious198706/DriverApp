package com.cy.library.entity;

/**
 * Created by 岩 on 2014/9/12.
 */
public class HistoryOrder {
    public HistoryOrder(String name, String time, String starting, String ending, String amount) {
        this.name = name;
        this.time = time;
        this.starting = starting;
        this.ending = ending;
        this.amount = amount;
    }

    public String name;
    public String time;
    public String starting;
    public String ending;
    public String amount;
}
