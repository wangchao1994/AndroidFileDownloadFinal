package com.example.androidcustom2download.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.example.androidcustom2download.entity.DownloadEntry;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
public class OrmDBHelper extends OrmLiteSqliteOpenHelper{
	
	public static final String DB_NAME = "zf_downloader";
	public static final int DB_VERSION = 1;
	private static OrmDBHelper mOrmDBHelper;

	public static OrmDBHelper getInstanceOrmHelper(Context context){
		if (null == mOrmDBHelper) {
			synchronized (OrmDBHelper.class) {
				if(null == mOrmDBHelper){
					mOrmDBHelper = new OrmDBHelper(context);
				}
			}
		}
		return mOrmDBHelper;
	}
	
	public OrmDBHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
		super(context, DB_NAME, factory, DB_VERSION);
	}

	public OrmDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, DownloadEntry.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//		try {
//			TableUtils.dropTable(connectionSource, DownloadEntry.class,true);
//            onCreate(database, connectionSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
	}

}