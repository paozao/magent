package com.dogs.encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dogs.common.Constant;

public class FileImpl implements IFile {

	@Override
	public String fileAds(File file) {
		return "";
	}

	@Override
	public String fileDls(File file) {
		return "";
	}

	@Override
	public String filesAds(String path) {
		return null;
	}

	@Override
	public String filesDls(String path) {
		return null;
	}

	@Override
	public String fileAds(InputStream in, String fileName) {
		try {
			// 创建文件夹
			createFloder(true);
			// 创建文件
			String filePath = Constant.SDPATH +"/"+ Constant.PHONEPATH
					+ Constant.FLODERADD + fileName;
			OutputStream out = createFile(filePath);
			disposeFile(in, out, true);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.FAIL;
		}
		return Constant.SUCCESS;
	}

	@Override
	public String fileDls(InputStream in, String fileName) {
		try {
			// 创建文件夹
			createFloder(false);
			String filePath = Constant.SDPATH +"/" + Constant.PHONEPATH
					+ Constant.FLODERDLS + fileName;
			OutputStream out = createFile(filePath);
			disposeFile(in, out, false);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.FAIL;
		}
		return Constant.SUCCESS;
	}

	/**
	 * 加密解密文件
	 * 
	 * @param in
	 * @param out
	 * @param add
	 * @throws Exception
	 */
	private void disposeFile(InputStream in, OutputStream out, boolean add)
			throws Exception {
		// 处理文件加密解密
		byte[] b = new byte[1204];
		try {
			int len = in.read(b);
			while (len > -1) {
				if (add) {
					b = byteAdd(b);
				} else {
					b = byteDel(b);
				}
				out.write(b, 0, len);
				len = in.read(b);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private OutputStream createFile(String filePath) throws Exception {
		OutputStream out = null;
		File file = null;
		file = new File(filePath);
		file.createNewFile();
		out = new FileOutputStream(file);
		return out;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param add
	 */
	private void createFloder(boolean add) {
		String path = Constant.SDPATH+"/" + Constant.PHONEPATH + Constant.FLODERADD;
		if (!add) {
			path = Constant.SDPATH+"/" + Constant.PHONEPATH + Constant.FLODERDLS;
		}
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 加密
	 * 
	 * @param b
	 * @return
	 * @throws Exception
	 */
	private byte[] byteAdd(byte[] b) throws Exception {
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) ((int) b[i] + Constant.key);
		}
		return b;
	}

	/**
	 * 解密
	 * 
	 * @param b
	 * @return
	 * @throws Exception
	 */
	private byte[] byteDel(byte[] b) throws Exception {
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) ((int) b[i] - Constant.key);
		}
		return b;
	}
}
