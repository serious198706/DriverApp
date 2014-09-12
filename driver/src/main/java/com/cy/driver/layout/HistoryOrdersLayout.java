package com.cy.driver.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cy.driver.R;

/**
 * TODO: document your custom view class.
 */
public class HistoryOrdersLayout extends LinearLayout {
    private Context context;
    private ListView listView;

    public HistoryOrdersLayout(Context context) {
        super(context);

        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.history_orders_layout, null);

        listView = (ListView)findViewById(R.id.historyOrdersList);


    }
}
