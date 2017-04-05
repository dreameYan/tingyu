package com.lmy.download;

/**
 * Created by limengyan on 2017/3/17.
 */

public interface DownloadListener {
    void onProgress(int p);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
