package com.dogs.util;

import java.io.File;
import java.io.InputStream;

import com.dogs.encrypt.FileImpl;
import com.dogs.encrypt.IFile;

public class Encrypt {

	private static IFile files;

	static {
		if (null == files) {
			files = new FileImpl();
		}
	}

	public static String FileAdd(String path) {
		return files.fileAds(new File(path));
	}

	public static String FileDel(String path) {
		return files.fileDls(new File(path));
	}

	public static String FileAdd(InputStream in,String fileName) {
		return files.fileAds(in,fileName);
	}

	public static String FileDel(InputStream in,String fileName) {
		return files.fileDls(in,fileName);
	}
}
