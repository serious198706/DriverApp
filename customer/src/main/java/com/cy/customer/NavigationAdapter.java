package com.cy.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 岩 on 2014/8/11.
 */
public class NavigationAdapter extends BaseAdapter {
    private class ViewHolder {
        public ImageView icon;
        public TextView text;
    }

    List<NavigationItem> items;
    Context context;

    public NavigationAdapter(Context context, List<NavigationItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public NavigationItem getItem(int i) {
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
            view = vi.inflate(R.layout.navigation_item, null);
            viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        final NavigationItem navigationItem = items.get(i);

        if(navigationItem != null) {
            viewHolder.icon.setBackgroundResource(navigationItem.iconId);
            viewHolder.text.setText(navigationItem.text);
        }

        return view;
    }
}
