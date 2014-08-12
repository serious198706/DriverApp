package com.cy.customer.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.cy.customer.R;
import com.cy.customer.fragment.BookFragment;
import com.cy.customer.fragment.MainFragment;
import com.cy.customer.fragment.MapFragment;
import com.cy.customer.fragment.MoreFragment;
import com.cy.customer.fragment.NavigationDrawerFragment;
import com.cy.customer.fragment.OrdersFragment;
import com.cy.customer.fragment.ScoreFragment;
import com.cy.customer.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        MapFragment.OnFragmentInteractionListener,
        MainFragment.OnFragmentInteractionListener,
        OrdersFragment.OnFragmentInteractionListener,
        BookFragment.OnFragmentInteractionListener,
        ScoreFragment.OnFragmentInteractionListener,
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
    private MapFragment mapFragment;
    private OrdersFragment ordersFragment;
    private BookFragment bookFragment;
    private ScoreFragment scoreFragment;
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

        try {
            str = getDummyJsonString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainFragment = MainFragment.newInstance(str, "");
        mapFragment = MapFragment.newInstance("", "");
        ordersFragment = OrdersFragment.newInstance("", "");
        bookFragment = BookFragment.newInstance("", "");
        scoreFragment = ScoreFragment.newInstance("", "");
        moreFragment = MoreFragment.newInstance("", "");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

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
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = mainFragment;

        if(fragment == null)
            fragment = mainFragment = MainFragment.newInstance("", "");

        switch (position) {
            case 0:
                fragment = mainFragment;
                break;
            case 1:
                fragment = mapFragment;
                break;
            case 2:
                fragment = ordersFragment;
                break;
            case 3:
                fragment = bookFragment;
                break;
            case 4:
                fragment = scoreFragment;
                break;
            case 5:
                fragment = moreFragment;
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.main);
                break;
            case 2:
                mTitle = getString(R.string.drivers);
                break;
            case 3:
                mTitle = getString(R.string.orders);
                break;
            case 4:
                mTitle = getString(R.string.book);
                break;
            case 5:
                mTitle = getString(R.string.score);
                break;
            case 6:
                mTitle = getString(R.string.more);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
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

    public String getDummyJsonString() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("moneySpent", "251.00");
        jsonObject.put("moneyLeft", "1249.00");
        jsonObject.put("orderCount", "4");
        jsonObject.put("score", "16");

        return jsonObject.toString();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
