package com.hzy.weiqiu;

import com.ITXY.weixin.R;

import android.content.Context;
import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

public class PlusActionProvider extends ActionProvider{
	private Context context;
	

	public PlusActionProvider(Context context2) {
		super(context2);
		this.context=context2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		subMenu.clear();
		subMenu.add("����Ⱥ��").setIcon(R.drawable.ofm_group_chat_icon);
		subMenu.add("��Ӻ���").setIcon(R.drawable.ofm_add_icon);
		subMenu.add("ɨһɨ").setIcon(R.drawable.ofm_qrcode_icon);
		subMenu.add("����").setIcon(R.drawable.ofm_feedback_icon);
		
		
		
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}
	
	

}
