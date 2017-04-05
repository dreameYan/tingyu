package com.example.exmtabbar.fragmentitem;

import com.example.exmtabbar.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {
	private static final String TAG = "FirstFragment";
	protected View mView;
	protected Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity();
		mView = inflater.inflate(R.layout.activity_first, container, false);

		String tag = getArguments().getString("tag");
		TextView tv_first = (TextView) mView.findViewById(R.id.tv_first);
		tv_first.setText(tv_first.getText().toString()+"\n来源："+tag);
		
		return mView;
	}
	
}
