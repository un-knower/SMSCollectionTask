package com.cloudyang.messageupload;

import com.cloudyang.util.ActivityCollector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class StartActivity extends Activity {
	
	private final int SPLASH_DISPLAY_LENGHT = 3000; //�ӳ�����
	
	@Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start);
        ActivityCollector.addActivity(this);
        new Handler().postDelayed(new Runnable(){ 
 
         @Override
         public void run() { 
             Intent mainIntent = new Intent(StartActivity.this, MainLayout.class); 
             StartActivity.this.startActivity(mainIntent); 
             StartActivity.this.finish(); 
         } 
             
        }, SPLASH_DISPLAY_LENGHT); 
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	} 
	
	public static boolean isConnect(Context context) { 
        // ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ��� 
	    try { 
	        ConnectivityManager connectivity = (ConnectivityManager) context 
	                .getSystemService(Context.CONNECTIVITY_SERVICE); 
	        if (connectivity != null) { 
	            // ��ȡ�������ӹ���Ķ��� 
	            NetworkInfo info = connectivity.getActiveNetworkInfo(); 
	            if (info != null&& info.isConnected()) { 
	                // �жϵ�ǰ�����Ƿ��Ѿ����� 
	                if (info.getState() == NetworkInfo.State.CONNECTED) { 
	                    return true; 
	                } 
	            } 
	        } 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
        return false; 
    } 
	

}
