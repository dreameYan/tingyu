package com.lmy.login;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.lmy.bean.VoiceInfo;

import android.app.Application;

public class DemoApplication extends Application{
	@Override
	  public void onCreate() {
	    super.onCreate();
	    AVOSCloud.initialize(this,"2qewcoP52UQbMqQFd5VmL03i-gzGzoHsz","394WCmr17Dbmjd8xinOkM4VF");
	   // AVOSCloud.setNetworkTimeout(20 * 1000);
	    //AVObject.registerSubclass(VoiceInfo.class);	    
	   // AVOSCloud.setDebugLogEnabled(true);
	  }
}
