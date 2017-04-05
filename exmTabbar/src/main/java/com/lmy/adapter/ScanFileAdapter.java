package com.lmy.adapter;

import java.util.ArrayList;

import com.example.exmtabbar.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScanFileAdapter extends BaseAdapter {
	
	private ArrayList<FileInfo> mFileList;
	private LayoutInflater mInflater;
	private ViewHolder holder;

	public ScanFileAdapter(Context context,ArrayList<FileInfo> fileList) {
		// TODO Auto-generated constructor stub
		this.mFileList=fileList;
		this.mInflater=LayoutInflater.from(context);
		
	}

	public static class FileInfo{
		private String filePath;
		private String fileName;
		private String fileSize;
		private FileType fileType;
		private String fileCategory;
		
		enum FileType {
			FILE, DIRECTORY;
		}
		public FileInfo(String filePath, String fileName, String fileCat, String fileSize, boolean isDir) {
			// TODO Auto-generated constructor stub
			this.setFilePath(filePath);
			this.setFileName(fileName);
			this.setFileCategory(fileCat);
			this.setFileSize(fileSize);
			setFileType(isDir ? FileType.DIRECTORY : FileType.FILE);
			
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		

		public CharSequence getFileSize() {
			return fileSize;
		}

		public void setFileSize(String fileSize) {
			this.fileSize = fileSize;
		}


		public String getFileCategory() {
			return fileCategory;
		}

		public void setFileCategory(String fileCategory) {
			this.fileCategory = fileCategory;
		}

		public FileType getFileType() {
			return fileType;
		}

		public void setFileType(FileType fileType) {
			this.fileType = fileType;
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFileList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mFileList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
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
		FileInfo fileInfo=(FileInfo) getItem(position);
		holder.title.setText(fileInfo.getFileName());//题目
		holder.type.setText(fileInfo.getFileCategory());//分类
		holder.sentences.setText(fileInfo.getFileSize());//大小
		return convertView;
	}

	class ViewHolder{
		public TextView title;
		public TextView type;
		public TextView sentences;//大小
		
		public Button download;
		public ProgressBar progressBar;				
	}
	
}
