package com.hzy.weiqiu;

import java.util.ArrayList;
import java.util.List;

import com.ITXY.weixin.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity extends Activity {
	private ListView mylistview;
	private EditText inputtext;
	private Button send;
	private MsgAdapter adapter;
	private List<Msg> msgList=new ArrayList<Msg>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		initMsgs();
		adapter=new MsgAdapter(ChatActivity.this, R.layout.chat_listview_item, msgList);
		mylistview=(ListView) findViewById(R.id.msg_listview);
		send=(Button) findViewById(R.id.send);
		inputtext=(EditText) findViewById(R.id.input_text);
		mylistview.setAdapter(adapter);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String content=inputtext.getText().toString();
				if(!"".equals(content)){
					Msg msg=new Msg(content,Msg.TYPE_SEND);
					msgList.add(msg);
					adapter.notifyDataSetChanged();
					mylistview.setSelection(msgList.size());
					inputtext.setText("");
					
					
					
				}
				
				// TODO 自动生成的方法存根
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void initMsgs(){
		Msg msg1=new Msg("hello",Msg.TYPE_RECEIVE);
		msgList.add(msg1);
		Msg msg2=new Msg("hello,Nice to meet you",Msg.TYPE_SEND);
		msgList.add(msg2);
		Msg msg3=new Msg("this tom,Nice to meet you",Msg.TYPE_RECEIVE);
		msgList.add(msg3);
		
	}
}



