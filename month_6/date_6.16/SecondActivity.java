package com.oracleoaec.project;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends Activity {

	TextView tv_temp,tv_time,tv_data;
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		/*tv_temp = (TextView) findViewById(R.id.temp);
		tv_time = (TextView) findViewById(R.id.time);
		tv_data = (TextView) findViewById(R.id.data);*/
		
		new Thread(){
			public void run() {
				
			};
		}.start();
		
	}
	/*public void JXjuheData(String res){
		try {
			JSONObject json = new JSONObject(res);
			JSONObject result = json.getJSONObject("result");
			JSONObject data = result.getJSONObject("data");
			JSONObject realtime = data.getJSONObject("realtime");
			//�����ĸ���ʱ��
			String time = realtime.getString("time");
			int week = realtime.getInt("week");
			//weather
			JSONObject weather = realtime.getJSONObject("weather");
			String temperature = weather.getString("temperature");
			
			//δ�����������
			JSONArray arr_weather = data.getJSONArray("weather");
			JSONObject day1 = arr_weather.getJSONObject(0);
			String day1_data = day1.getString("data");
			
			//��ʾ�ڽ����ϡ�������UI
			tv_temp.setText(temperature);
			tv_time.setText(time);
			tv_data.setText(day1_data);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void requestJuheData(String path){
		try {
			String path1 = "http://op.juhe.cn/onebox/basketball/nba?dtype=&=&key=10840016bca6a085e493545d4fa79348";
						
			//URL ��ַ  ���һ����������  ��ȡ����
			URL url = new URL(path1);
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
			Log.i("????", sb.toString());
			
			//JXjuheData(sb.toString());
			
			
			//����һ����Ϣ����
			Message message = new Message();
			message.arg1 = 1;
			handler.sendMessage(message);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
