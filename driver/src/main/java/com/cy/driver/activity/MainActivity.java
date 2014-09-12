package com.cy.driver.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.cy.driver.R;
import com.cy.driver.fragment.AccountInfoFragment;
import com.cy.driver.fragment.MainFragment;
import com.cy.driver.fragment.MoreFragment;
import com.cy.driver.fragment.NavigationDrawerFragment;
import com.cy.driver.fragment.OrdersFragment;
import com.cy.driver.fragment.StatisticsFragment;
import com.cy.driver.fragment.WaitOrderFragment;
import com.cy.driver.utils.Constants;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        MainFragment.OnWaitOrder,
        WaitOrderFragment.OnFragmentInteractionListener,
        OrdersFragment.OnFragmentInteractionListener,
        AccountInfoFragment.OnFragmentInteractionListener,
        StatisticsFragment.OnFragmentInteractionListener,
        MoreFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private MainFragment mainFragment;
    private WaitOrderFragment waitOrderFragment;
    private OrdersFragment ordersFragment;
    private AccountInfoFragment accountInfoFragment;
    private StatisticsFragment statisticsFragment;
    private MoreFragment moreFragment;

    private SDKReceiver mReceiver;

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            Log.d(Constants.TAG, "action: " + s);

            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                Log.d(Constants.TAG, "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s
                    .equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                Toast.makeText(MainActivity.this, "网络出错", Toast.LENGTH_LONG).show();
                Log.d(Constants.TAG, "Map SDK network error!!");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = "";
//
//        try {
//            str = getDummyJsonString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        mainFragment = MainFragment.newInstance(str, "");
        waitOrderFragment = WaitOrderFragment.newInstance("", "");
        ordersFragment = OrdersFragment.newInstance("", "");
        accountInfoFragment = AccountInfoFragment.newInstance("", "");
        statisticsFragment = StatisticsFragment.newInstance("", "");
        moreFragment = MoreFragment.newInstance("", "");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getString(R.string.main);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = mainFragment;

        if(fragment == null) {
            String str = "";

//            try {
//                str = getDummyJsonString();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            fragment = mainFragment = MainFragment.newInstance(str, "");
            mTitle = getString(R.string.main);
            restoreActionBar();
        }
        switch (position) {
            case 0:
                fragment = mainFragment;
                mTitle = getString(R.string.main);
                break;
            case 1:
                fragment = waitOrderFragment;
                mTitle = getString(R.string.wait_order);
                break;
            case 2:
                fragment = ordersFragment;
                mTitle = getString(R.string.orders);
                break;
            case 3:
                fragment = accountInfoFragment;
                mTitle = getString(R.string.account_info);
                break;
            case 4:
                fragment = statisticsFragment;
                mTitle = getString(R.string.statistics);
                break;
            case 5:
                fragment = moreFragment;
                mTitle = getString(R.string.more);
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * 当在主页点击接单按钮时，跳转到接单页面
     */
    @Override
    public void onWaitOrder() {
        mNavigationDrawerFragment.selectItem(1);
    }
}
