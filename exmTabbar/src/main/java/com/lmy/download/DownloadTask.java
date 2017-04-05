package com.lmy.download;

import android.os.AsyncTask;
import android.os.Environment;

import com.avos.avoscloud.okhttp.OkHttpClient;
import com.avos.avoscloud.okhttp.Request;
import com.avos.avoscloud.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by limengyan on 2017/3/17.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    public static  final int TYPE_SUCCESS= 0;
    public static  final int TYPE_FAILED= 1;
    public static  final int TYPE_PAUSED= 2;
    public static  final int TYPE_CANCEL= 3;

    private DownloadListener downloadListener;
    private int lastProgres;


    public  DownloadTask(DownloadListener listener){
        this.downloadListener=listener;
    }



    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        File file = null;

        try {
            long dLength = 0;//download length
            String dUrl =params[0];//? 传的参数就是网址
            String fileName =dUrl.substring(dUrl.lastIndexOf("/"));//?
            String direc = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS).getPath();
            file=new File(direc + fileName);
            if (file.exists()){
                dLength = file.length();
            }
            long contentLen = getContentLen(dUrl);
            if (contentLen==0){
                return TYPE_FAILED;
            }else if(contentLen== dLength){
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            {
                try {
                    if (inputStream!=null){
                        inputStream.close();
                    }
                    if (randomAccessFile!=null){
                        randomAccessFile.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];//?进度的更新
        if (progress > lastProgres){
            downloadListener.onProgress(progress);
            lastProgres = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_CANCEL:
                downloadListener.onCanceled();
                break;
            case TYPE_PAUSED:
                downloadListener.onPaused();
                break;

        }
    }

    private long getContentLen(String url) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (response!=null && response.isSuccessful()){
                long contentLength=response.body().contentLength();
                response.body().close();
                return contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
