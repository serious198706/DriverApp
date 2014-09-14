package com.cy.driver.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cy.driver.R;
import com.cy.driver.adapter.ChargeHistoryListAdapter;
import com.cy.driver.adapter.CostHistoryListAdapter;
import com.cy.driver.entities.ChargeHistory;
import com.cy.driver.entities.CostHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 岩 on 2014/9/12.
 */
public class CostHistoryLayout extends LinearLayout {
    private Context context;
    private View rootView;

    private ListView listView;
    private CostHistoryListAdapter adapter;
    private List<CostHistory> items;

    public CostHistoryLayout(Context context) {
        super(context);

        this.context = context;

        init();
    }

    private void init() {
        rootView = LayoutInflater.from(context).inflate(R.layout.cost_history_layout, this);

        listView = (ListView)findViewById(R.id.costHistoryList);

        items = getDummyRecords();
        adapter = new CostHistoryListAdapter(context, items);

        listView.setAdapter(adapter);
    }

    private List<CostHistory> getDummyRecords() {
        List<CostHistory> items = new ArrayList<CostHistory>();

        items.add(new CostHistory("50", "2014年10月1日"));
        items.add(new CostHistory("120", "2014年9月1日"));
        items.add(new CostHistory("50", "2014年8月1日"));
        items.add(new CostHistory("110", "2014年7月1日"));
        items.add(new CostHistory("500", "2014年6月1日"));
        items.add(new CostHistory("501", "2014年5月1日"));

        return items;
    }
}
