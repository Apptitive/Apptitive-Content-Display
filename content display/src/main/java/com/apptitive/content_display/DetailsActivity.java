package com.apptitive.content_display;

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
import android.widget.TextView;

import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.model.DbContent;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.Utilities;
import com.dibosh.experiments.android.support.customfonthelper.AndroidCustomFontSupport;

import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends BaseActionBar implements DetailsFragment.DetailProvider {

    private int iconDrawableId;
    private Content contentInView;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private List<Content> contents;
    private ArrayAdapter<Content> drawerListAdapter;
    private ListView listViewDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbManager.init(this);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            contentInView = extras.getParcelable(Constants.content.EXTRA_CONTENT);
            iconDrawableId = extras.getInt(Constants.menu.EXTRA_ICON_ID);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarInnerBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(contentInView.getHeader(), this));
        actionBar.setIcon(getResources().getDrawable(iconDrawableId));
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_details);

        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);
        listViewDrawer = (ListView) findViewById(R.id.listView_drawer);

        contents = dbResultToContent(DbManager.getInstance().getDbContentForMenu(contentInView.getContentId()));

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
                TextView tvHeader;
                Content content = getItem(position);
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_nav_drawer, parent, false);
                }
                tvHeader = (TextView) convertView.findViewById(R.id.btv_nav);
                tvHeader.setText(getItem(position).getHeader());
                if (content.getContentId().equals(contentInView.getContentId()))
                    convertView.setBackgroundColor(getResources().getColor(R.color.NavDrawerListItemSelected));
                return convertView;
            }
        };
        listViewDrawer.setAdapter(drawerListAdapter);

        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                contentInView = contents.get(position);
                actionBar.setTitle(Utilities.getBanglaSpannableString(contentInView.getHeader(), DetailsActivity.this));
                DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details);
                detailsFragment.changeTopic(contentInView);
                listViewDrawer.setAdapter(drawerListAdapter);
                drawerLayout.closeDrawer(listViewDrawer);
            }
        });
    }

    private List<Content> dbResultToContent(List<DbContent> dbContents) {
        List<Content> contents = new ArrayList<Content>();
        for (DbContent dbContent : dbContents) {
            Content content = new Content();
            content.populateFrom(dbContent);
            contents.add(content);
        }
        return contents;
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
