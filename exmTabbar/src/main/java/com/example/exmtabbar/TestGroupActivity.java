package com.example.exmtabbar;

import com.example.exmtabbar.activityitem.FirstActivity;
import com.example.exmtabbar.activityitem.SecondActivity;
import com.example.exmtabbar.activityitem.ThirdActivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class TestGroupActivity extends ActivityGroup implements OnClickListener {
	private static final String TAG = "TestGroupActivity";
	private Bundle mBundle = new Bundle();

	private LinearLayout layout_container, layout_first, layout_second, layout_third;
	private ImageView img_first, img_second, img_third;
	private TextView txt_first, txt_second, txt_third;
	public static final int PAGE_FIRST = 1001;
	public static final int PAGE_SECOND = 1002;
	public static final int PAGE_XINXI = 1003;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_group);
		layout_container = (LinearLayout) findViewById(R.id.layout_container);
		layout_first = (LinearLayout) findViewById(R.id.layout_first);
		layout_second = (LinearLayout) findViewById(R.id.layout_second);
		layout_third = (LinearLayout) findViewById(R.id.layout_third);
		img_first = (ImageView) findViewById(R.id.img_first);
		img_second = (ImageView) findViewById(R.id.img_second);
		img_third = (ImageView) findViewById(R.id.img_third);
		txt_first = (TextView) findViewById(R.id.txt_first);
		txt_second = (TextView) findViewById(R.id.txt_second);
		txt_third = (TextView) findViewById(R.id.txt_third);

		layout_first.setOnClickListener(this);
		layout_second.setOnClickListener(this);
		layout_third.setOnClickListener(this);
		mBundle.putString("tag", TAG);
		
		changeContainerView(PAGE_FIRST);
	}

	private void changeContainerView(int page) {
		switch (page) {
		case PAGE_FIRST:
			layout_first.setBackgroundResource(R.drawable.ic_tabbar_bg_click);
			layout_second.setBackgroundResource(R.drawable.ic_tabbar_bg_normal);
			layout_third.setBackgroundResource(R.drawable.ic_tabbar_bg_normal);
			img_first.setBackgroundResource(R.drawable.ic_tabbar_first_pressed);
			img_second.setBackgroundResource(R.drawable.ic_tabbar_second_normal);
			img_third.setBackgroundResource(R.drawable.ic_tabbar_third_normal);
			txt_first.setTextColor(getResources().getColor(R.color.tab_text_selected));
			txt_second.setTextColor(getResources().getColor(R.color.tab_text_normal));
			txt_third.setTextColor(getResources().getColor(R.color.tab_text_normal));
			toActivity("item1", new Intent(this, FirstActivity.class).putExtras(mBundle));
			return;
		case PAGE_SECOND:
			layout_first.setBackgroundResource(R.drawable.ic_tabbar_bg_normal);
			layout_second.setBackgroundResource(R.drawable.ic_tabbar_bg_click);
			layout_third.setBackgroundResource(R.drawable.ic_tabbar_bg_normal);
			img_first.setBackgroundResource(R.drawable.ic_tabbar_first_normal);
			img_second.setBackgroundResource(R.drawable.ic_tabbar_second_pressed);
			img_third.setBackgroundResource(R.drawable.ic_tabbar_third_normal);
			txt_first.setTextColor(getResources().getColor(R.color.tab_text_normal));
			txt_second.setTextColor(getResources().getColor(R.color.tab_text_selected));
			txt_third.setTextColor(getResources().getColor(R.color.tab_text_normal));
			toActivity("item1", new Intent(this, SecondActivity.class).putExtras(mBundle));
			return;
		case PAGE_XINXI:
			layout_first.setBackgroundResource(R.drawable.ic_tabbar_bg_normal);
			layout_second.setBackgroundResource(R.drawable.ic_tabbar_bg_normal);
			layout_third.setBackgroundResource(R.drawable.ic_tabbar_bg_click);
			img_first.setBackgroundResource(R.drawable.ic_tabbar_first_normal);
			img_second.setBackgroundResource(R.drawable.ic_tabbar_second_normal);
			img_third.setBackgroundResource(R.drawable.ic_tabbar_third_pressed);
			txt_first.setTextColor(getResources().getColor(R.color.tab_text_normal));
			txt_second.setTextColor(getResources().getColor(R.color.tab_text_normal));
			txt_third.setTextColor(getResources().getColor(R.color.tab_text_selected));
			toActivity("item1", new Intent(this, ThirdActivity.class).putExtras(mBundle));
			return;
		default:
			return;
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.layout_first:
			changeContainerView(PAGE_FIRST);
			return;
		case R.id.layout_second:
			changeContainerView(PAGE_SECOND);
			return;
		case R.id.layout_third:
			changeContainerView(PAGE_XINXI);
			return;
		default:
			return;
		}
	}
	
	private void toActivity(String label, Intent intent) {
		layout_container.removeAllViews();
		View view = getLocalActivityManager().startActivity(label, intent).getDecorView();
		layout_container.addView(view);
	}

}
