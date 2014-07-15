package com.apptitive.content_display.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rayhan on 7/13/2014.
 */
@DatabaseTable
public class Topics implements Parcelable{
    @DatabaseField
    private int actionId;

    @DatabaseField(id = true)
    private String topicId;

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

    @DatabaseField
    private String menuId;

    public Topics() {
    }

    public Topics(int actionId, String topicId, String header, String shortDescription, String details, String viewType, String actionType, String menuId) {
        this.actionId = actionId;
        this.topicId = topicId;
        this.header = header;
        this.shortDescription = shortDescription;
        this.details = details;
        this.viewType = viewType;
        this.actionType = actionType;
        this.menuId = menuId;
    }

    public int getActionId() {
        return actionId;
    }

    public String getTopicId() {
        return topicId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(header);
    }

    public String getMenuId() {
        return menuId;
    }
}
