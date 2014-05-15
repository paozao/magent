package com.dogs.fileactivity;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dogs.R;
import com.dogs.common.Constant;

public class FileListAdapter extends BaseAdapter {
	private List<Map<String, String>> list;
	@SuppressWarnings("unused")
	private Context context;
	private LayoutInflater iflater;

	public FileListAdapter(List<Map<String, String>> list, Context context) {
		this.list = list;
		this.context = context;
		iflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int count, View view, ViewGroup parentView) {
		FileHolder holder = null;
		if (view == null) {
			view = iflater.inflate(R.layout.activity_file_detail, null);
			holder = new FileHolder();
			holder.imgView = (TextView) view.findViewById(R.id.fileImage);
			holder.msgView = (TextView) view.findViewById(R.id.fileText);
			view.setTag(holder);
		} else {
			holder = (FileHolder) view.getTag();
		}
		// 设置数据
		Map<String, String> map = list.get(count);
		// holder.imgView.setText(map.get(Constant.KEY_TYPE));
		// 文件夹
		if (Constant.TYPE_FLODER.equals(map.get(Constant.KEY_TYPE))) {
			holder.imgView.setBackgroundResource(R.drawable.ic_menu_archive);
		} else {
			// 文件
			holder.imgView
					.setBackgroundResource(R.drawable.ic_menu_copy_holo_light);
		}

		holder.msgView.setText(map.get(Constant.KEY_NAME));
		return view;
	}

}
