package com.lmy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by limengyan on 2017/3/17.
 */

public class AudiosDb extends SQLiteOpenHelper {

    public static final String DATABASENAME = "musicdb.db";
    private static final int VERSION = 1;
    private static AudiosDb sInstance = null;
    private Context mContext;

    public AudiosDb(Context context) {
        super(context,DATABASENAME,null,VERSION);
        this.mContext=context;
    }


    public static final synchronized AudiosDb getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = new AudiosDb(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //DownFileStore.getInstance(mContext).onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
