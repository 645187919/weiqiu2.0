package com.hzy.weiqiu;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.ITXY.weixin.R;
import com.hzy.weiqiu.fragment.finding.SettingFragment;
import com.hzy.weiqiu.fragment.frients.FriendsFragment;
import com.hzy.weiqiu.fragment.myself.MyselfFragment;
import com.hzy.weiqiu.fragment.news.News;
import com.hzy.weiqiu.fragment.news.NewsFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private ViewPager myViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragment=new ArrayList<Fragment>();
	 
	private LinearLayout myTabWeiQiu;
	private LinearLayout myTabMyself;
	private LinearLayout myTabAddress;
	private LinearLayout myTabSetting;
	private ImageButton myWeiqiuImg;
	private ImageButton mySelfImg;
	private ImageButton myAddressImg;
	private ImageButton mySeettingImg;
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		setIconEnable(menu,true);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void setIconEnable(Menu menu,boolean enable){
		try{  
			Class<?> clazz =Class.forName("com.android.internal.view.menu.MenuBuilder");
			Method m=clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
			m.setAccessible(true);
			m.invoke(menu, enable);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_tab_weixin:
			myViewPager.setCurrentItem(0);
			resetImg();
			myWeiqiuImg.setImageResource(R.drawable.tab_weixin_pressed);
			break;
		case R.id.id_tab_address:
			myViewPager.setCurrentItem(1);
			resetImg();
			myAddressImg.setImageResource(R.drawable.tab_address_pressed);
			break;
		case R.id.id_tab_myself:
			myViewPager.setCurrentItem(2);
			resetImg();
			mySelfImg.setImageResource(R.drawable.tab_myself_press);
			break;
		case R.id.id_tab_setting:
			myViewPager.setCurrentItem(3);
			resetImg();
			mySeettingImg.setImageResource(R.drawable.tab_settings_pressed);
			break;
			
		
			

		default:
			break;
		}
		
	}
	private void initView(){
		
		  myViewPager=(ViewPager) findViewById(R.id.Viewpager);
		  myTabWeiQiu=(LinearLayout)findViewById(R.id.id_tab_weixin);
		  myTabMyself=(LinearLayout)findViewById(R.id.id_tab_myself);
		  myTabAddress=(LinearLayout)findViewById(R.id.id_tab_address);
		  myTabSetting=(LinearLayout)findViewById(R.id.id_tab_setting);
		  myWeiqiuImg=(ImageButton)findViewById(R.id.id_tab_weiqiu_img);
		  mySelfImg=(ImageButton)findViewById(R.id.id_tab_myself_img);
		  myAddressImg=(ImageButton)findViewById(R.id.id_tab_address_img);
		  mySeettingImg=(ImageButton)findViewById(R.id.id_tab_setting_img);
		  mFragment=new ArrayList<Fragment>();
		  Fragment tab01=new NewsFragment();
		  Fragment tab02=new FriendsFragment();
		  Fragment tab03=new MyselfFragment();
		  Fragment tab04=new SettingFragment();
		  mFragment.add(tab01);
		  mFragment.add(tab02);
		  mFragment.add(tab03);
		  mFragment.add(tab04);
		  mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFragment.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mFragment.get(arg0);
			}
		};
		  
		myViewPager.setAdapter(mAdapter); 
	}
	
	
	
	private void initEvent(){
		myTabWeiQiu.setOnClickListener(this);
		myTabMyself.setOnClickListener(this);
		myTabAddress.setOnClickListener(this);
		myTabSetting.setOnClickListener(this);
		
		myViewPager.setOnPageChangeListener(
			new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int arg0) {
					// TODO Auto-generated method stub
					int currentItem=myViewPager.getCurrentItem();
					switch(currentItem)
					{
					case 0:
						resetImg();
						myWeiqiuImg.setImageResource(R.drawable.tab_weixin_pressed);
						break;
					case 1:
						resetImg();
						mySelfImg.setImageResource(R.drawable.tab_myself_press);
						break;
					case 2:
						resetImg();
						myAddressImg.setImageResource(R.drawable.tab_address_pressed);
						break;
					case 3:
						resetImg();
						mySeettingImg.setImageResource(R.drawable.tab_settings_pressed);
						break;
						default:
							break;
						
					
					}
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}
			}	
				
				
		);
		
		
		
	}
	private void resetImg(){
		myWeiqiuImg.setImageResource(R.drawable.tab_weixin_normal);
		mySelfImg.setImageResource(R.drawable.tab_myself);
		myAddressImg.setImageResource(R.drawable.tab_address_normal);
		mySeettingImg.setImageResource(R.drawable.tab_settings_normal);
		
		
		
	}
	
	

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
