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
	// 1.����ȫ�ֱ��� ���� ctrl+shift+o
	EditText etTel, etMsg;
	Button btnTel, btnMsg;

	Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ز���
		setContentView(R.layout.taleng);
		// 2.�����ؼ� ͨ��id ���������ļ��еĿؼ�
		etMsg = (EditText) findViewById(R.id.etMsg);
		etTel = (EditText) findViewById(R.id.etTel);
		btnMsg = (Button) findViewById(R.id.btnMsg);
		btnTel = (Button) findViewById(R.id.btnTel);

		btn1 = (Button) findViewById(R.id.btn1);

		// 3.����ť��Ӽ����¼�
		btnTel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// ��ñ༭���е����� �����ַ�������ȥ�ո�
				String num = etTel.getText().toString().trim();
				// ��ͼ�� ��תҳ�� Intent
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ num));

				// ��ʼ��ת���µĽ���
				startActivity(intent);
			}
		});
		// ���ڶ�����ť��Ӽ����¼�
		btnMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// ��ñ༭���еĶ�������
				String msg = etMsg.getText().toString();

				// ��õ绰����
				String num = etTel.getText().toString().trim();


				if ((num == null || "".equals(num)) ||  (msg == null || "".equals(msg))) {
					Toast.makeText(MainActivity.this, "δ�����ֻ��Ż��������",
							Toast.LENGTH_SHORT).show();
				} else {
					// SMSManager ��ö��� ������
					SmsManager manager = SmsManager.getDefault();
					ArrayList<String> msgs = manager.divideMessage(msg);
					// ������
					manager.sendMultipartTextMessage(num,// Ŀ�ĵ�ַ
							null, msgs,// ����
							null, null);
					// ��˾��ʾ
					Toast.makeText(MainActivity.this, "���ŷ��ͳɹ�",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// ��ת Intent ��������ת������
				Intent intent = new Intent(MainActivity.this,
						FisrtActivity.class);
				// �����Ҫ���ݵ����� ����intent������Я��
				// ��һ�� name �ڶ���ֵ
				intent.putExtra("city", "��̨");
				intent.putExtra("num", 33);
				startActivity(intent);

				// finish���� ������ת�Ժ��Ƿ����ٵ�ǰ����
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
