package com.apptitive.content_display;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.utilities.Config;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.HttpHelper;
import com.apptitive.content_display.utilities.Utilities;
import com.apptitive.content_display.views.BanglaTextView;
import com.dibosh.experiments.android.support.customfonthelper.AndroidCustomFontSupport;

import java.util.ArrayList;


public class DetailsActivity extends BaseActionBar implements DetailsFragment.DetailProvider {

    private int iconDrawableId, topicPosition;
    private Content contentInView;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private ArrayList<Content> contents;
    private ArrayAdapter<Content> drawerListAdapter;
    private ListView listViewDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        contents = getIntent().getParcelableArrayListExtra(Constants.content.EXTRA_PARCELABLE_LIST);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            topicPosition = extras.getInt(Constants.content.EXTRA_VIEWING_NOW);
            iconDrawableId = extras.getInt(Constants.content.EXTRA_ICON_ID);
        }
        contentInView = contents.get(topicPosition);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarInnerBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(contentInView.getHeader(), this));
        ImageLoader imageLoader = HttpHelper.getInstance(this).getImageLoader();
        imageLoader.get(Config.getImageUrl(this)+"1_ab_title.png", new ImageLoader.ImageListener() {

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

        setContentView(R.layout.activity_details);

        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);
        listViewDrawer = (ListView) findViewById(R.id.listView_drawer);

        drawerListAdapter = new ArrayAdapter<Content>(this, R.layout.list_item_nav_drawer,
                contents) {
            @Override
            public int getViewTypeCount() {
                if (getCount() != 0)
                    return getCount();
                return 1;
            }

            @Override
            public int getItemViewType(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                BanglaTextView btv;
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_nav_drawer, parent, false);
                }
                btv = (BanglaTextView) convertView.findViewById(R.id.btv_nav);
                btv.setBanglaText(getItem(position).getHeader());
                if (position == contents.indexOf(contentInView))
                    convertView.setBackgroundColor(getResources().getColor(R.color.NavDrawerListItemSelected));
                return convertView;
            }
        };
        listViewDrawer.setAdapter(drawerListAdapter);

        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                contentInView = contents.get(position);
                /*if (TextUtils.isEmpty(contentInView.getDetailUri().toString())) {
                    actionBar.setTitle(Utilities.getBanglaSpannableString(contentInView.getHeader(), DetailsActivity.this));
                    DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details);
                    detailsFragment.changeTopic(contentInView);
                    listViewDrawer.setAdapter(drawerListAdapter);
                    drawerLayout.closeDrawer(listViewDrawer);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(contentInView.getDetailUri());
                    startActivity(intent);
                }*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_drawer:
                if (drawerLayout.isDrawerOpen(listViewDrawer))
                    drawerLayout.closeDrawer(listViewDrawer);
                else drawerLayout.openDrawer(listViewDrawer);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(listViewDrawer))
            drawerLayout.closeDrawer(listViewDrawer);
        else
            super.onBackPressed();
    }

    @Override
    public void setTitle(CharSequence title) {
        actionBar.setTitle(AndroidCustomFontSupport.getCorrectedBengaliFormat(title.toString(), Utilities.getFont(this), -1));
    }

    public Content getContent() {
        return contentInView;
    }
}
