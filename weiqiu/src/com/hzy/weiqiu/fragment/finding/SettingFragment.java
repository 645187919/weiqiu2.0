package com.hzy.weiqiu.fragment.finding;

import com.ITXY.weixin.R;
import com.ITXY.weixin.R.id;
import com.hzy.weiqiu.MyApplication;
import com.zxing.activity.CaptureActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SettingFragment extends Fragment implements OnClickListener {
	private RelativeLayout find_youxi;
	private RelativeLayout find_gouwu;
	private RelativeLayout find_yaoyiyao;
	private RelativeLayout find_saoyisao;
	private RelativeLayout find_location;
	private RelativeLayout find_POI;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		return inflater.inflate(R.layout.tab04, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		find_youxi = (RelativeLayout) getView().findViewById(R.id.re_youxi);
		find_gouwu = (RelativeLayout) getView().findViewById(R.id.re_gouwu);
		find_yaoyiyao=(RelativeLayout) getView().findViewById(R.id.re_yaoyiyao);
		find_saoyisao=(RelativeLayout) getView().findViewById(R.id.re_erweima);
		find_location=(RelativeLayout) getView().findViewById(R.id.re_fujin);
		find_POI=(RelativeLayout) getView().findViewById(R.id.re_piaoliuping);
		find_youxi.setOnClickListener(this);
		find_gouwu.setOnClickListener(this);
		find_yaoyiyao.setOnClickListener(this);
		find_saoyisao.setOnClickListener(this);
		find_location.setOnClickListener(this);
		find_POI.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.re_youxi:
			Uri uri = Uri.parse("http://www.4399.com");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case R.id.re_gouwu:
			Uri uri1 = Uri.parse("http://www.tianmao.com");
			Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
			startActivity(intent1);
			break;
		case R.id.re_yaoyiyao:
			Intent intent2=new Intent(MyApplication.getContext(),YaoyiyaoActivity.class);
			startActivity(intent2);
			break;
		case R.id.re_erweima:
			Intent intent3=new Intent(MyApplication.getContext(),CaptureActivity.class);
			startActivity(intent3);
			break;
		case R.id.re_fujin:
			Intent intent4=new Intent(MyApplication.getContext(),LocationFilter.class);
			startActivity(intent4);
			break;
		case R.id.re_piaoliuping:
			Intent intent5=new Intent(MyApplication.getContext(),PoiSearchDemo.class);
			startActivity(intent5);
			break;
			

		default:
			break;
		}
	}

}
