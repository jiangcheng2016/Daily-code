package com.oracleoaec.project;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FisrtActivity extends Activity {
	TextView tv;
	String content;
	Button btn;
	//public void log(String tag, String msg);
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.arg1==1) {
				tv.setText(content);
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fisrt);
		tv =(TextView) findViewById(R.id.tv); 
		btn = (Button) findViewById(R.id.btn);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(FisrtActivity.this,SecondActivity.class);
				startActivity(intent);
				
			}
		});
		//�޸ı������ķ��ذ�ť  ActionBar
		ActionBar bar =  getActionBar();
		//�����ذ�ť
		bar.setDisplayHomeAsUpEnabled(true);
		
		//��������  intent 
		Intent intent = getIntent();
		//ȡֵ    �����ڱ�����
		String city = intent.getStringExtra("city");
		int num = intent.getIntExtra("num",0);//Ĭ��ֵ
		//���ĸ�������˾����˾���ݣ���˾���
		Toast.makeText(FisrtActivity.this, city+""+num, Toast.LENGTH_SHORT).show();
		
		//������������
		new Thread(){
			public void run() {
				requestJuheData(
						"http://op.juhe.cn/onebox/basketball/nba?dtype=&=&key=10840016bca6a085e493545d4fa79348"
);
			};
		}.start();
	}
	
	
	    
	
	//�Զ���һ�������������ݵķ���
	public void requestJuheData(String path){
		try {
			//URL ��ַ  ���һ����������  ��ȡ����
			URL url = new URL(path);
			//������Ӷ���
			URLConnection conn = url.openConnection();
			//���IO��
			InputStream is = conn.getInputStream();
			//��װ ʹ���ַ����������ݶ�ȡ
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			//StringBuilder
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line=br.readLine()) !=null) {
				sb.append(line);
			}
			content = sb.toString();
			//����һ����Ϣ����
			Message message = new Message();
			message.arg1 = 1;
			handler.sendMessage(message);
			//L(">>>>",content);
			
			/*Log.i(">>>>>>>>>>>>>",str.substring(0,str.length()/4));
			Log.i(">>>>>>>>>>>>>",str.substring(str.length()/4,str.length()*2/4));
			Log.i(">>>>>>>>>>>>>",str.substring(str.length()*2/4,str.length()*3/4));
			Log.i(">>>>>>>>>>>>>",str.substring(str.length()*3/4));*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*public void L(String tag, String msg) {
        if (tag == null || tag.length() == 0 
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize ) {// ����С�ڵ�������ֱ�Ӵ�ӡ
             Log.e(tag, msg);
        }else {
            while (msg.length() > segmentSize ) {// ѭ���ֶδ�ӡ��־
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// ��ӡʣ����־    
        }
    }*/
	
	
	

	private void log() {
		// TODO Auto-generated method stub
		
	}







	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fisrt, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.item1:
			Toast.makeText(FisrtActivity.this, item.getTitle()+"�����", Toast.LENGTH_LONG).show();
			break;
		case R.id.item2:
			Intent intent = new Intent(FisrtActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
