package com.lmy.login;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.Toolbar;

import com.example.exmtabbar.R;
import com.ryg.fragment.FragmentOne;
import com.ryg.fragment.FragmentThree;
import com.ryg.fragment.FragmentTwo;
import com.ryg.fragment.ui.IndicatorFragmentActivity;

import java.util.List;

/**
 * Created by limengyan on 2017/3/21.
 */

public class SignUpActV3 extends IndicatorFragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int supplyTabs(List<TabInfo> tabs) {
        // TODO Auto-generated method stub
        tabs.add(new TabInfo(FRAGMENT_ONE, getString(R.string.fragment_email),
                FragmentEmail.class));
        tabs.add(new TabInfo(FRAGMENT_TWO, getString(R.string.fragment_mobile),
                FragmentMobile.class));


        return FRAGMENT_ONE;
    }



}
