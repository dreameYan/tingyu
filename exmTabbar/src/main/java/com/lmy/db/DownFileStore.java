package com.lmy.db;

import android.content.Context;

/**
 * Created by limengyan on 2017/3/17.
 */
public class DownFileStore {

    private static DownFileStore sInstance;


    public DownFileStore(Context context){


    }

    public static synchronized DownFileStore getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = new DownFileStore(context.getApplicationContext());
        }

        return sInstance;
    }

}
