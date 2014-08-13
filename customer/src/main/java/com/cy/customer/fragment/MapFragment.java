package com.cy.customer.fragment;

import android.app.Activity;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.cy.customer.R;
import com.cy.customer.entity.DriverInfo;
import com.cy.customer.view.DriverInfoView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View rootView;
    private MapView mapView;
    private BaiduMap mBaiduMap;
    private Marker mMarkerA;
    private Marker mMarkerB;
    private Marker mMarkerC;
    private Marker mMarkerD;
    private InfoWindow mInfoWindow;

    private DriverInfo driverA;
    private DriverInfo driverB;
    private DriverInfo driverC;
    private DriverInfo driverD;

    BitmapDescriptor bd = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_gcoding);

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        driverA = new DriverInfo(null, "张师傅", "13888888888", 2.0f);
        driverB = new DriverInfo(null, "李师傅", "13999999999", 4.0f);
        driverD = new DriverInfo(null, "王师傅", "13000000000", 3.5f);
        driverD = new DriverInfo(null, "赵师傅", "13777777777", 5.0f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapView = new MapView(getActivity(), new BaiduMapOptions());

        mBaiduMap = mapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
        initOverlay();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {
                DriverInfoView driverInfoView = new DriverInfoView(getActivity(), driverA);

                final LatLng ll = marker.getPosition();
                Point p = mBaiduMap.getProjection().toScreenLocation(ll);
                p.y -= 47;
                LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
                InfoWindow.OnInfoWindowClickListener listener = null;

                mInfoWindow = new InfoWindow(driverInfoView, llInfo, listener);
                mBaiduMap.showInfoWindow(mInfoWindow);
                mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        mBaiduMap.hideInfoWindow();
                    }

                    @Override
                    public boolean onMapPoiClick(MapPoi mapPoi) {
                        return false;
                    }
                });
                return true;
            }
        });

        return mapView;
    }

    public void initOverlay() {
        // add marker overlay
        LatLng llA = new LatLng(39.963175, 116.400244);
        LatLng llB = new LatLng(39.942821, 116.369199);
        LatLng llC = new LatLng(39.939723, 116.425541);
        LatLng llD = new LatLng(39.906965, 116.401394);

        OverlayOptions ooA = new MarkerOptions().position(llA).icon(bd)
                .zIndex(9).draggable(true);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
        OverlayOptions ooB = new MarkerOptions().position(llB).icon(bd)
                .zIndex(5);
        mMarkerB = (Marker) (mBaiduMap.addOverlay(ooB));
        OverlayOptions ooC = new MarkerOptions().position(llC).icon(bd)
                .perspective(false).anchor(0.5f, 0.5f).rotate(30).zIndex(7);
        mMarkerC = (Marker) (mBaiduMap.addOverlay(ooC));
        OverlayOptions ooD = new MarkerOptions().position(llD).icon(bd)
                .perspective(false).zIndex(7);
        mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));

        // add ground overlay
        LatLng southwest = new LatLng(39.92235, 116.380338);
        LatLng northeast = new LatLng(39.947246, 116.414977);
        LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
                .include(southwest).build();

        MapStatusUpdate u = MapStatusUpdateFactory
                .newLatLng(bounds.getCenter());
        mBaiduMap.setMapStatus(u);

        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
            }

            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(getActivity(),
                        "拖拽结束，新位置：" + marker.getPosition().latitude + ", "
                                + marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
            }

            public void onMarkerDragStart(Marker marker) {
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        mapView.onDestroy();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public void switchToListView() {


        Toast.makeText(getActivity(), "Switching...", Toast.LENGTH_SHORT).show();
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
