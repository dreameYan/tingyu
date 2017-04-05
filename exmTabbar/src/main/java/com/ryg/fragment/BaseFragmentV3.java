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
import com.lmy.bean.Constant;
import com.lmy.bean.VoiceInfo;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.trinea.android.common.util.DownloadManagerPro;
import cn.trinea.android.common.util.PreferencesUtils;

public class BaseFragmentV3 extends Fragment implements OnClickListener{
	
	private ContentResolver resolver;
	private ListItemAdapterV2 listAdapter;
	private final  ArrayList<VoiceInfo> mList=new ArrayList<>();
	private View mMainView;
	private Button bt_all;
	private Button bt_daf;
	private Button bt_dsh;
	private Button bt_news;
	private Button bt_daily;
	private Button bt_other;
	private ListView listView;
	private Context mContext;
	private MyHandler handler;
	private DownloadManager downManger;
	private DownloadManagerPro downMangerPro;
	private DownloadChangeObserver downObserver;
	private CompleteReceiver completeReceiver;
	private RecyclerView listRView;
	private DownloadListAdapter downListAdapter;
	private long downloadId;
	private int status;//download status
	private DownloadListAdapter.ViewHolder holder;
	private String url;

    public BaseFragmentV3(){
        super();
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity.getApplicationContext();

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mMainView=inflater.inflate(R.layout.fragment_one, container,false);
		resolver=getActivity().getContentResolver();
		handler=new MyHandler();
		downManger=(DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
		downMangerPro=new DownloadManagerPro(downManger);

		
		initView();
		//sdSearch();
        //initData();


		downObserver=new DownloadChangeObserver();
		completeReceiver=new CompleteReceiver();

		return mMainView;
	}

    private void initData() {

    }



    @Override
	public void onResume() {
		super.onResume();
		//mContext.getContentResolver().registerContentObserver(downMangerPro.CONTENT_URI,true,downObserver);

	}

	@Override
	public void onPause() {
		super.onPause();
		//mContext.getContentResolver().unregisterContentObserver(downObserver);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	private void sdSearch() {
		// TODO Auto-generated method stub
		new sdScanAysnTask(mContext,0).execute();//查询sd图书
		new sdScanAysnTask(mContext,1).execute();//添加书架
	}

	void initView(){
		bt_all = (Button) mMainView.findViewById(R.id.bt_all);
		bt_daf = (Button) mMainView.findViewById(R.id.bt_daf);
		bt_dsh = (Button) mMainView.findViewById(R.id.bt_dsh);
		bt_news = (Button) mMainView.findViewById(R.id.bt_news);
		bt_daily = (Button) mMainView.findViewById(R.id.bt_daily);
		bt_other = (Button) mMainView.findViewById(R.id.bt_other);
		//listView = (ListView) mMainView.findViewById(R.id.list);

		bt_all.setOnClickListener(this);
		bt_daf.setOnClickListener(this);
		bt_dsh.setOnClickListener(this);
		bt_news.setOnClickListener(this);
		bt_daily.setOnClickListener(this);
		bt_other.setOnClickListener(this);
		//listView.setOnItemClickListener(this);

		//listRView=(RecyclerView)mMainView.findViewById(R.id.recyclerview);
		LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
		listRView.setLayoutManager(layoutManager);

		downListAdapter=new DownloadListAdapter(mList);
		listRView.setAdapter(downListAdapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//AVQuery<AVObject> avQuery = new AVQuery<>("VoiceInfo");
		AVQuery<VoiceInfo> avQuery = AVQuery.getQuery(VoiceInfo.class);

		switch (v.getId()) {
		case R.id.bt_all:
			mList.clear();
			avQuery.orderByDescending("createdAt");
			// avQuery.include("owner");
			avQuery.findInBackground(new FindCallback<VoiceInfo>() {
				@Override
				public void done(List<VoiceInfo> list, AVException e) {
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
			avQuery.findInBackground(new FindCallback<VoiceInfo>() {
				@Override
				public void done(List<VoiceInfo> list, AVException e) {
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
			avQuery.findInBackground(new FindCallback<VoiceInfo>() {
				@Override
				public void done(List<VoiceInfo> list, AVException e) {
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





	private class DownloadChangeObserver extends ContentObserver {
		/**
		 * Creates a content observer.
		 *
		 */
		public DownloadChangeObserver() {
			super(handler);
		}
	}


	private class CompleteReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			long reference =intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
			if (reference==downloadId) {
				downLoadFile(url);
				updateView();
				if (downMangerPro.getStatusById(downloadId) == DownloadManager.STATUS_SUCCESSFUL) {
					holder.download.setBackgroundResource(R.drawable.anniu_down);
					holder.download.setText("已下载");
				}
			}
		}
	}

	public class DownloadListAdapter extends RecyclerView.Adapter<DownloadListAdapter.ViewHolder> {

		private final List<VoiceInfo> mList;
		private String url;

		public DownloadListAdapter(List<VoiceInfo> list) {
			this.mList=list;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_v2,parent,false);
			 final ViewHolder holder=new ViewHolder(view);
			holder.download.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int positon=holder.getAdapterPosition();
					url = mList.get(positon).getString("url");
					downLoadFile(url);
					//updateView();
//					int[] bytesAndStatus = downMangerPro.getBytesAndStatus(downloadId);
//					status=bytesAndStatus[2];
//
//					if (isDownloading(status)) {
//						if (bytesAndStatus[1] < 0) {
//							holder.sentences.setText("0%");
//						}else {
//							holder.sentences.setText(getNotiPercent(bytesAndStatus[0], bytesAndStatus[1]));
//						}
//					} else {
//						if (status==DownloadManager.STATUS_SUCCESSFUL) {
//							holder.download.setBackgroundResource(R.drawable.anniu_down);
//							holder.download.setText("已下载");
//						}else if (status==DownloadManager.STATUS_FAILED) {
//							holder.download.setText("重试");
//						}else {
//							holder.download.setText("下载");
//						}
//					}
				}
			});
			return holder;
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			holder.title.setText((CharSequence) mList.get(position).get("name"));//题目
			holder.type.setText((CharSequence) mList.get(position).get("classify"));//分类
			holder.sentences.setText( (CharSequence) mList.get(position).get("size"));//大小

		}



		@Override
		public int getItemCount() {
			return mList.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder{

			private final TextView title;
			private final TextView type;
			private final TextView sentences;
			private final Button download;

			public ViewHolder(View itemView) {
				super(itemView);
				title=(TextView) itemView.findViewById(R.id.tv_title);
				type=(TextView) itemView.findViewById(R.id.tv_type);
				sentences=(TextView) itemView.findViewById(R.id.tv_size);
				download=(Button)itemView.findViewById(R.id.bt_download);
			}
		}
	}





	private String getNotiPercent(int progress, int max) {
		int rate = 0;
		if (progress <= 0 || max <= 0) {
			rate = 0;
		} else if (progress > max) {
			rate = 100;
		} else {
			rate = (int)((double)progress / max * 100);
		}
		return new StringBuilder(16).append(rate).append("%").toString();
	}

	private boolean isDownloading(int status) {
		return status == DownloadManager.STATUS_RUNNING
				|| status == DownloadManager.STATUS_PAUSED
				|| status == DownloadManager.STATUS_PENDING;
	}


	private void downLoadFile(String url) {
		// TODO Auto-generated method stub

		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
		// 设置在什么网络情况下进行下载
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
		// 设置显示通知栏
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		request.setTitle("下载");
		if (isFolderExists(Constant.filePath)) {
			request.setDestinationInExternalPublicDir(Constant.filePath, Constant.audioDownloadFolder);
		}
		// 开始下载,变量是系统为当前的下载请求分配的一个唯一的ID，我们可以通过这个ID重新获得这个下载任务，进行一些自己想要进行的操作或者查询
		downloadId = downManger.enqueue(request);
		//PreferencesUtils.putLong(mContext, KEY_NAME_DOWNLOAD_ID, downloadId);
		//updateView();
	}

	private void updateView() {
		int[] bytesAndStatus = downMangerPro.getBytesAndStatus(downloadId);
		handler.sendMessage(handler.obtainMessage(0, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
	}

	private boolean isFolderExists(String dir) {
		// TODO Auto-generated method stub
		File folder = Environment.getExternalStoragePublicDirectory(dir);
		return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
	}

	private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int status = (Integer) msg.obj;
                    if (isDownloading(status)) {
                        if (msg.arg2 < 0) {
                            holder.sentences.setText("0%");
                        } else {
                            holder.sentences.setText(getNotiPercent(msg.arg1, msg.arg2));
                        }
                    } else {
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            holder.download.setBackgroundResource(R.drawable.anniu_down);
                            holder.download.setText("已下载");
                        } else if (status == DownloadManager.STATUS_FAILED) {
                            holder.download.setText("重试");
                        } else {
                            holder.download.setText("下载中");
                        }
                    }
                    break;
            }
        }
    }
}
