package com.hzy.weiqiu.fragment.news;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NewsDaoUtils {
	private NewsOpenHelper newsOpenHelper;

	public NewsDaoUtils(Context context){

		//����һ�����������
		newsOpenHelper = new NewsOpenHelper(context);

	}

	//ɾ�����ݿ��л���ľ�����
	public void delete(){

		//ͨ������������ȡһ�����ݿ��������
		SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
		db.delete("news", null, null);
		db.close();
	}

	//�����ݿ��������������
	public void saveNews(ArrayList<NewsBean> list){

		//ͨ������������ȡһ�����ݿ��������
		SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
		for (NewsBean newsBean : list) {
			ContentValues values = new ContentValues();
			
			values.put("title", newsBean.title);
			values.put("des", newsBean.des);
			values.put("icon_url", newsBean.icon_url);
			values.put("news_url", newsBean.news_url);
			
			db.insert("news", null, values);

		}

		db.close();
	}

	//�����ݿ��л�ȡ�������������
	public ArrayList<NewsBean> getNews(){
		ArrayList<NewsBean> list = new ArrayList<NewsBean>();
		//ͨ������������ȡһ�����ݿ��������
		SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from news", null);//��ѯ��ȡ����

		if(cursor != null && cursor.getCount() > 0){

			while(cursor.moveToNext()){

				NewsBean newsBean = new NewsBean();
				
				newsBean. title = cursor.getString(0);
				newsBean. des =	cursor.getString(1);
				newsBean. icon_url =	cursor.getString(2);
				newsBean. news_url =	cursor.getString(3);
				
				list.add(newsBean);
			}
		}

		db.close();
		cursor.close();


		return list;

	}



}
