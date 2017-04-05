package com.lmy.db;

import java.util.ArrayList;

import com.lmy.bean.audios;


import android.content.ContentResolver;
import android.database.Cursor;

public class DbDataOperation {
	

	public static ArrayList<audios> getAudioInfo(ContentResolver resolver){
		audios mAudio;
		ArrayList<audios> audioList=new ArrayList<audios>();
		return null;
		
//		Cursor cursor=resolver.query(uri, null, null, null, null);
//		if (cursor!=null) {
//			while (cursor.moveToNext()) {
//				mAudio=new audios();
//				mAudio.setAudioId(Integer.parseInt(getFieldContent(cursor,
//						DbTags.FIELD_A_ID)));
//				mAudio.setId(getFieldContent(cursor, DbTags.FIELD_ID));
//				mAudio.setMd5(getFieldContent(cursor, DbTags.FIELD_A_MD5));
//				mAudio.setUrl(getFieldContent(cursor, DbTags.FIELD_A_DOWNLOAD));
//				mAudio.setName(getFieldContent(cursor, DbTags.FIELD_A_NAME));
//				//mAudio.setAuthor(getFieldContent(cursor, DbTags.FIELD_A_AUTHOR));
//				mAudio.setPath(getFieldContent(cursor, DbTags.FIELD_A_PATH));
//				mAudio.setAddTime(getFieldContent(cursor,
//						DbTags.FIELD_A_ADD_TIME));
//				mAudio.setOpenTime(getFieldContent(cursor,
//						DbTags.FIELD_A_OPEN_TIME));
//				mAudio.setCategoryId(Integer.parseInt(getFieldContent(cursor,
//						DbTags.FIELD_A_CATEGORY_ID)));
//				mAudio.setCategroyName(getFieldContent(cursor,
//						DbTags.FIELD_A_CATEGORY_NAME));
//				mAudio.setSize(getFieldContent(cursor, DbTags.FIELD_A_SIZE));
//				mAudio.setProgress(getFieldContent(cursor,
//						DbTags.FIELD_A_PROGRESS));
//			}
//		}
	}

	private static String getFieldContent(Cursor cursor, String fieldname) {
		// TODO Auto-generated method stub
		
		return cursor.getString(cursor.getColumnIndex(fieldname));
	}
}
