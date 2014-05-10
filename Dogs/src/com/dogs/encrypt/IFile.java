package com.dogs.encrypt;

import java.io.File;
import java.io.InputStream;

public interface IFile {

	/**
	 * 单个文件加密
	 * 
	 * @param file
	 * @return
	 */
	public String fileAds(File file);

	/**
	 * 单个文件加密
	 * 
	 * @param file
	 * @return
	 */
	public String fileAds(InputStream in,String fileName);

	/**
	 * 单个文件解密
	 * 
	 * @param file
	 * @return
	 */
	public String fileDls(File file);

	/**
	 * 单个文件解密
	 * 
	 * @param file
	 * @return
	 */
	public String fileDls(InputStream in,String fileName);

	/**
	 * 多个文件加密
	 * 
	 * @param path
	 * @return
	 */
	public String filesAds(String path);

	/**
	 * 多个文件解密
	 * 
	 * @param path
	 * @return
	 */
	public String filesDls(String path);
}
