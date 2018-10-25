package com.example.androidcustom2download.entity;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import com.example.androidcustom2download.DownloadConfig;
import com.example.androidcustom2download.utils.FormatSize;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "downloadentry")
public class DownloadEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id = true)
	public String url;

	@DatabaseField
	public String name;

	@DatabaseField
	public int currentLength;

	@DatabaseField
	public int totalLength;

	@DatabaseField
	public DownloadStatus status = DownloadStatus.idle;

	@DatabaseField
	public boolean isSupportRange;

	@DatabaseField(dataType = DataType.SERIALIZABLE)
	public HashMap<Integer, Integer> ranges;

	@DatabaseField
	public int percent;

	public enum DownloadStatus {
		idle, waiting, connecting, downloading, pause, resume, cancel, done, error
	}

	public DownloadEntry(String url) {
		this.url = url;
	}

	public DownloadEntry() {}
	
	@Override
	public String toString() {
		return name + " is " + status.name() + " " + FormatSize.formatFileSize(currentLength) + "/" + FormatSize.formatFileSize(totalLength) + " " + percent +"%";
	}

	@Override
	public boolean equals(Object o) {
		return o.hashCode() == this.hashCode();
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}

	public void reset() {
		currentLength = 0;
		percent = 0;
		ranges = null;
		String path = DownloadConfig.DOWNLOAD_PATH + name;
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
	}

}
