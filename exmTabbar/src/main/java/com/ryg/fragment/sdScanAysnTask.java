package com.ryg.fragment;

import java.io.File;
import java.util.ArrayList;

import com.lmy.adapter.ScanFileAdapter.FileInfo;
import com.lmy.bean.audios;
import com.lmy.db.DbDataOperation;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.SyncStateContract.Constants;

public class sdScanAysnTask extends AsyncTask<Integer, Integer, String>{

	private final ContentResolver resolver;
	private int forWhat;
	private ArrayList<FileInfo> mFileLists;
	private ArrayList<audios> shelfData=new ArrayList<audios>();
	private Context context;

	public sdScanAysnTask(Context context, int forWhat) {
		// TODO Auto-generated constructor stub
		this.forWhat=forWhat;
		this.context=context;
		resolver=context.getContentResolver();
	}
	
	@Override
	protected void onPreExecute() {
		// 0
		if (forWhat == 0) {
			//showLoading(getActivity(), "SD卡扫描中...");
			if (mFileLists != null && mFileLists.size() > 0) {
				mFileLists.clear();
			}
		}
		// 1
		if (forWhat == 1) {
			//showLoading(getActivity(), "正加入书架中...");
		}
		// 2
		if (forWhat == 2) {
			//showLoading(getActivity(), "正在删除书籍...");
		}
		if (forWhat == 3) {
			// showLoading(MainActivity.this, "初始化书架...");
		}
		super.onPreExecute();
	}
	
	@Override
	protected String doInBackground(Integer... arg0) {
		// TODO Auto-generated method stub
		// 0
		if (forWhat == 0) {
			if (!android.os.Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {}
			//GetFiles();
		}
		// 1
		if (forWhat == 1) {
			DoneScanLocal();
		}
		// 2
		if (forWhat == 2) {
			delLocalShelf();
			
		}
		if (forWhat == 3) {
			loadShelfData();
		}
		return null;
	}

	private void GetFiles(String xx) {
		File filePath=new File(xx);
		File[] files=filePath.listFiles();

	}

	private void loadShelfData() {
		// TODO Auto-generated method stub
		shelfData=DbDataOperation.getAudioInfo(resolver);
	}

	private void delLocalShelf() {
		// TODO Auto-generated method stub

		
	}

	private void DoneScanLocal() {
		// TODO Auto-generated method stub
		
	}

}
