package com.hzy.weiqiu;


import java.net.URI;

import com.ITXY.weixin.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends BaseActivity {

	private SharedPreferences pref;

	private SharedPreferences.Editor editor;

	private EditText accountEdit;

	private EditText passwordEdit;

	private Button login;

	private CheckBox rememberPass;
	private Button creatAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_tab);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText) findViewById(R.id.password);
		rememberPass = (CheckBox) findViewById(R.id.remember_pass);
		login = (Button) findViewById(R.id.login);
		creatAccount=(Button)findViewById(R.id.creatAccount);
		boolean isRemember = pref.getBoolean("remember_password", false);
		if (isRemember) {
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				if (account.equals("admin") && password.equals("123456")) {
					editor = pref.edit();
					if (rememberPass.isChecked()) {
						editor.putBoolean("remember_password", true);
						editor.putString("account", account);
						editor.putString("password", password);
					} else {
						editor.clear();
					}
					editor.commit();
					Intent intent = new Intent(Login.this, Loading.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(Login.this, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ", Toast.LENGTH_SHORT).show();
				}
			}
		});
		creatAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri=Uri.parse("http://www.qq.com");
				Intent intent=new Intent(Intent.ACTION_VIEW,uri);
				startActivity(intent);
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
	}

}
