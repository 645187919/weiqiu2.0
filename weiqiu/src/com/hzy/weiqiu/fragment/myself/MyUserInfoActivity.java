package com.hzy.weiqiu.fragment.myself;


import com.ITXY.weixin.R;
import com.google.zxing.WriterException;

import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyUserInfoActivity extends Activity {
private Button scanButton;
private TextView text;
private EditText input;
private Button genButton;
private ImageView img; 

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.acticity_erweima);
	
	scanButton = (Button) findViewById(R.id.scan);
	text = (TextView) findViewById(R.id.text);
	scanButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Toast.makeText( getApplicationContext(), "你写可以扫描条形码或者二维码", Toast.LENGTH_SHORT).show();
			Intent  startScan = new Intent(getApplicationContext(), CaptureActivity.class);
//			startActivity(startScan);
			startActivityForResult(startScan, 0);
		}
	});
	
	input = (EditText) findViewById(R.id.input);
	genButton = (Button) findViewById(R.id.gen);
	img = (ImageView) findViewById(R.id.img);
	genButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			String in = input.getText().toString();
			if(in.equals("")){
				Toast.makeText(getApplicationContext(), "请输入文本", Toast.LENGTH_SHORT).show();
			}else {
				try {
					
					Bitmap qrcode = EncodingHandler.createQRCode(in, 400);
					img.setImageBitmap(qrcode);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	});
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_OK) {
		String result = data.getExtras().getString("result");
		text.setText(result);
	}
}
}



