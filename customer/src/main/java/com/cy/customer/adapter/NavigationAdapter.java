package com.cy.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cy.customer.entity.NavigationItem;
import com.cy.customer.R;

import java.util.List;

/**
 * Created by 岩 on 2014/8/11.
 */
public class NavigationAdapter extends BaseAdapter {
    private class ViewHolder {
        public ImageView icon;
        public TextView text;
        public LinearLayout root;
    }

    List<NavigationItem> items;
    Context context;
    private int selectedPosition;

    public NavigationAdapter(Context context, List<NavigationItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
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
            viewHolder.root = (LinearLayout) view.findViewById(R.id.root);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        final NavigationItem navigationItem = items.get(i);

        if(navigationItem != null) {
            viewHolder.icon.setBackgroundResource(navigationItem.iconId);
            viewHolder.text.setText(navigationItem.text);

            // 设置选中效果
            if(selectedPosition == i)
            {
                viewHolder.text.setTextColor(Color.BLACK);
                viewHolder.root.setBackgroundResource(R.drawable.navi_selected);
            } else {
                viewHolder.text.setTextColor(Color.WHITE);
                viewHolder.root.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        return view;
    }
}
