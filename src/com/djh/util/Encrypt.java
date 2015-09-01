package com.djh.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Encrypt {

	/**
	 * 将字符串 经md5加密转换 为16进制字小写符串
	 * @param data 原字符串
	 * @return
	 */
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}
}
