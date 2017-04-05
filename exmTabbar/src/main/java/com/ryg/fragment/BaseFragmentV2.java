package com.ryg.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.exmtabbar.R;

import com.lmy.adapter.ListItemAdapterV2;
import com.lmy.adapter.ListItemAdapterV2.OnMyClickListener;
import com.lmy.bean.Constant;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import cn.trinea.android.common.util.DownloadManagerPro;



public class BaseFragmentV2 extends Fragment implements OnClickListener   {

	private Context mContext;
	private View mMainView;
	private Button bt_all;
	private Button bt_daf;
	private Button bt_dsh;
	private Button bt_news;
	private Button bt_daily;
	private Button bt_other;
	private ListView listView;
	private ListItemAdapterV2 listAdapter;
	private List<AVObject> mList=new ArrayList<>();
	
	private Button downBtn;
	private String url;
	private String size;


	
	DownloadManager downloadManager;
	DownloadManagerPro dPro;
	private long downloadId;


	
	
	
	
	public BaseFragmentV2() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity.getApplicationContext();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMainView = inflater.inflate(R.layout.fragment_one, container, false);
		bt_all = (Button) mMainView.findViewById(R.id.bt_all);
		bt_daf = (Button) mMainView.findViewById(R.id.bt_daf);
		bt_dsh = (Button) mMainView.findViewById(R.id.bt_dsh);
		bt_news = (Button) mMainView.findViewById(R.id.bt_news);
		bt_daily = (Button) mMainView.findViewById(R.id.bt_daily);
		bt_other = (Button) mMainView.findViewById(R.id.bt_other);
		listView = (ListView) mMainView.findViewById(R.id.list);
		//aa();
		listAdapter = new ListItemAdapterV2(mContext, mList);
		listView.setAdapter(listAdapter);
		/*listAdapter.setListener(new OnMyClickListener() {

			@Override
			public void download(int position, View view) {
				downBtn=(Button)view;
				url = mList.get(position).getString("url");
				size=mList.get(position).getString("size");
				downLoadFile(url);
			}
		});*/

		onClicks();
		
		
		downloadManager=(DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
		dPro=new DownloadManagerPro(downloadManager);
		
		return mMainView;
	}
	
	
	
	private void aa() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				Button  button = (Button)view.findViewById(R.id.bt_download);
				button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(mContext, "走起 ", 2).show();
					}
				});
			}
		}); 
	}


	public void onClicks() {
		bt_all.setOnClickListener(this);
		bt_daf.setOnClickListener(this);
		bt_dsh.setOnClickListener(this);
		bt_news.setOnClickListener(this);
		bt_daily.setOnClickListener(this);
		bt_other.setOnClickListener(this);
		//listView.setOnItemClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AVQuery<AVObject> avQuery = new AVQuery<>("VoiceInfo");// class name
		switch (v.getId()) {
		case R.id.bt_all:
			mList.clear();
			avQuery.orderByDescending("createdAt");
			// avQuery.include("owner");
			avQuery.findInBackground(new FindCallback<AVObject>() {
				@Override
				public void done(List<AVObject> list, AVException e) {
					// TODO Auto-generated method stub
					if (e == null) {
						mList.addAll(list);
						listAdapter.notifyDataSetChanged();
					} else {
						e.printStackTrace();
					}

				}
			});

			break;

		case R.id.bt_daf:
			mList.clear();
			avQuery.whereContains("classify", "DaF");
			avQuery.orderByDescending("createdAt");
			avQuery.findInBackground(new FindCallback<AVObject>() {
				@Override
				public void done(List<AVObject> list, AVException e) {
					// TODO Auto-generated method stub					
					if (e == null) {
						mList.addAll(list);
						listAdapter.notifyDataSetChanged();
					} else {
						e.printStackTrace();
					}

				}
			});

			break;

		case R.id.bt_dsh:
			mList.clear();
			Toast.makeText(mContext, "暂无音频", Toast.LENGTH_SHORT).show();
			break;

		case R.id.bt_news:
			mList.clear();
			avQuery.whereContains("classify", "新闻");
			avQuery.orderByDescending("createdAt");
			avQuery.findInBackground(new FindCallback<AVObject>() {
				@Override
				public void done(List<AVObject> list, AVException e) {
					// TODO Auto-generated method stub				
					if (e == null) {
						mList.addAll(list);
						listAdapter.notifyDataSetChanged();
					} else {
						e.printStackTrace();
					}

				}
			});
			break;

		case R.id.bt_daily:
			mList.clear();
			Toast.makeText(mContext, "暂无音频", Toast.LENGTH_SHORT).show();
			break;

		case R.id.bt_other:
			mList.clear();
			Toast.makeText(mContext, "暂无音频", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}
