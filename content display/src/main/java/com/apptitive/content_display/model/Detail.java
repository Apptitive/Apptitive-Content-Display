package com.apptitive.content_display.model;

import com.apptitive.content_display.utilities.Constants;

/**
 * Created by Iftekhar on 6/5/2014.
 */
public class Detail {
    private String text;
    private int tag;

    public Detail() {
    }

    public Detail(String text, int detailViewType) {
        this.text = text;
        tag = detailViewType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void populateFrom(JsonDetail jsonDetail) {
        setTag(findTagFor(jsonDetail.key));
        setText(jsonDetail.value);
    }

    private int findTagFor(String key) {
        if (key.equalsIgnoreCase(Constants.detail.tag.PARAGRAPH))
            return Constants.detail.VIEW_TYPE_P;
        else if (key.equalsIgnoreCase(Constants.detail.tag.BOLD))
            return Constants.detail.VIEW_TYPE_B;
        else if (key.equalsIgnoreCase(Constants.detail.tag.UNORDERED_LIST_ITEM))
            return Constants.detail.VIEW_TYPE_UL;
        else if (key.equalsIgnoreCase(Constants.detail.tag.HEADER_1))
            return Constants.detail.VIEW_TYPE_H1;
        return 0;
    }
}
