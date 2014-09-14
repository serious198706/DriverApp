package com.cy.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cy.driver.R;
import com.cy.driver.entities.CostHistory;

import java.util.List;

/**
 * Created by serious on 2014/9/14.
 */
public class CostHistoryListAdapter extends BaseAdapter {
    class ViewHolder {
        public TextView amount;
        public TextView dateTime;
    }

    private List<CostHistory> items;
    private Context context;

    public CostHistoryListAdapter(Context context, List<CostHistory> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CostHistory getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();

        if(view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.cost_history_list_item, null);
            viewHolder.amount = (TextView) view.findViewById(R.id.amount);
            viewHolder.dateTime = (TextView) view.findViewById(R.id.dateTime);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        CostHistory chargeHistory = items.get(i);

        if(chargeHistory != null) {
            viewHolder.amount.setText(chargeHistory.amount);
            viewHolder.dateTime.setText(chargeHistory.dateTime);
        }

        return view;
    }
}
