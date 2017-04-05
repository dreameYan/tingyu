package com.lmy.bean;

import com.avos.avoscloud.AVObject;

import android.R.string;

public class VoiceInfo extends AVObject{

	public static final Creator CREATOR = AVObjectCreator.instance;

	public static final String ID="objectId";
	public static final String CLASSIFY="classify";
	public static final String NAME="name";
	public static final String TIME="time";
	public static final String SENTENCES="sentences";
	public static final String SIZE="size";
	public static final String PROGRESS="progress";

	
	public VoiceInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public  String getClassify() {
		return getString(CLASSIFY);
	}
	
	void setClassify(String classify){
		put(CLASSIFY, classify);
	}
	
	public  String getName() {
		return getString(NAME);
	}
	
	void setName(String name){
		put(NAME, name);
	}

	public  String getId() {
		return getString(ID);
	}

	void setId(String voiceId){
		put(ID, voiceId);
	}
	
	public  String getTime() {
		return getString(TIME);
	}
	
	void setTime(String time){
		put(TIME, time);
	}
	
	public  String getSentences() {
		return getString(SENTENCES);
	}
	
	void setSentences(String sentences){
		put(SENTENCES, sentences);
	}
	
	public  long getSize() {
		return getLong(SIZE);
	}
	
	void setSize(long size){
		put(SIZE, size);
	}

	public long getProgress() {
		return getLong(PROGRESS);
	}

	void setProgress(long progress){
		put(PROGRESS, progress);
	}
	
	
}
