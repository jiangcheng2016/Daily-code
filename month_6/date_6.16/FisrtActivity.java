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
		//修改标题栏的返回按钮  ActionBar
		ActionBar bar =  getActionBar();
		//将返回按钮
		bar.setDisplayHomeAsUpEnabled(true);
		
		//接收数据  intent 
		Intent intent = getIntent();
		//取值    保存在变量中
		String city = intent.getStringExtra("city");
		int num = intent.getIntExtra("num",0);//默认值
		//在哪个界面吐司，吐司内容，吐司多久
		Toast.makeText(FisrtActivity.this, city+""+num, Toast.LENGTH_SHORT).show();
		
		//请求网络数据
		new Thread(){
			public void run() {
				requestJuheData(
						"http://op.juhe.cn/onebox/basketball/nba?dtype=&=&key=10840016bca6a085e493545d4fa79348"
);
			};
		}.start();
	}
	
	
	    
	
	//自定义一个请求网络数据的方法
	public void requestJuheData(String path){
		try {
			//URL 地址  获得一个网络链接  获取数据
			URL url = new URL(path);
			//获得链接对象
			URLConnection conn = url.openConnection();
			//获得IO流
			InputStream is = conn.getInputStream();
			//包装 使用字符流，将数据读取
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			//StringBuilder
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line=br.readLine()) !=null) {
				sb.append(line);
			}
			content = sb.toString();
			//创建一个消息对象
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
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
             Log.e(tag, msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志    
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
			Toast.makeText(FisrtActivity.this, item.getTitle()+"点击了", Toast.LENGTH_LONG).show();
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
