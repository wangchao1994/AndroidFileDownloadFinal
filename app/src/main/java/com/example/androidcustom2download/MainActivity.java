package com.example.androidcustom2download;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcustom2download.entity.DownloadEntry;
import com.example.androidcustom2download.notify.DataWatcher;


public class MainActivity extends Activity implements OnClickListener{

	private final String url = "http://www.meituan.com/mobile/download/meituan/android/meituan?from=new";
	private DownloadEntry entry = new DownloadEntry(url);
	
	private Button addBtn;
	private Button cancelBtn;
	private Button pauseBtn;
	private Button resumeBtn;
	
	private TextView showText;
	
	private DataWatcher dataWatcher = new DataWatcher() {

		@Override
		public void onDataChanged(DownloadEntry data) {
			entry = data;
			Log.d("wangchao","data---------==="+data);
			showText.setText(entry.toString());
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		entry.name = "ANC.apk";
		
		showText = (TextView)findViewById(R.id.show_text);
		addBtn = (Button)findViewById(R.id.add_btn);
		cancelBtn = (Button)findViewById(R.id.cancel_btn);
		pauseBtn = (Button)findViewById(R.id.pause_btn);
		resumeBtn = (Button)findViewById(R.id.resume_btn);
		
		addBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		resumeBtn.setOnClickListener(this);
		
	}
	
	@Override
    protected void onResume() {
        super.onResume();
		DownloadManager.getInstance(this).addObserver(dataWatcher);
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        DownloadManager.getInstance(this).removeObserver(dataWatcher);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.add_btn:
			DownloadManager.getInstance(MainActivity.this).add(entry);
			break;
		case R.id.pause_btn:
			DownloadManager.getInstance(MainActivity.this).pause(entry);
			break;
		case R.id.resume_btn:
			DownloadManager.getInstance(MainActivity.this).resume(entry);
			break;
		case R.id.cancel_btn:
			DownloadManager.getInstance(MainActivity.this).cancel(entry);
			break;
		}
	}
}
