package com.cy.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cy.library.R;
import com.cy.library.entity.HistoryOrder;

import java.util.List;

/**
 * Created by 岩 on 2014/9/12.
 */
public class HistoryOrdersListAdapter extends BaseAdapter {
    class ViewHolder {
        TextView name;
        TextView time;
        TextView starting;
        TextView ending;
        TextView amount;
    }

    private Context context;
    private List<HistoryOrder> items;

    public HistoryOrdersListAdapter(Context context, List<HistoryOrder> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public HistoryOrder getItem(int i) {
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
            view = vi.inflate(R.layout.history_order_list_item, null);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.time = (TextView) view.findViewById(R.id.dateTime);
            viewHolder.starting = (TextView) view.findViewById(R.id.starting);
            viewHolder.ending = (TextView) view.findViewById(R.id.ending);
            viewHolder.amount = (TextView) view.findViewById(R.id.amount);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        HistoryOrder historyOrder = items.get(i);

        if(historyOrder != null) {
            viewHolder.name.setText(historyOrder.name);
            viewHolder.time.setText(historyOrder.time);
            viewHolder.starting.setText(historyOrder.starting);
            viewHolder.ending.setText(historyOrder.ending);
            viewHolder.amount.setText(historyOrder.amount);
        }

        return view;
    }
}
