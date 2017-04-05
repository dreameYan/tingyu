package com.lmy.bean;

public class listening {

	private String title;
    private String startTime;
    private String endTime;
    
    public listening(String title,String startTime,String endTime){
    	this.title=title;
    	this.startTime=startTime;
    	this.endTime=endTime;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;//材料来源
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;//句数统计
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
