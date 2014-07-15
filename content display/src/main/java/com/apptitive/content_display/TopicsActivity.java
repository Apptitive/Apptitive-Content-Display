package com.apptitive.content_display;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;

import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.Utilities;


public class TopicsActivity extends BaseActionBar {

    private int iconDrawableId, topicResId;
    private String topicTitle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            topicTitle = extras.getString(Constants.topic.EXTRA_TITLE);
            iconDrawableId = extras.getInt(Constants.topic.EXTRA_ICON_ID);
            topicResId = extras.getInt(Constants.topic.EXTRA_DATA_FILE);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarHomeBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(topicTitle, this));
        actionBar.setIcon(getResources().getDrawable(iconDrawableId));
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_topics);
    }

    public int getTopicResId() {
        return topicResId;
    }

    public int getIconDrawableId() {
        return iconDrawableId;
    }
}
