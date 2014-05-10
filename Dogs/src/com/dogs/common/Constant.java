package com.dogs.common;

import android.os.Environment;

public class Constant {
	public final static String SDPATH = Environment
			.getExternalStorageDirectory().getPath()+"/";
	public final static String PHONEPATH = "加密狗/";
	public final static String FLODERADD = "加密/";
	public final static String FLODERDLS = "解密/";
	public final static int key = 12306;
	public final static String SUCCESS = "操作成功";
	public final static String FAIL = "操作失败";

}
