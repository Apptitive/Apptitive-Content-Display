package com.apptitive.content_display;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apptitive.content_display.adapter.ContentListAdapter;
import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.model.DbContent;
import com.apptitive.content_display.views.ParallaxListView;
import com.google.analytics.tracking.android.EasyTracker;

import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.paralloid.Parallaxor;

public class ContentFragment extends ListFragment implements ContentListAdapter.ContentCallback {

    private XmlPullParserFactory parserFactory;
    private ContentProvider contentProvider;
    private ContentListAdapter contentListAdapter;

    public ContentFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(getActivity()).activityStart(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(getActivity()).activityStop(getActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            contentProvider = (ContentProvider) activity;
        } catch (ClassCastException cce) {
            Log.e(this.getTag(), "Parent activity must implement ContentProvider");
        }
        DbManager.init(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String menu_id = contentProvider.getMenuId();
        contentListAdapter = new ContentListAdapter(getActivity(), R.layout.list_item_content, dbResultToContent(DbManager.getInstance().getDbContentForMenu(menu_id)) , this);
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

    private void parallaxListViewBackground(int drawable) {
        final ListView listView = getListView();
        if (listView instanceof Parallaxor) {
            ((ParallaxListView) listView).parallaxViewBackgroundBy(listView, getResources().getDrawable(drawable), .15f);
        }
    }

    private boolean isListScrolling(ListView listView) {
        int last_visible_pos = listView.getLastVisiblePosition();
        View lastChild = listView.getChildAt(last_visible_pos);
        if (lastChild != null)
            if (last_visible_pos == listView.getCount() - 1 && lastChild.getBottom() <= listView.getHeight())
                return false;
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setListAdapter(contentListAdapter);
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView listView = getListView();
        listView.post(new Runnable() {
            @Override
            public void run() {
                if (isListScrolling(listView)) {
                    int orientation = getResources().getConfiguration().orientation;
                    if (orientation == Configuration.ORIENTATION_PORTRAIT)
                        parallaxListViewBackground(R.drawable.bg_home);
                    else
                        parallaxListViewBackground(R.drawable.bg_home_land);
                }
            }
        });
    }

    @Override
    public void onContentClick(Content content, int position) {
        /*if (!content.hasFullText()) {
            if(content.getDetailUri() == null && content.getDetailId() > 0) {
                Intent i = new Intent(parentActivity, DetailsActivity.class);
                i.putParcelableArrayListExtra(Constants.content.EXTRA_PARCELABLE_LIST, contents);
                i.putExtra(Constants.content.EXTRA_VIEWING_NOW, position);
                i.putExtra(Constants.content.EXTRA_ICON_ID, parentActivity.getIconDrawableId());
                i.putExtra(Constants.content.EXTRA_DATA_FILE, topicFileResId);
                startActivity(i);
            }
            else if(content.getDetailUri() != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(content.getDetailUri());
                startActivity(intent);
            }
        }*/
    }

    public interface ContentProvider {
        String getMenuId();
    }
}
