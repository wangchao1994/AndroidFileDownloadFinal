package com.example.androidcustom2download.utils;

import java.text.DecimalFormat;

public class FormatSize {
	/**
	 * 传入对应的文件大小double,返回转换后文件大小
	 * @param fileS 返回String文件大小
	 * @return
	 */
	public static String formatFileSize(final int fileS) {
		// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS <= 0) {
			fileSizeString = "0B";
		} else if (fileS < 1024) {
			fileSizeString = df.format(fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format(fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format(fileS / 1048576) + "MB";
		} else if (fileS < 1099511627776d){
			fileSizeString = df.format(fileS / 1073741824) + "GB";
		} else {
			fileSizeString = df.format(fileS / 1099511627776d) + "TB";
		}
		return fileSizeString;
	}
}
