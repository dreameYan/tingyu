package com.example.exmtabbar.fragmentitem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exmtabbar.R;

public class SecondFragment extends Fragment {
	private static final String TAG = "SecondFragment";
	protected View mView;
	protected Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity();
		mView = inflater.inflate(R.layout.activity_second, container, false);

		String tag = getArguments().getString("tag");
		TextView tv_second = (TextView) mView.findViewById(R.id.tv_second);
		tv_second.setText(tv_second.getText().toString()+"\n来源："+tag);
		
		return mView;
	}
	
}
