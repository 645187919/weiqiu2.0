package com.hzy.weiqiu.fragment.news;

import java.util.ArrayList;
import java.util.List;

import com.ITXY.weixin.R;
import com.ITXY.weixin.R.id;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	
	private ArrayList<NewsBean> list;
	private Context context;

	//ͨ�����췽������Ҫ��ʾ���������ݼ���
	public NewsAdapter(Context context,ArrayList<NewsBean> list){
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		//1.����converView�Ż�listview,����һ��view��Ϊgetview�ķ���ֵ������ʾһ����Ŀ
		if(convertView != null){
			view = convertView;
		}else {
			//context:������, resource:Ҫת����view�����layout��id, root:��layout��root(ViewGroup)��һ����Ϊcodify�ķ���ֵ,һ�㴫null
//			view = View.inflate(context, R.layout.item_news_layout, null);//��һ�������ļ�ת����һ��view����
			
			//ͨ��LayoutInflater������ת����view����
//			view =  LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);
			
			//ͨ��context��ȡϵͳ����õ�һ��LayoutInflater��ͨ��LayoutInflater��һ������ת��Ϊview����
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.item_news_layout, null);
			
		}
		//2.��ȡview�ϵ��ӿؼ�����
		MyImageView item_img_icon = (MyImageView) view.findViewById(R.id.item_img_icon);
		TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
		TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
		
		
		
		//3.��ȡpostionλ����Ŀ��Ӧ��list�����е��������ݣ�Bean����
		NewsBean newsBean = list.get(position);
		//4.���������ø���Щ�ӿؼ�����ʾ
		item_img_icon.setImageUrl(newsBean.icon_url);
		item_tv_title.setText(newsBean.title);
		item_tv_des.setText(newsBean.des);
		
		
	

		
		
		return view;
	}

}
