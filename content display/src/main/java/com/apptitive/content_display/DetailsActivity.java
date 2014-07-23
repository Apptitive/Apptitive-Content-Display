package com.apptitive.content_display;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.WindowCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.model.DbContent;
import com.apptitive.content_display.model.DetailType;
import com.apptitive.content_display.utilities.Config;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.HttpHelper;
import com.apptitive.content_display.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends BaseActionBar implements DetailsFragment.DetailProvider {

    private String menuId;
    private Content content;
    private List<Content> contents;
    private ArrayAdapter<Content> drawerListAdapter;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private FrameLayout fragmentContainer;
    private ListView listViewDrawer;
    private WebView webViewDetails;
    private DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbManager.init(this);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            menuId = extras.getString(Constants.menu.EXTRA_MENU_ID);
            content = extras.getParcelable(Constants.content.EXTRA_CONTENT);
        }

        if (savedInstanceState != null) {
            content = savedInstanceState.getParcelable(Constants.content.EXTRA_CONTENT);
            menuId = savedInstanceState.getString(Constants.menu.EXTRA_MENU_ID);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarInnerBg)));
        actionBar.setTitle(content.getHeader());
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

        setContentView(R.layout.activity_details);

        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);
        listViewDrawer = (ListView) findViewById(R.id.listView_drawer);
        webViewDetails = (WebView) findViewById(R.id.webView_details);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

        if (content.getDetailType().equals(DetailType.HTML)) {
            webViewDetails.setVisibility(View.VISIBLE);
            webViewDetails.loadData(content.getDetails(), "text/html", "utf-8");
        } else if (content.getDetailType().equals(DetailType.NATIVE)) {
            showHideFragment(true);
        }

        contents = dbResultToContent(DbManager.getInstance().getDbContentForMenu(menuId));

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
                if (content.getContentId().equals(DetailsActivity.this.content.getContentId()))
                    convertView.setBackgroundColor(getResources().getColor(R.color.NavDrawerListItemSelected));
                return convertView;
            }
        };
        listViewDrawer.setAdapter(drawerListAdapter);

        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                content = contents.get(position);
                actionBar.setTitle(Utilities.getBanglaSpannableString(content.getHeader(), DetailsActivity.this));
                if (content.getDetailType().equals(DetailType.HTML)) {
                    showHideFragment(false);
                    webViewDetails.setVisibility(View.VISIBLE);
                    webViewDetails.loadData(content.getDetails(), "text/html", "utf-8");
                } else if (content.getDetailType().equals(DetailType.NATIVE)) {
                    showHideFragment(true);
                    webViewDetails.setVisibility(View.GONE);
                }
                listViewDrawer.setAdapter(drawerListAdapter);
                drawerLayout.closeDrawer(listViewDrawer);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.menu.EXTRA_MENU_ID, menuId);
        ;
        outState.putParcelable(Constants.content.EXTRA_CONTENT, content);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        content = savedInstanceState.getParcelable(Constants.content.EXTRA_CONTENT);
        menuId = savedInstanceState.getString(Constants.menu.EXTRA_MENU_ID);
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

    private void showHideFragment(boolean show) {
        if (detailsFragment == null) {
            detailsFragment = new DetailsFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (show) {
            if (detailsFragment.isAdded()) {
                detailsFragment.switchContent();
            } else {
                fragmentContainer.setVisibility(View.VISIBLE);
                ft.add(fragmentContainer.getId(), detailsFragment);
            }
        } else {
            ft.remove(detailsFragment);
            fragmentContainer.setVisibility(View.GONE);
        }
        ft.commit();
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

    public Content getContent() {
        return content;
    }
}
