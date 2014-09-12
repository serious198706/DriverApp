package com.cy.customer.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cy.customer.R;
import com.cy.customer.adapter.MainAdapter;
import com.cy.customer.entity.MainItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.cy.customer.fragment.MainFragment.OnSwitchFragment} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View rootView;
    private ListView listView;
    private MainAdapter adapter;
    private List<MainItem> items;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnSwitchFragment mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();

        fragment.mParam1 = param1;
        fragment.mParam2 = param2;

        return fragment;
    }
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        items = new ArrayList<MainItem>();

        try {
            JSONObject jsonObject = new JSONObject(mParam1);
            items.add(new MainItem("已消费金额", jsonObject.getString("moneySpent"), OrdersFragment.class));
            items.add(new MainItem("剩余金额", jsonObject.getString("moneyLeft"), OrdersFragment.class));
            items.add(new MainItem("完成订单数", jsonObject.getString("orderCount"), OrdersFragment.class));
            items.add(new MainItem("积分", jsonObject.getString("score"), ScoreFragment.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new MainAdapter(getActivity(), items);

        listView = (ListView)rootView.findViewById(R.id.mainList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i <= 2) {
                    if (mListener != null) {
                        mListener.onSwitchFragment(2);
                    }
                } else if(i == 3) {
                    if (mListener != null) {
                        mListener.onSwitchFragment(4);
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSwitchFragment) activity;
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
    public interface OnSwitchFragment {
        // TODO: Update argument type and name
        public void onSwitchFragment(int index);
    }

}
