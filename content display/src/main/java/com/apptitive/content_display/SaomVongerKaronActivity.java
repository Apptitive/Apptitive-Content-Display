package com.apptitive.content_display;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.utilities.Config;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.HttpHelper;
import com.apptitive.content_display.utilities.Utilities;


public class SaomVongerKaronActivity extends BaseActionBar implements DetailsFragment.DetailProvider {

    private String menuId, topicTitle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            menuId = extras.getString(Constants.menu.EXTRA_MENU_ID);
            topicTitle = extras.getString(Constants.menu.EXTRA_MENU_TITLE);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarInnerBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(topicTitle, this));
        ImageLoader imageLoader = HttpHelper.getInstance(this).getImageLoader();
        imageLoader.get(Config.getImageUrl(this) + menuId + "_ab_title.png", new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    actionBar.setIcon(new BitmapDrawable(getResources(), response.getBitmap()));
                }
            }
        });
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_saom_vonger_karon);
    }

    @Override
    public Content getContent() {
        return new Content();
    }
}
