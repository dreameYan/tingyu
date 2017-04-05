package com.example.exmtabbar.activityitem;

import com.example.exmtabbar.R;

import com.lmy.login.SignUpActV2;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ThirdActivity extends Activity implements OnClickListener {

	private TextView nikname;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		byId();
		onClicks();
		
	}
	
	public void byId(){

		nikname = (TextView) findViewById(R.id.nikname);
	}
	
	public void onClicks(){
		nikname.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.nikname:
			Intent intent=new Intent(ThirdActivity.this, SignUpActV2.class);
			startActivity(intent);
		}
	}
	
	

}
