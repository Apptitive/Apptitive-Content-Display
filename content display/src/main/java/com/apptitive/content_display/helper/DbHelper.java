package com.apptitive.content_display.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.apptitive.content_display.R;
import com.apptitive.content_display.model.Menu;
import com.apptitive.content_display.model.Region;
import com.apptitive.content_display.model.TimeTable;
import com.apptitive.content_display.model.DbContent;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 5/27/2014.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Ramadan.sqlite";
    private static final int DATABASE_VERSION = 3;

    private Dao<Region, String> regionDao = null;
    private Dao<TimeTable, String> timeTableDao = null;
    private Dao<Menu, String> menuDao = null;
    private Dao<DbContent, String> topicsDao = null;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Region.class);
            TableUtils.createTable(connectionSource, TimeTable.class);
            TableUtils.createTable(connectionSource, Menu.class);
            TableUtils.createTable(connectionSource, DbContent.class);
        } catch (SQLException e) {
            Log.e(DbHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        List<String> allSql = new ArrayList<String>();
        try {
            TableUtils.clearTable(connectionSource, Region.class);
            TableUtils.clearTable(connectionSource, TimeTable.class);
            TableUtils.clearTable(connectionSource, Menu.class);
            TableUtils.clearTable(connectionSource, DbContent.class);
            CSVToDbHelper.readCSVAndInserIntoDb(context, R.raw.region, DbTableName.Region);
            CSVToDbHelper.readCSVAndInserIntoDb(context, R.raw.timetable, DbTableName.TimeTable);
            for (String sql : allSql) {
                sqLiteDatabase.execSQL(sql);
            }
        } catch (Exception e) {
            Log.e(DbHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Region, String> getRegionDao() {
        if (null == regionDao) {
            try {
                regionDao = getDao(Region.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return regionDao;
    }

    public Dao<TimeTable, String> getTimeTableDao() {
        if (null == timeTableDao) {
            try {
                timeTableDao = getDao(TimeTable.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return timeTableDao;
    }

    public Dao<Menu, String> getMenuDao() {
        if (null == menuDao) {
            try {
                menuDao = getDao(Menu.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return menuDao;
    }

    public Dao<DbContent, String> getTopicsDao() {
        if (null == topicsDao) {
            try {
                topicsDao = getDao(DbContent.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return topicsDao;
    }

}
