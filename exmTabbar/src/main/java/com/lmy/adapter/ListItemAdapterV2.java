package com.lmy.adapter;



import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVObject;
import com.example.exmtabbar.R;
import com.lmy.bean.Constant;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.trinea.android.common.util.DownloadManagerPro;
import cn.trinea.android.common.util.PreferencesUtils;

public class ListItemAdapterV2 extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<AVObject> mList;
	private OnMyClickListener listener;
	private DownloadManager downloadManager;
	private long downloadId;
	private DownloadManagerPro dPro;



	public String url;
	private DownloadObserver downloadObserver;
	private int status;
	private final static String KEY_NAME_DOWNLOAD_ID="downloadId";
	
	public interface OnMyClickListener{
		void download(int position,View view);
	}
	
	 public void setListener(OnMyClickListener listener) {
		this.listener = listener;
	}

	 protected CharSequence getNotiPercent(int progress, int max) {
		// TODO Auto-generated method stub
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

	public static boolean isDownloading(int downloadManagerStatus) {
	        return downloadManagerStatus == DownloadManager.STATUS_RUNNING
	                || downloadManagerStatus == DownloadManager.STATUS_PAUSED
	                || downloadManagerStatus == DownloadManager.STATUS_PENDING;
	    }

	public ListItemAdapterV2(Context context,List<AVObject> list) {
		// TODO Auto-generated constructor stub
		this.mContext=context;
		this.mList=list;
		this.mInflater=LayoutInflater.from(context);

		downloadManager=(DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
		 dPro=new DownloadManagerPro(downloadManager);
		downloadObserver=new DownloadObserver();
		context.getContentResolver().registerContentObserver(DownloadManagerPro.CONTENT_URI, true, downloadObserver);
	    //updateView();
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);//fastJson
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView==null) {
			convertView=mInflater.inflate(R.layout.listview_item_v2, null);
			holder=new ViewHolder();
			holder.title=(TextView) convertView.findViewById(R.id.tv_title);
			holder.type=(TextView) convertView.findViewById(R.id.tv_type);
			holder.sentences=(TextView) convertView.findViewById(R.id.tv_size);	
			holder.download=(Button)convertView.findViewById(R.id.bt_download);
			
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		holder.title.setText((CharSequence) mList.get(position).get("name"));//题目
		holder.type.setText((CharSequence) mList.get(position).get("classify"));//分类
		holder.sentences.setText( (CharSequence) mList.get(position).get("size"));//大小
		holder.download.setOnClickListener(new OnClickListener() {			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null){
					listener.download(position,v);					
				}
				
				url = mList.get(position).getString("url");
				downLoadFile(url);
				//updateView();
				
			}
		});	
		
		int[] bytesAndStatus = dPro.getBytesAndStatus(downloadId);
		status=bytesAndStatus[2];

		if (isDownloading(status)) {
        	if (bytesAndStatus[1] < 0) {
        		holder.sentences.setText("0%");
        	}else {
        		holder.sentences.setText(getNotiPercent(bytesAndStatus[0], bytesAndStatus[1]));
        	}
        } else {
        	if (status==DownloadManager.STATUS_SUCCESSFUL) {
        		holder.download.setBackgroundResource(R.drawable.anniu_down);
        		holder.download.setText("已下载");
        	}else if (status==DownloadManager.STATUS_FAILED) {
        		holder.download.setText("重试");
        	}else {
        		holder.download.setText("下载");
			}
		}
		
		
		return convertView;			
	}
	
	 class ViewHolder{
		public TextView title;
		public TextView type;
		public TextView sentences;//大小
		
		public Button download;
		public ProgressBar progressBar;				
	}

//	private Handler handler=new Handler(){
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			switch (msg.what) {
//				case 0:
//					int status = (Integer)msg.obj;
//					if (isDownloading(status)) {
//						if (msg.arg2<0) {
//							holder.sentences.setText("0%");
//						}else {
//							holder.sentences.setText(getNotiPercent(msg.arg1, msg.arg2));
//						}
//					} else {
//						if (status==DownloadManager.STATUS_SUCCESSFUL) {
//							holder.download.setBackgroundResource(R.drawable.anniu_down);
//							holder.download.setText("已下载");
//						}else if (status==DownloadManager.STATUS_FAILED) {
//							holder.download.setText("重试");
//						}else {
//							holder.download.setText("下载中");
//						}}
//					break;
//
//				default:
//					break;
//			}
//		};
//	};
		
	
	static final DecimalFormat DOUBLE_DECIMAL_FORMAT = new DecimalFormat("0.##");

    public static final int    MB_2_BYTE             = 1024 * 1024;
    public static final int    KB_2_BYTE             = 1024;

    /**
     * @param size
     * @return
     */
    public static CharSequence getSize(long size) {
        if (size <= 0) {
            return "0M";
        }

        if (size >= MB_2_BYTE) {
            return new StringBuilder(16).append(DOUBLE_DECIMAL_FORMAT.format((double)size / MB_2_BYTE)).append("M");
        } else if (size >= KB_2_BYTE) {
            return new StringBuilder(16).append(DOUBLE_DECIMAL_FORMAT.format((double)size / KB_2_BYTE)).append("K");
        } else {
            return size + "B";
        }
    }
    
    private void downLoadFile(String url) {
		// TODO Auto-generated method stub
		 
		 DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
		// 设置在什么网络情况下进行下载
		request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
		// 设置显示通知栏
		request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		request.setTitle("下载");		
		if (isFolderExists(Constant.filePath)) {
			request.setDestinationInExternalPublicDir(Constant.filePath, Constant.audioDownloadFolder);
		}	
		// 开始下载,变量是系统为当前的下载请求分配的一个唯一的ID，我们可以通过这个ID重新获得这个下载任务，进行一些自己想要进行的操作或者查询
		 downloadId = downloadManager.enqueue(request);	 
		 PreferencesUtils.putLong(mContext, KEY_NAME_DOWNLOAD_ID, downloadId);
		 //updateView();
	}
    
//    private void updateView() {
//		// TODO Auto-generated method stub
//		int[] bytesAndStatus = dPro.getBytesAndStatus(downloadId);
//		Toast.makeText(mContext, "a:"+bytesAndStatus[0]+"b:"+bytesAndStatus[1],Toast.LENGTH_SHORT).show();
//
//        handler.sendMessage(handler.obtainMessage(0, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
//	}
	
    private boolean isFolderExists(String dir) {
		// TODO Auto-generated method stub
		File folder = Environment.getExternalStoragePublicDirectory(dir);
		return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();

	}


	class DownloadObserver extends ContentObserver{
	
		public DownloadObserver() {
			super(null);
			// TODO Auto-generated constructor stub
		}
		 @Override
	        public void onChange(boolean selfChange) {
	           // updateView();
	        }		
	}

}


	