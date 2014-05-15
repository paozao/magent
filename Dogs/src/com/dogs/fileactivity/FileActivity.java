package com.dogs.fileactivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dogs.MainActivity;
import com.dogs.R;
import com.dogs.base.BaseActivity;
import com.dogs.common.Constant;

public class FileActivity extends BaseActivity implements OnItemClickListener {

	private ListView fileListView;
	private LinearLayout floderList;
	private List<Map<String, String>> fileListData;
	private String newPath = "";
	@SuppressWarnings("unused")
	private String oldPath = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		// 初始化控件
		init();
		// 加载文件夹列表头
		loadFloderView(Constant.SDPATH);
		// 加载列表
		loadFile(Constant.SDPATH);
	}

	/**
	 * 加载列表
	 * 
	 * @param path
	 */
	private void loadFile(String path) {
		newPath = path + "/";
		File file = new File(path);
		File files[] = file.listFiles();
		fileListData = listMap(files);
		FileListAdapter adapter = new FileListAdapter(fileListData, this);
		fileListView.setAdapter(adapter);
		fileListView.setOnItemClickListener(this);
	}

	/**
	 * 处理文件列表
	 * 
	 * @param fileList
	 */
	private List<Map<String, String>> listMap(File[] files) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listFloder = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listFile = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if (null != files) {
			for (File file : files) {
				map = new HashMap<String, String>();
				map.put(Constant.KEY_NAME, file.getName());
				if (file.isFile()) {
					// 文件
					map.put(Constant.KEY_TYPE, Constant.TYPE_FILE);
					listFile.add(map);
				} else {
					// 文件夹
					map.put(Constant.KEY_TYPE, Constant.TYPE_FLODER);
					listFloder.add(map);
				}
			}
		}

		list.addAll(listFloder);
		list.addAll(listFile);
		return list;
	}

	private void loadFloderView(String text) {
		TextView textview = null;
		textview = new TextView(this);
		textview.setText(text + " ");
		floderList.addView(textview);
	}

	private void init() {
		fileListView = (ListView) findViewById(R.id.fileListView);
		floderList = (LinearLayout) findViewById(R.id.floderList);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int count,
			long arg3) {
		Map<String, String> map = fileListData.get(count);
		String name = map.get(Constant.KEY_NAME);
		String type = map.get(Constant.KEY_TYPE);
		loadFloderView("/" + name);
		if (Constant.TYPE_FLODER.equals(type)) {
			oldPath = newPath;
			loadFile(newPath + name);
		} else {
			oldPath = newPath;
			newPath += name;
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("path", newPath);
			startActivity(intent);
			finish();
		}

	}
}
