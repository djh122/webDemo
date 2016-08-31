package com.djh.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @djh
 */
public class IoUtil {

	public static byte[] readInputStream(InputStream inputStream,
			String inputStreamName) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[16 * 1024];
		try {
			int bytesRead = inputStream.read(buffer);
			while (bytesRead != -1) {
				outputStream.write(buffer, 0, bytesRead);
				bytesRead = inputStream.read(buffer);
			}
		} catch (Exception e) {
			throw new RuntimeException("不能读取inputStream流：" + inputStreamName, e);
		}
		return outputStream.toByteArray();
	}

	public static String readFileAsString(String filePath) {
		byte[] buffer = new byte[(int) getFile(filePath,false).length()];
		BufferedInputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(
					getFile(filePath,false)));
			inputStream.read(buffer);
		} catch (Exception e) {
			throw new RuntimeException("不能读取文件： " + filePath + ": "
					+ e.getMessage());
		} finally {
			IoUtil.closeSilently(inputStream);
		}
		return new String(buffer);
	}

	/**
	 * @param filePath  全路径名称
	 * @param canCreate
	 * @return
	 */
	public static File getFile(String filePath,boolean canCreate) {
		//IoUtil.class.getClassLoader().getResource(filePath);
		try {
			//URL url = new File(filePath).toURL();
			File file = null;
			if(canCreate){//读取的时候不能创建文件，写入的时候如果文件不存在则创建
				int sp = filePath.lastIndexOf(File.separator);
				String parentPath = filePath.substring(0,sp);
				String fileName = filePath.substring(sp);
				File dir = new File(parentPath);
				dir.mkdirs();
				file = new File(dir,fileName);
				file.createNewFile();
			}else {
				file = new File(filePath);
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("不能读取文件： " + filePath + ": "
					+ e.getMessage());
		}
	}

	public static void writeStringToFile(String content, String filePath) {
		BufferedOutputStream outputStream = null;
		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(
					getFile(filePath,true)));
			outputStream.write(content.getBytes());
			outputStream.flush();
		} catch (Exception e) {
			throw new RuntimeException("不能写入文件： " + filePath, e);
		} finally {
			IoUtil.closeSilently(outputStream);
		}
	}

	public static void closeSilently(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException ignore) {
			// Exception is silently ignored
		}
	}

	public static void closeSilently(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException ignore) {
			// Exception is silently ignored
		}
	}
	public static void main(String[] args) {
		writeStringToFile("abc22", "f:\\ss\\hh.txt");
		
		System.out.println(readFileAsString("f:\\ss\\hh.txt"));
	}
}