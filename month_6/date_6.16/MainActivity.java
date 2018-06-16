package com.oracleoaec.project;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 1.定义全局变量 导包 ctrl+shift+o
	EditText etTel, etMsg;
	Button btnTel, btnMsg;

	Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 加载布局
		setContentView(R.layout.taleng);
		// 2.关联控件 通过id 关联布局文件中的控件
		etMsg = (EditText) findViewById(R.id.etMsg);
		etTel = (EditText) findViewById(R.id.etTel);
		btnMsg = (Button) findViewById(R.id.btnMsg);
		btnTel = (Button) findViewById(R.id.btnTel);

		btn1 = (Button) findViewById(R.id.btn1);

		// 3.给按钮添加监听事件
		btnTel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 获得编辑框中的内容 ——字符串——去空格
				String num = etTel.getText().toString().trim();
				// 意图类 跳转页面 Intent
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ num));

				// 开始跳转到新的界面
				startActivity(intent);
			}
		});
		// 给第二个按钮添加监听事件
		btnMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 获得编辑框中的短信内容
				String msg = etMsg.getText().toString();

				// 获得电话号码
				String num = etTel.getText().toString().trim();


				if ((num == null || "".equals(num)) ||  (msg == null || "".equals(msg))) {
					Toast.makeText(MainActivity.this, "未输入手机号或短信内容",
							Toast.LENGTH_SHORT).show();
				} else {
					// SMSManager 获得对象 发短信
					SmsManager manager = SmsManager.getDefault();
					ArrayList<String> msgs = manager.divideMessage(msg);
					// 发短信
					manager.sendMultipartTextMessage(num,// 目的地址
							null, msgs,// 短信
							null, null);
					// 吐司提示
					Toast.makeText(MainActivity.this, "短信发送成功",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 跳转 Intent 从哪里跳转到哪里
				Intent intent = new Intent(MainActivity.this,
						FisrtActivity.class);
				// 添加需要传递的数据 放在intent对象中携带
				// 第一个 name 第二个值
				intent.putExtra("city", "烟台");
				intent.putExtra("num", 33);
				startActivity(intent);

				// finish方法 界面跳转以后，是否销毁当前界面
				// finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
