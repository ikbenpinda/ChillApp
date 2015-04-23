package com.hang.devicetodevice;

public interface Config {

	// used to share GCM regId with application server - using php app server
	//static final String APP_SERVER_URL = "http://192.168.1.17/gcm/gcm.php?shareRegId=1";

	// GCM server using java
	static final String APP_SERVER_URL = "http://145.93.250.73:8081/GCM_Server/GCMNotification";//-Device-To-Device/GCMNotification?";

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "294086667769";
	
	static final String REGISTER_NAME = "name";
	
	static final String MESSAGE_ACTIVITY = "activity";
    static final String MESSAGE_PLACE = "place";
    static final String DATE = "date";
    static final String TIME= "time";
	static final String TO_NAME = "toName";	

}
