package com.lmy.bean;

public class audios {

	private String Id;  				//服务器id
	private long AudioId;				//书架id
	private String Name;			// 名字
	private String Path;			// 路径
	private String AddTime;			//
	private String OpenTime;		// 
	private int CategoryId;			// 
	private String CategroyName;	//种类
	private String Size;			//大小
	private String Progress;		// 百分比
	private String Url;		//下载地址
	private String Md5;
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	public long getAudioId() {
		return AudioId;
	}
	public void setAudioId(long audioId) {
		AudioId = audioId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getAddTime() {
		return AddTime;
	}
	public void setAddTime(String addTime) {
		AddTime = addTime;
	}
	public String getOpenTime() {
		return OpenTime;
	}
	public void setOpenTime(String openTime) {
		OpenTime = openTime;
	}
	public int getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}
	public String getCategroyName() {
		return CategroyName;
	}
	public void setCategroyName(String categroyName) {
		CategroyName = categroyName;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public String getProgress() {
		return Progress;
	}
	public void setProgress(String progress) {
		Progress = progress;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getMd5() {
		return Md5;
	}
	public void setMd5(String md5) {
		Md5 = md5;
	}
}
