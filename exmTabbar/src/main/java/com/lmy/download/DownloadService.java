package com.lmy.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

public class DownloadService extends Service {
    private String downloadUrl;

    public DownloadService() {
    }

    private DownloadTask downloadTask;
    private DownloadListener listener =new DownloadListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onProgress(int p) {
            getNotiManager().notify(1, getNoti("Downloading...", p));


        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotiManager().notify(1, getNoti("Download Success", -1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotiManager().notify(1, getNoti("Download Failed", -1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onCanceled() {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNoti(String s, int p) {
        Notification.Builder builder =new Notification.Builder(this);//?NotiCompact
        builder.setContentTitle(s);
        if (p >0){
            builder.setContentText(p+"%");
            builder.setProgress(100,p,false);
        }

        return builder.build();//?
    }

    private NotificationManager getNotiManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    private DownloadBinder dBinder=new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return dBinder;
    }

    private class DownloadBinder extends Binder {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void startDownload(String url){
            if (downloadTask==null){
                downloadUrl =url;
                downloadTask =new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNoti("Downloading...",0));
                Toast.makeText(DownloadService.this,"Downloading...",Toast.LENGTH_SHORT).show();
            }
        }

       public void pauseDownload(){
            if (downloadTask!=null){

            }
        }

        public void cancelDownload(){

        }


    }
}
