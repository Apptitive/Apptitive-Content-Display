package com.apptitive.ramadan.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rayhan on 7/13/2014.
 */
@DatabaseTable
public class Menu {

    @DatabaseField
    private int actionId;

    @DatabaseField(id = true)
    private String menuId;

    @DatabaseField
    private String title;

    @DatabaseField
    private String actionType;

    @DatabaseField
    private int patternId;

    @DatabaseField
    private int subPatternId;

    public Menu() {
    }

    public Menu(int actionId, String menuId, String title, String actionType, int patternId, int subPatternId) {
        this.actionId = actionId;
        this.menuId = menuId;
        this.title = title;
        this.actionType = actionType;
        this.patternId = patternId;
        this.subPatternId = subPatternId;
    }

    public int getActionId() {
        return actionId;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getTitle() {
        return title;
    }

    public String getActionType() {
        return actionType;
    }

    public int getPatternId() {
        return patternId;
    }

    public int getSubPatternId() {
        return subPatternId;
    }
}
