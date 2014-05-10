package com.dogs;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dogs.base.BaseActivity;
import com.dogs.util.Encrypt;
import com.dogs.util.Util;

public class MainActivity extends BaseActivity implements OnClickListener {

	private final int INTENTFILE = 1;
	private Button selFile;
	private TextView showFilePath;
	private Button ads;
	private Button dls;
	private InputStream in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private final void init() {

		selFile = (Button) findViewById(R.id.selFile);
		showFilePath = (TextView) findViewById(R.id.showFilePath);
		ads = (Button) findViewById(R.id.ads);
		dls = (Button) findViewById(R.id.dls);
		selFile.setOnClickListener(this);
		ads.setOnClickListener(this);
		dls.setOnClickListener(this);

	}

	private void selFile() {
		Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
		intent.setType("*/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		/* 取得相片后返回本画面 */
		startActivityForResult(intent, INTENTFILE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			try {
				Uri uri = data.getData();
				String uris = Uri.decode(uri.toString());
				Log.e("uri", uris);
				ContentResolver cr = getContentResolver();
				in = cr.openInputStream(uri);
				String[] temp = uris.split("/");
				String fileName = temp[temp.length-1];
				Log.e("file", fileName);
				showFilePath.setText(fileName);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		if (v == selFile) {
			Toast("sel");
			selFile();
		} else if (v == ads) {
			if (Util.isEmpty(showFilePath.getText().toString())) {
				Toast("请先选择文件");
				return;
			}
			Dialog progressDialog = ProgressDialog.show(this, null,"正在加密...");
			Toast(Encrypt.FileAdd(in,showFilePath.getText().toString()));
			progressDialog.hide();
			showFilePath.setText("");
		} else if (v == dls) {
			if (Util.isEmpty(showFilePath.getText().toString())) {
				Toast("请先选择文件");
				return;
			}
			Dialog progressDialog = ProgressDialog.show(this, null,"正在解密...");
			Toast(Encrypt.FileDel(in,showFilePath.getText().toString()));
			progressDialog.hide();
			showFilePath.setText("");
		}

	}

}
