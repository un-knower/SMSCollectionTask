package com.cloudyang.messageupload;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.cloudyang.util.ActivityCollector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MainLayout extends BaseActivity {
	
	private int imageIds[];
	private String[] titles;
	private ArrayList<ImageView> images;
	private ArrayList<View> dots;
	private TextView title;
	private ViewPager mViewPager;
	private ViewPagerAdapter adapter;
	private int oldPosition = 0;//��¼��һ�ε��λ��
	private int currentItem; //��ǰҳ��
	private ScheduledExecutorService scheduledExecutorService;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		ActivityCollector.addActivity(this);
		
		LinearLayout beginButton = (LinearLayout) findViewById(R.id.ll_begin_sms);
		beginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
				String usernameString = sharedPreferences.getString("account", "");
				if(!usernameString.isEmpty()){
					Intent intent = new Intent(MainLayout.this, RulesDetail.class);
					startActivity(intent);
				}else{
					Intent intent = new Intent(MainLayout.this, Login.class);
					startActivity(intent);
				}
				
			}
		});
		
		
		//ͼƬID
        imageIds = new int[]{
            R.drawable.a,    
            R.drawable.b,    
            R.drawable.c   
        };
        
        //ͼƬ����
        titles = new String[]{
    		"�쿴����Ѫ��ů��", 
            "��������--��ķ˹",    
            "��ѪӲ��--��ɭ˹̹ɭ"
        };
        
        //��ʾ��ͼƬ
        images = new ArrayList<ImageView>();
        for(int i =0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            
            images.add(imageView);
        }
        
        //��ʾ�ĵ�
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        
        
        title = (TextView) findViewById(R.id.title);
        title.setText(titles[0]);
        
        mViewPager = (ViewPager) findViewById(R.id.vp);
        
        adapter = new ViewPagerAdapter(); 
        mViewPager.setAdapter(adapter);
        
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            


            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                title.setText(titles[position]);
                
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                
                oldPosition = position;
                currentItem = position;
            }
            
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                
            }
        });
        
    }
	
	
	private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.size();
        }

        //�Ƿ���ͬһ��ͼƬ
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//            super.destroyItem(container, position, object);
//            view.removeViewAt(position);
            view.removeView(images.get(position));
            
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            
            return images.get(position);
        }
    }


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	
	
	@Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        //ÿ��2�����л�һ��ͼƬ
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 2, TimeUnit.SECONDS);
    }
    
    //�л�ͼƬ
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            currentItem = (currentItem +1) % imageIds.length;
            //���½���
//            handler.sendEmptyMessage(0);
            handler.obtainMessage().sendToTarget();
        }
        
    }
    
    private Handler handler = new Handler(){

        @SuppressLint("HandlerLeak")
		@Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //���õ�ǰҳ��
            mViewPager.setCurrentItem(currentItem);
        }
        
    };

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
	

}
