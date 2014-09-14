package com.cy.driver.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cy.driver.R;
import com.cy.library.adapter.HistoryOrdersListAdapter;
import com.cy.library.entity.HistoryOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class HistoryOrdersLayout extends LinearLayout {
    private Context context;
    private ListView listView;
    private HistoryOrdersListAdapter adapter;
    private List<HistoryOrder> items;

    public HistoryOrdersLayout(Context context) {
        super(context);

        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.history_orders_layout, this);

        listView = (ListView)findViewById(R.id.historyOrdersList);

        items = getDummyRecord();

        adapter = new HistoryOrdersListAdapter(context, items);
        listView.setAdapter(adapter);
    }

    private List<HistoryOrder> getDummyRecord() {
        List<HistoryOrder> items = new ArrayList<HistoryOrder>();

        items.add(new HistoryOrder("13888888888", "2014年9月2日15点34分", "北京市朝阳半壁店", "北京市渠路方家村", "25"));
        items.add(new HistoryOrder("13777777777", "2014年9月2日15点34分", "北京市朝阳半壁店", "北京市渠路方家村", "95"));
        items.add(new HistoryOrder("13666666666", "2014年9月2日15点34分", "北京市朝阳半壁店", "北京市渠路方家村", "40"));
        items.add(new HistoryOrder("13555555555", "2014年9月2日15点34分", "北京市朝阳半壁店", "北京市渠路方家村", "37"));
        items.add(new HistoryOrder("13444444444", "2014年9月2日15点34分", "北京市朝阳半壁店", "北京市渠路方家村", "60"));
        items.add(new HistoryOrder("13333333333", "2014年9月2日15点34分", "北京市朝阳半壁店", "北京市渠路方家村", "88"));

        return items;
    }
}
