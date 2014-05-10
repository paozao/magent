package com.dogs.util;

public class Util {

	public static boolean isEmpty(String str) {
		if (null == str || str.length() == 0 || "".equals(str)) {
			return true;
		}
		return false;
	}

}
