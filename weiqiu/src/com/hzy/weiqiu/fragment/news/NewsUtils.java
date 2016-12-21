package com.hzy.weiqiu.fragment.news;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.util.Log;

public class NewsUtils {

	public static String newsPath_url = "http://v.juhe.cn/toutiao/index?type=top&key=9918a2cfab25bd3bce959221be46e1c9";
	static ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
	
	
	//��װ���ŵ����ݵ�list�з���
	public static ArrayList<NewsBean> getAllNewsForNetWork(Context context) {
		
		try{
			//1.�����������ȡ��������
			//��ȡһ��url����ͨ��url����õ�һ��urlconnnection����
			URL url = new URL(newsPath_url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//�������ӵķ�ʽ�ͳ�ʱʱ��
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10*1000);
			//��ȡ������Ӧ��
			int code = connection.getResponseCode();
			if(code == 200){
				//��ȡ���󵽵�����Ϣ
				InputStream inputStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf_8"));
				StringBuilder response = new StringBuilder();
				String line;;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String string=response.toString();
				Log.i("Json����", string);
				parseJSONWithJSONObject(context, string);
				
				
				

				
					}

				//3.������ݿ��оɵ����ݣ����µ����ݻ��浽���ݿ���
					new NewsDaoUtils(context).delete();
					new NewsDaoUtils(context).saveNews(arrayList);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	//�����ݿ��л�ȡ�ϴλ��������������listview��չʾ
	public  static ArrayList<NewsBean> getAllNewsForDatabase(Context context) {
		
		return new NewsDaoUtils(context).getNews();

	}
	public static void parseJSONWithJSONObject(Context context,String jsonData) {
		
		JSONObject root_json= null;
		try {
			root_json = new JSONObject(jsonData);
			JSONObject jsonArray1 = root_json.getJSONObject("result");//��ȡroot_json�е�newss��ΪjsonArray����
			String string=jsonArray1.toString();
			
			Log.i("result",string);
			JSONArray jsonArray=jsonArray1.getJSONArray("data");
			for (int i = 0 ;i < jsonArray.length();i++){//ѭ������jsonArray
				JSONObject news_json = jsonArray.getJSONObject(i);//��ȡһ�����ŵ�json
				
				NewsBean newsBean = new NewsBean();
				
				
				
				newsBean. des = news_json.getString("author_name");
				newsBean. title = news_json.getString("title");
				newsBean. news_url = news_json.getString("url");
				newsBean. icon_url = news_json.getString("thumbnail_pic_s");
				
				arrayList.add(newsBean);
			}} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��һ���ַ�����װ��һ��json����
		
		}
	}
