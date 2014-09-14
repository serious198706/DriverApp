package com.cy.driver.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cy.driver.R;
import com.cy.driver.adapter.ChargeHistoryListAdapter;
import com.cy.driver.entities.ChargeHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 岩 on 2014/9/12.
 */
public class ChargeHistoryLayout extends LinearLayout {
    private Context context;
    private View rootView;

    private ListView listView;
    private ChargeHistoryListAdapter adapter;
    private List<ChargeHistory> items;

    public ChargeHistoryLayout(Context context) {
        super(context);

        this.context = context;

        init();
    }

    private void init() {
        rootView = LayoutInflater.from(context).inflate(R.layout.charge_history_layout, this);

        listView = (ListView)findViewById(R.id.chargeHistoryList);

        items = getDummyRecords();
        adapter = new ChargeHistoryListAdapter(context, items);

        listView.setAdapter(adapter);
    }

    private List<ChargeHistory> getDummyRecords() {
        List<ChargeHistory> items = new ArrayList<ChargeHistory>();

        items.add(new ChargeHistory("50", "2014年10月1日"));
        items.add(new ChargeHistory("120", "2014年9月1日"));
        items.add(new ChargeHistory("50", "2014年8月1日"));
        items.add(new ChargeHistory("110", "2014年7月1日"));
        items.add(new ChargeHistory("500", "2014年6月1日"));
        items.add(new ChargeHistory("501", "2014年5月1日"));

        return items;
    }
}
