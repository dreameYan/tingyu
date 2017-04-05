package com.example.exmtabbar.activityitem;

import java.util.List;

import com.example.exmtabbar.R;
import com.example.exmtabbar.R.id;
import com.example.exmtabbar.R.layout;
import com.ryg.fragment.FragmentOne;
import com.ryg.fragment.FragmentThree;
import com.ryg.fragment.FragmentTwo;
import com.ryg.fragment.ui.IndicatorFragmentActivity;
import com.ryg.fragment.ui.IndicatorFragmentActivity.TabInfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends IndicatorFragmentActivity {
	
	public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
	}

	@Override
	protected int supplyTabs(List<TabInfo> tabs) {
		// TODO Auto-generated method stub
		tabs.add(new TabInfo(FRAGMENT_ONE, getString(R.string.fragment_one),
                FragmentOne.class));
        tabs.add(new TabInfo(FRAGMENT_TWO, getString(R.string.fragment_two),
                FragmentTwo.class));
        tabs.add(new TabInfo(FRAGMENT_THREE, getString(R.string.fragment_three),
                FragmentThree.class));
		
        return FRAGMENT_TWO;
	}

}
