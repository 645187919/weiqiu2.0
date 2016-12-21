package com.hzy.weiqiu.fragment.myself;

import com.ITXY.weixin.R;
import com.hzy.weiqiu.fragment.finding.SettingActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyselfFragment extends Fragment {
	
	   
	    
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tab03, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	        RelativeLayout re_myinfo = (RelativeLayout) getView().findViewById(
	                R.id.re_myinfo);
	        re_myinfo.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                startActivity(new Intent(getActivity(),
	                        MyUserInfoActivity.class));
	            }

	        });
	        RelativeLayout re_setting = (RelativeLayout) getView().findViewById(
	                R.id.re_setting);
	        re_setting.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                startActivity(new Intent(getActivity(), SettingActivity.class));
	            }

	        });
	       
	       
	    }

	    

	    @Override
	    public void onResume() {
	        super.onResume();
	       
	}}
