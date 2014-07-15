package com.apptitive.ramadan.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rayhan on 7/13/2014.
 */
@DatabaseTable
public class Topics {
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

    public Topics() {
    }

    public Topics(int actionId, String topicId, String header, String shortDescription, String details, String viewType, String actionType) {
        this.actionId = actionId;
        this.topicId = topicId;
        this.header = header;
        this.shortDescription = shortDescription;
        this.details = details;
        this.viewType = viewType;
        this.actionType = actionType;
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
}
