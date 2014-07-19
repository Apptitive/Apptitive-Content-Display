package com.apptitive.content_display.model;

import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.utilities.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by rayhan on 7/13/2014.
 */
@DatabaseTable
public class DbContent{
    @DatabaseField
    private int actionId;

    @DatabaseField(id = true)
    private String contentId;

    @DatabaseField
    private String menuId;

    @DatabaseField
    private String header;

    @DatabaseField
    private String shortDescription;

    @DatabaseField
    private String details;

    @DatabaseField
    private String viewType;

    @DatabaseField
    private String actionType;

    public DbContent() {
    }

    public DbContent(int actionId, String contentId, String menuId, String header, String shortDescription, String details, String viewType, String actionType) {
        this.actionId = actionId;
        this.contentId = contentId;
        this.menuId = menuId;
        this.header = header;
        this.shortDescription = shortDescription;
        this.details = details;
        this.viewType = viewType;
        this.actionType = actionType;
    }

    public int getActionId() {
        return actionId;
    }

    public String getContentId() {
        return contentId;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getHeader() {
        return header;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public String getViewType() {
        return viewType;
    }

    public String getActionType() {
        return actionType;
    }


    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public static void updateDb(DbManager dbManager, List<DbContent> dbContents) {
        for (DbContent dbContent:dbContents){
            if (dbContent.getActionType().equals(Constants.ContentType.ADD)) {
                dbManager.addDbContent(dbContent);
            } else if (dbContent.getActionType().equals(Constants.ContentType.DELETE)) {
                dbManager.deleteDbContent(dbContent);
            } else if (dbContent.getActionType().equals(Constants.ContentType.UPDATE)) {
                dbManager.updateDbContent(dbContent);
            }
        }
    }
}
