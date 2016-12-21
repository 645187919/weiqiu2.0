package com.hzy.weiqiu.fragment.finding;

import com.ITXY.weixin.R;
import com.hzy.weiqiu.ActivityCollector;
import com.hzy.weiqiu.BaseActivity;
import com.hzy.weiqiu.Login;
import com.hzy.weiqiu.MyApplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SettingActivity extends BaseActivity implements OnClickListener{

    /**
     * ��������Ϣ֪ͨ����
     */
    private RelativeLayout rl_switch_notification;
    /**
     * ������������
     */
    private RelativeLayout rl_switch_sound;
    /**
     * �����𶯲���
     */
    private RelativeLayout rl_switch_vibrate;
    /**
     * ��������������
     */
    private RelativeLayout rl_switch_speaker;

    /**
     * ������Ϣ֪ͨimageView
     */
    private ImageView iv_switch_open_notification;
    /**
     * �ر�����Ϣ֪ͨimageview
     */
    private ImageView iv_switch_close_notification;
    /**
     * ��������ʾimageview
     */
    private ImageView iv_switch_open_sound;
    /**
     * �ر�������ʾimageview
     */
    private ImageView iv_switch_close_sound;
    /**
     * ����Ϣ����ʾ
     */
    private ImageView iv_switch_open_vibrate;
    /**
     * �ر���Ϣ����ʾ
     */
    private ImageView iv_switch_close_vibrate;
    /**
     * ����������������
     */
    private ImageView iv_switch_open_speaker;
    /**
     * �ر���������������
     */
    private ImageView iv_switch_close_speaker;

   
    
    /**
     * �˳���ť
     */
    private Button logoutBtn;

   
 
     
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
      
        if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        rl_switch_notification = (RelativeLayout) this.findViewById(R.id.rl_switch_notification);
        rl_switch_sound = (RelativeLayout) this.findViewById(R.id.rl_switch_sound);
        rl_switch_vibrate = (RelativeLayout) this.findViewById(R.id.rl_switch_vibrate);
        rl_switch_speaker = (RelativeLayout) this.findViewById(R.id.rl_switch_speaker);

        iv_switch_open_notification = (ImageView) this.findViewById(R.id.iv_switch_open_notification);
        iv_switch_close_notification = (ImageView) this.findViewById(R.id.iv_switch_close_notification);
        iv_switch_open_sound = (ImageView) this.findViewById(R.id.iv_switch_open_sound);
        iv_switch_close_sound = (ImageView) this.findViewById(R.id.iv_switch_close_sound);
        iv_switch_open_vibrate = (ImageView) this.findViewById(R.id.iv_switch_open_vibrate);
        iv_switch_close_vibrate = (ImageView) this.findViewById(R.id.iv_switch_close_vibrate);
        iv_switch_open_speaker = (ImageView) this.findViewById(R.id.iv_switch_open_speaker);
        iv_switch_close_speaker = (ImageView) this.findViewById(R.id.iv_switch_close_speaker);
        logoutBtn = (Button) this.findViewById(R.id.btn_logout);     
        rl_switch_notification.setOnClickListener(this);
        rl_switch_sound.setOnClickListener(this);
        rl_switch_vibrate.setOnClickListener(this);
        rl_switch_speaker.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
      
      
    }

    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.rl_switch_notification:
            if (iv_switch_open_notification.getVisibility() == View.VISIBLE) {
                iv_switch_open_notification.setVisibility(View.INVISIBLE);
                iv_switch_close_notification.setVisibility(View.VISIBLE);
                rl_switch_sound.setVisibility(View.GONE);
                rl_switch_vibrate.setVisibility(View.GONE);
               
               
            } else {
                iv_switch_open_notification.setVisibility(View.VISIBLE);
                iv_switch_close_notification.setVisibility(View.INVISIBLE);
                rl_switch_sound.setVisibility(View.VISIBLE);
                rl_switch_vibrate.setVisibility(View.VISIBLE);
               
              
            }
            break;
        case R.id.rl_switch_sound:
            if (iv_switch_open_sound.getVisibility() == View.VISIBLE) {
                iv_switch_open_sound.setVisibility(View.INVISIBLE);
                iv_switch_close_sound.setVisibility(View.VISIBLE);
              
            } else {
                iv_switch_open_sound.setVisibility(View.VISIBLE);
                iv_switch_close_sound.setVisibility(View.INVISIBLE);
               
            }
            break;
        case R.id.rl_switch_vibrate:
            if (iv_switch_open_vibrate.getVisibility() == View.VISIBLE) {
                iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
                iv_switch_close_vibrate.setVisibility(View.VISIBLE);
              
            } else {
                iv_switch_open_vibrate.setVisibility(View.VISIBLE);
                iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
               
            }
            break;
        case R.id.rl_switch_speaker:
            if (iv_switch_open_speaker.getVisibility() == View.VISIBLE) {
                iv_switch_open_speaker.setVisibility(View.INVISIBLE);
                iv_switch_close_speaker.setVisibility(View.VISIBLE);
               
            } else {
                iv_switch_open_speaker.setVisibility(View.VISIBLE);
                iv_switch_close_speaker.setVisibility(View.INVISIBLE);
               
            }
            break;
        case R.id.btn_logout: //�˳���½
            logout();
            break;
     
        default:
            break;
        }

    }

    void logout() {
        final ProgressDialog pd = new ProgressDialog(SettingActivity.this);
        pd.setMessage("�����˳���½..");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        Intent intent=new Intent(MyApplication.getContext(),Login.class);
        startActivity(intent);
    }

    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        
        super.onSaveInstanceState(outState);
        
    }
    
    public void back(View view){
        
        finish();
    }
}
