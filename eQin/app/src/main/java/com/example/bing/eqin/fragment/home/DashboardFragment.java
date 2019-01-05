package com.example.bing.eqin.fragment.home;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bing.eqin.R;
import com.example.bing.eqin.activity.EspTouchActivity;
import com.example.bing.eqin.controller.DynamicLineChartController;
import com.example.bing.eqin.controller.MQTTController;
import com.example.bing.eqin.fragment.dashboard.ControllerFragment;
import com.example.bing.eqin.fragment.dashboard.SensorFragment;
import com.example.bing.eqin.model.MQTTDataItem;
import com.github.mikephil.charting.charts.LineChart;
import com.yanzhenjie.permission.AndPermission;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment{

//    private LineChart lineChart;
//    private DynamicLineChartController dynamicLineChartController;
//    private List<String> names = new ArrayList<>();
//    private List<Integer> colors = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager dashboardContainer;
    private SensorFragment sensorFragment;
    private ControllerFragment controllerFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorFragment = new SensorFragment();
        controllerFragment = new ControllerFragment();

        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CHANGE_NETWORK_STATE)
                .permission(Manifest.permission.CHANGE_WIFI_STATE)
                .permission(Manifest.permission.ACCESS_NETWORK_STATE)
                .permission(Manifest.permission.ACCESS_WIFI_STATE)
                .permission(Manifest.permission.INTERNET)
                .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tabLayout = view.findViewById(R.id.dashboard_tab_layout);
        dashboardContainer = view.findViewById(R.id.dashboard_container);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i==0)
                    return sensorFragment;
                else
                    return controllerFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

        dashboardContainer.setAdapter(adapter);
        tabLayout.setupWithViewPager(dashboardContainer);
        tabLayout.getTabAt(0).setText("传感器");
        tabLayout.getTabAt(1).setText("执行器");

//        lineChart = view.findViewById(R.id.chart);
//        setConfigToChart();

        return view;
    }



//    private void setConfigToChart() {
//        names.add("温度");
//        names.add("湿度");
//        colors.add(Color.CYAN);
//        colors.add(Color.BLUE);
//        dynamicLineChartController = new DynamicLineChartController(lineChart, names, colors);
//        dynamicLineChartController.setDescription("当前温湿度");
//        dynamicLineChartController.setLeftYAxis(100, 0, 10);
//        dynamicLineChartController.setRightYAxis(100, 0, 10);
//    }




}
