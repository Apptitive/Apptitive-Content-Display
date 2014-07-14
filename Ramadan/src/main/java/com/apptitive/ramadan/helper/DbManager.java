package com.apptitive.ramadan.helper;

import android.content.Context;

import com.apptitive.ramadan.model.Menu;
import com.apptitive.ramadan.model.Region;
import com.apptitive.ramadan.model.TimeTable;
import com.apptitive.ramadan.model.Topics;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 5/27/2014.
 */
public class DbManager {

    static private DbManager instance;

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new DbManager(ctx);
        }
    }

    static public DbManager getInstance() {
        return instance;
    }

    private DbHelper helper;

    private DbManager(Context ctx) {
        helper = new DbHelper(ctx);
    }

    private DbHelper getHelper() {
        return helper;
    }

    public List<Region> getAllRegions() {
        List<Region> regionList = null;
        try {
            regionList = getHelper().getRegionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public String[] getAllRegionNames() {
        ArrayList<String> regionNames = new ArrayList<String>();
        List<Region> regions = getAllRegions();
        for (Region region : regions) {
            regionNames.add(region.getName());
        }
        return regionNames.toArray(new String[regionNames.size()]);
    }

    public Region getRegionWithName(String name) {
        List<Region> regions = getAllRegions();
        for (Region region : regions) {
            if (region.getName().equals(name))
                return region;
        }
        return new Region("1", "Dhaka", "", true,0, 0);
    }

    public Region getDonorWithId(String regionId) {
        Region region = null;
        try {
            region = getHelper().getRegionDao().queryForId(regionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    public void addRegion(Region region) {
        try {
            getHelper().getRegionDao().create(region);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRegion(Region region) {
        try {
            getHelper().getRegionDao().update(region);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<TimeTable> getAllTimeTables() {
        List<TimeTable> timeTableList = null;
        try {
            timeTableList = getHelper().getTimeTableDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTableList;
    }

    public TimeTable getTimeTableWithId(String timeTableId) {
        TimeTable timeTable = null;
        try {
            timeTable = getHelper().getTimeTableDao().queryForId(timeTableId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTable;
    }

    public void addTimeTable(TimeTable timeTable) {
        try {
            getHelper().getTimeTableDao().create(timeTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTimeTable(TimeTable timeTable) {
        try {
            getHelper().getTimeTableDao().update(timeTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String[] getAllBanglaRegionNames() {
        ArrayList<String> regionNames = new ArrayList<String>();
        List<Region> regions = getAllRegions();
        for (Region region : regions) {
            regionNames.add(region.getNameInBangla());
        }
        return regionNames.toArray(new String[regionNames.size()]);
    }


    public List<Topics> getAllTopics() {
        List<Topics> topicsList = null;
        try {
            topicsList = getHelper().getTopicsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicsList;
    }


    public void addTopics(Topics topics) {
        try {
            getHelper().getTopicsDao().create(topics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTopics(Topics topics){
        try {
            getHelper().getTopicsDao().delete(topics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTopics(Topics topics){
        try {
            getHelper().getTopicsDao().update(topics);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Menu> getAllMenus() {
        List<Menu> menuList = null;
        try {
            menuList = getHelper().getMenuDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }


    public void addMenu(Menu menu) {
        try {
            getHelper().getMenuDao().create(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(Menu menu){
        try {
            getHelper().getMenuDao().delete(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenu(Menu menu){
        try {
            getHelper().getMenuDao().update(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


