package com.example.exmtabbar.activityitem;

import java.util.ArrayList;

import com.example.exmbanner.ui.BannerPager;
import com.example.exmbanner.util.Utils;
import com.example.exmtabbar.R;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class FirstActivity extends Activity {
	
	private TextView tv_pager;
	private BannerPager mBanner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner_pager);
		
		/*Bundle bundle = getIntent().getExtras();
		String tag = bundle.getString("tag");
		TextView tv_first = (TextView) findViewById(R.id.tv_first);
		tv_first.setText(tv_first.getText().toString()+"\n来源："+tag);*/
		
		tv_pager = (TextView) findViewById(R.id.tv_pager);

		mBanner = (BannerPager) findViewById(R.id.banner_pager);
		LayoutParams params = (LayoutParams) mBanner.getLayoutParams();
		Point point = Utils.getSize(this);
		params.height = (int) (point.x * 250f/ 640f);
		mBanner.setLayoutParams(params);
		
		ArrayList<Integer> bannerArray = new ArrayList<Integer>();
		bannerArray.add(Integer.valueOf(R.drawable.banner_1));
		bannerArray.add(Integer.valueOf(R.drawable.banner_2));
		bannerArray.add(Integer.valueOf(R.drawable.banner_3));
		bannerArray.add(Integer.valueOf(R.drawable.banner_4));
		bannerArray.add(Integer.valueOf(R.drawable.banner_5));
		mBanner.setImage(bannerArray);
		
	}

}
