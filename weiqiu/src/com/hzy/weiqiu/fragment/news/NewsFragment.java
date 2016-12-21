package com.hzy.weiqiu.fragment.news;

import java.util.ArrayList;
import java.util.List;

import com.ITXY.weixin.R;
import com.ITXY.weixin.R.layout;
import com.hzy.weiqiu.ChatActivity;
import com.hzy.weiqiu.MainActivity;
import com.hzy.weiqiu.MyApplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsFragment extends Fragment implements OnItemClickListener{
	private ListView lv_news;
	
	
	

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {

			ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;

			if(allNews != null && allNews .size()>0)
			{
				
				NewsAdapter newsAdapter = new NewsAdapter(getContext(), allNews);
				lv_news.setAdapter(newsAdapter);
			}

		};
	};


	

	
	@Override 
	public void onAttach(Context context) {
		// TODO Auto-generated method stub
		super.onAttach(context);
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= inflater.inflate(R.layout.tab01, container, false);
		lv_news=(ListView) view.findViewById(R.id.weiqiu_listView);
		//1.��ȥ���ݿ��л�ȡ�������������չʾ��listview
		ArrayList<NewsBean> allnews_database = NewsUtils.getAllNewsForDatabase(getContext());

			if(allnews_database !=null && allnews_database.size()>0){
					//����һ��adapter���ø�listview
					NewsAdapter newsAdapter = new NewsAdapter(getContext(), allnews_database);
					lv_news.setAdapter(newsAdapter);
				}

				//2.ͨ�������ȡ�������ϵ�����������list��װ ,��ȡ����������Ҫ�����߳�����
				new Thread(new Runnable() {

					@Override
					public void run() {

						
						//������������
						ArrayList<NewsBean> allNews = NewsUtils.getAllNewsForNetWork(getContext());
						//ͨ��handler��msg���͵����߳�ȥ����Ui
						Message msg = Message.obtain();
						msg.obj = allNews;
						handler.sendMessage(msg);


					}
				}).start();


				//3.����listview��Ŀ�ĵ���¼�
				lv_news.setOnItemClickListener(this);





			
		
		
		return view;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//��Ҫ��ȡ��Ŀ��bean������url����ת
				NewsBean bean = (NewsBean) parent.getItemAtPosition(position);

				String url = bean.news_url;

				//��ת�����
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				startActivity(intent);
			}
		
	}



