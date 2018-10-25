package com.example.androidcustom2download.db;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;

import com.example.androidcustom2download.entity.DownloadEntry;
import com.example.androidcustom2download.utils.LogUtils;
import com.j256.ormlite.dao.Dao;
public class DBController {
    private static DBController mInstance;
    private OrmDBHelper mDBhelper;

    private DBController(Context context) {
        //mDBhelper = new OrmDBHelper(context);
    	mDBhelper = OrmDBHelper.getInstanceOrmHelper(context);
        mDBhelper.getWritableDatabase();
    }

    public static DBController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBController(context);
        }
        return mInstance;
    }

    public synchronized void newOrUpdate(DownloadEntry entry) {
        try {
            Dao<DownloadEntry, String> dao = mDBhelper.getDao(DownloadEntry.class);
            dao.createOrUpdate(entry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized ArrayList<DownloadEntry> queryAll() {
        Dao<DownloadEntry, String> dao;
        try {
            dao = mDBhelper.getDao(DownloadEntry.class);
            return (ArrayList<DownloadEntry>) dao.query(dao.queryBuilder().prepare());
        } catch (SQLException e) {
            LogUtils.e(e.getMessage());
            return null;
        }
    }

    public synchronized DownloadEntry queryByUrl(String url) {
        try {
            Dao<DownloadEntry, String> dao = mDBhelper.getDao(DownloadEntry.class);
            return dao.queryForId(url);
        } catch (SQLException e) {
            LogUtils.e(e.getMessage());
            return null;
        }
    }
    
    public synchronized void deleteByUrl(String url){
    	 try {
			Dao<DownloadEntry, String> dao = mDBhelper.getDao(DownloadEntry.class);
			dao.deleteById(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
