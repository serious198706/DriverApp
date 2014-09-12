package com.cy.driver.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.driver.R;
import com.cy.driver.layout.CurrentOrderLayout;
import com.cy.driver.layout.HistoryOrdersLayout;
import com.cy.driver.utils.Constants;
import com.cy.library.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrdersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class OrdersFragment extends Fragment implements ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View rootView;

    private ViewPager viewPager;

    private CurrentOrderLayout currentOrderLayout;
    private HistoryOrdersLayout historyOrdersLayout;

    private List<View> views;

    private TextView currentOrderTab;
    private TextView historyOrdersTab;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_orders, container, false);

        currentOrderLayout = new CurrentOrderLayout(getActivity());
        historyOrdersLayout = new HistoryOrdersLayout(getActivity());

        initTextView();
        initViewPager();

        return rootView;
    }

    /**
     * 初始化viewPager，用来承载各个模块，可以通过滑动切换
     */
    private void initViewPager() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        views = new ArrayList<View>();

        views.add(currentOrderLayout);
        views.add(historyOrdersLayout);

        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
    }

    /**
     * 初始化标签，用来标识当前模块，可以点击
     */
    private void initTextView() {
        currentOrderTab = (TextView) rootView.findViewById(R.id.currentOrderTab);
        historyOrdersTab = (TextView) rootView.findViewById(R.id.historyOrdersTab);

        currentOrderTab.setOnClickListener(new MyViewPagerAdapter.MyOnClick(viewPager, 0));
        historyOrdersTab.setOnClickListener(new MyViewPagerAdapter.MyOnClick(viewPager, 1));

        selectTab(0);
    }

    private void selectTab(int index) {
        currentOrderTab.setTextColor(index == 0 ? Constants.selectedColor : Constants.unselectedColor);
        historyOrdersTab.setTextColor(index == 1 ? Constants.selectedColor : Constants.unselectedColor);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        selectTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
