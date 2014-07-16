package com.apptitive.content_display;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;

import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.Utilities;


public class ContentActivity extends BaseActionBar implements ContentFragment.ContentProvider {

    private int iconDrawableId;
    private String menuId, menuTitle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            menuId = extras.getString(Constants.content.EXTRA_MENU_ID);
            menuTitle = extras.getString(Constants.content.EXTRA_MENU_TITLE);
            iconDrawableId = extras.getInt(Constants.content.EXTRA_ICON_ID);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarHomeBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(menuTitle, this));
        actionBar.setIcon(getResources().getDrawable(iconDrawableId));
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_content);
    }

    @Override
    public String getMenuId() {
        return menuId;
    }
}
