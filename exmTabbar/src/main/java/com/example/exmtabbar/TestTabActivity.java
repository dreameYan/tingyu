package com.example.exmtabbar;

import com.example.exmtabbar.activityitem.FirstActivity;
import com.example.exmtabbar.activityitem.SecondActivity;
import com.example.exmtabbar.activityitem.ThirdActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TestTabActivity extends TabActivity implements OnCheckedChangeListener {
	private static final String TAG = "TestTabActivity";
	private Bundle mBundle = new Bundle();
	
	private TabHost tab_host;
	private CompoundButton first_button;
	private CompoundButton second_button;
	private CompoundButton third_button;
	private String FIRST_TAG = "first";
	private String SECOND_TAG = "second";
	private String THIRD_TAG = "third";

	private TabHost.TabSpec getNewTab(String spec, int label, int icon, Intent intent) {
		return tab_host
				.newTabSpec(spec)
				.setIndicator(getString(label), getResources().getDrawable(icon))
				.setContent(intent);
	}

	private void setButtonCheck(CompoundButton button) {
		if (button.equals(first_button)) {
			button.setChecked(true);
			second_button.setChecked(false);
			third_button.setChecked(false);
		} else if (button.equals(third_button)) {
			button.setChecked(true);
			second_button.setChecked(false);
			first_button.setChecked(false);
		} else if (button.equals(second_button)) {
			button.setChecked(true);
			first_button.setChecked(false);
			third_button.setChecked(false);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_tab);
		mBundle.putString("tag", TAG);
		tab_host = getTabHost();
		tab_host.addTab(getNewTab(FIRST_TAG, R.string.menu_first,
				R.drawable.tab_first_selector, new Intent(this, FirstActivity.class).putExtras(mBundle)));
		tab_host.addTab(getNewTab(SECOND_TAG, R.string.menu_second,
				R.drawable.tab_second_selector, new Intent(this, SecondActivity.class).putExtras(mBundle)));
		tab_host.addTab(getNewTab(THIRD_TAG, R.string.menu_third,
				R.drawable.tab_third_selector, new Intent(this, ThirdActivity.class).putExtras(mBundle)));

		 first_button = ((CompoundButton) findViewById(R.id.rbtn_tab_first));
		 first_button.setOnCheckedChangeListener(this);
		 second_button = ((CompoundButton) findViewById(R.id.rbtn_tab_second));
		 second_button.setOnCheckedChangeListener(this);
		 third_button = ((CompoundButton) findViewById(R.id.rbtn_tab_third));
		 third_button.setOnCheckedChangeListener(this);

		tab_host.setCurrentTabByTag(FIRST_TAG);
		setButtonCheck(first_button);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked == true) {
			setButtonCheck(buttonView);
			if (buttonView.equals(first_button)) {
				tab_host.setCurrentTabByTag(FIRST_TAG);
			} else if (buttonView.equals(second_button)) {
				tab_host.setCurrentTabByTag(SECOND_TAG);
			} else if (buttonView.equals(third_button)) {
				tab_host.setCurrentTabByTag(THIRD_TAG);
			}
		}
	}

}
