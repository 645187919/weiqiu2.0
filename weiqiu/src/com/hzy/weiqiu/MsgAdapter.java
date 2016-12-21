package com.hzy.weiqiu;

import java.util.List;

import com.ITXY.weixin.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdapter extends ArrayAdapter<Msg> {
	private int resourceId;
	public MsgAdapter(Context context,int textViewResourceId,List<Msg>objects){
		super(context, textViewResourceId,objects);
		resourceId=textViewResourceId;
		
	} 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Msg msg=getItem(position);
		View view;
		ViewHolder viewholder;
		
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewholder=new ViewHolder();
			viewholder.leftlayout=(LinearLayout) view.findViewById(R.id.left_layout);
			viewholder.rightlayout=(LinearLayout) view.findViewById(R.id.right_layout);
			viewholder.leftview=(TextView) view.findViewById(R.id.left_msg);
			viewholder.rightview=(TextView) view.findViewById(R.id.right_msg);
			view.setTag(viewholder);
			
			
		}
		else{
			view=convertView;
			viewholder=(ViewHolder) view.getTag();
		}
		if(msg.gettype()==Msg.TYPE_RECEIVE){
			viewholder.leftlayout.setVisibility(View.VISIBLE);
			viewholder.rightlayout.setVisibility(View.GONE);
			viewholder.leftview.setText(msg.getcontent());
			
		}
		else if(msg.gettype()==Msg.TYPE_SEND){
			viewholder.leftlayout.setVisibility(View.GONE);
			viewholder.rightlayout.setVisibility(View.VISIBLE);
			viewholder.rightview.setText(msg.getcontent());
		}
		
		
		// TODO 自动生成的方法存根
		return view;
	}
	class ViewHolder{
		LinearLayout leftlayout;
		LinearLayout rightlayout;
		TextView leftview;
		TextView rightview;
	}

}
