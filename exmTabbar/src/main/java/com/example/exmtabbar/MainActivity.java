package com.example.exmtabbar;

import com.avos.avoscloud.AVOSCloud;
import com.lmy.login.Config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_tabhost = (Button) findViewById(R.id.btn_tabhost);
		Button btn_group = (Button) findViewById(R.id.btn_group);
		Button btn_fragment = (Button) findViewById(R.id.btn_fragment);
		btn_tabhost.setOnClickListener(this);
		btn_group.setOnClickListener(this);
		btn_fragment.setOnClickListener(this);
		
		
		setupAVOSCloud(false);
	}

	private void setupAVOSCloud(boolean config) {
		// TODO Auto-generated method stub
		if (!config) {
		      AVOSCloud.initialize(getApplication(),
		          Config.APP_ID, Config.APP_KEY);
		      return;
		    }
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_tabhost) {
			Intent intent = new Intent(this, TestTabActivity.class);
			startActivity(intent);
		} else if (v.getId() == R.id.btn_group) {
			Intent intent = new Intent(this, TestGroupActivity.class);
			startActivity(intent);
		} else if (v.getId() == R.id.btn_fragment) {
			Intent intent = new Intent(this, TestFragmentActivity.class);
			startActivity(intent);
		}
	}

}
