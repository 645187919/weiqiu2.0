package com.hzy.weiqiu;

import com.baidu.mapapi.SDKInitializer;
import com.hzy.weiqiu.fragment.finding.LocationService;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

public class MyApplication extends Application {
	private static Context context;
	public LocationService locationService;
    public Vibrator mVibrator;
	@Override
	public void onCreate() { 
		// TODO Auto-generated method stub
		context=getApplicationContext();
		 /***
         * 初始化定位sdk，建议在Application中创�??
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());  
       
    }
	public static Context getContext() {
		return context;
	}

}
