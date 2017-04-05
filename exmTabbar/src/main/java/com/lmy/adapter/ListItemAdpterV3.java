package com.lmy.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.exmtabbar.R;
import com.lmy.bean.Constant;
import com.lmy.bean.VoiceInfo;
import com.lmy.bean.audios;
import com.ryg.fragment.BaseFragmentV3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by limengyan on 2017/3/8.
 */

public class ListItemAdpterV3 extends RecyclerView.Adapter<ListItemAdpterV3.ViewHolder>{


    private List<VoiceInfo> mList;
    private String url;
    private DownloadManager downManger;
    private long downloadId;
    private long progress;
    private long totalSize;
    private int downPosition;
    private ArrayList<String> currentTaskList;//string?
    private boolean isCurrent=false;


    public ListItemAdpterV3(List<VoiceInfo> list, ArrayList<String> currentTaskList) {
        this.mList=list;
        this.currentTaskList=currentTaskList;
    }

    public void update(List<VoiceInfo> list, ArrayList<String> currentTaskList){
        this.mList=list;
        this.currentTaskList=currentTaskList;
        progress=0;
        totalSize=-1;
        notifyDataSetChanged();//与notifyItem区别？
    }

    public void notifyItem(long completed, long total){
        this.progress=completed;
        if (total!= -1){
            this.totalSize=total;//?
            notifyItemChanged(downPosition);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_v2,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.download.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int positon=holder.getAdapterPosition();//make holder final
                url = mList.get(positon).getString("url");
                downLoadFile(url);
            }
        });
        return null;
    }

    private void downLoadFile(String url) {
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

    private boolean isFolderExists(String filePath) {
        File folder = Environment.getExternalStoragePublicDirectory(filePath);//path exists
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getName());//题目
        holder.type.setText(mList.get(position).getClassify());//分类
        holder.sentences.setText((int) mList.get(position).getSize());//大小int?

        if (currentTaskList.size() > 0) {
            //L.D(d, TAG, "currentlist size = " + currentTaskList.size());
            isCurrent = currentTaskList.get(0).equals(mList.get(position).getId());
            if (isCurrent) {
            downPosition = position;
        }
//            if (currentTaskList.contains(mList.get(position).getId())) {
//                isPreparing = true;
//            }
        }

        if (isCurrent){
            progress = (progress > mList.get(position).getProgress()) ?
                    progress : mList.get(position).getProgress();
            totalSize =(totalSize > mList.get(position).getSize()) ?
                    totalSize : mList.get(position).getSize();


        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

      class ViewHolder extends RecyclerView.ViewHolder {
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

