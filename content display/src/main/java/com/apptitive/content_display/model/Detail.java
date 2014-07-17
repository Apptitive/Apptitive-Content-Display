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
        if (key.equalsIgnoreCase("p"))
            return Constants.detail.VIEW_TYPE_TEXT_ONLY;
        else if (key.equalsIgnoreCase("b"))
            return Constants.detail.VIEW_TYPE_BULLET;
        else if (key.equalsIgnoreCase("h"))
            return Constants.detail.VIEW_TYPE_HEADER_ONLY;
        else if (key.equalsIgnoreCase("a"))
            return Constants.detail.VIEW_TYPE_ARABIC;
        else if (key.equalsIgnoreCase("ab"))
            return Constants.detail.VIEW_TYPE_ARABIC_BULLET_ALIGN;
        else if (key.equalsIgnoreCase("tb"))
            return Constants.detail.VIEW_TYPE_TEXT_BULLET_ALIGN;
        return 0;
    }
}
