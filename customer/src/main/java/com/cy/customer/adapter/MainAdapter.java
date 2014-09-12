package com.cy.customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cy.customer.R;
import com.cy.customer.entity.MainItem;

import java.util.List;

/**
 * Created by 岩 on 2014/8/12.
 */
public class MainAdapter extends BaseAdapter {
    class ViewHolder {
        TextView item;
        TextView value;
    }

    private List<MainItem> items;
    private Context context;

    public MainAdapter(Context context, List<MainItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MainItem getItem(int i) {
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
            view = vi.inflate(R.layout.main_item, null);
            viewHolder.item = (TextView) view.findViewById(R.id.item);
            viewHolder.value = (TextView) view.findViewById(R.id.value);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        final MainItem mainItem = items.get(i);

        if(mainItem != null) {
            viewHolder.item.setText(mainItem.item);
            viewHolder.value.setText(mainItem.value);
        }

        return view;
    }
}
