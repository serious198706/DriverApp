package com.cy.library.adapter;

/**
 * Created by 岩 on 2014/9/12.
 */


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 岩 on 13-12-20.
 *
 * 滑动页面
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> views;

    public MyViewPagerAdapter(List<View> views){
        this.views = views;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position),0);
        return views.get(position);
    }


    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public static class MyOnClick implements View.OnClickListener
    {
        int index = 0;
        ViewPager viewPager;

        public MyOnClick(ViewPager viewPager, int i){
            this.index = i ;
            this.viewPager = viewPager;
        }

        @Override
        public void onClick(View v) {
            this.viewPager.setCurrentItem(index);
        }
    }

}

