package com.apptitive.content_display;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.apptitive.content_display.adapter.TopicListAdapter;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.views.ParallaxListView;
import com.google.analytics.tracking.android.EasyTracker;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.ArrayList;

import uk.co.chrisjenx.paralloid.Parallaxor;

public class TopicsFragment extends ListFragment implements TopicListAdapter.TopicClickListener {

    private XmlPullParserFactory parserFactory;
    private TopicsActivity parentActivity;
    private TopicListAdapter topicListAdapter;
    private ArrayList<Content> contents;

    public TopicsFragment() {

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
        parentActivity = (TopicsActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contents = new ArrayList<Content>();
        topicListAdapter = new TopicListAdapter(parentActivity, R.layout.list_item_topics, contents, this);
        /*try {
            populateList(topicFileResId);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*private void populateList(int topicResId) throws XmlPullParserException, IOException {
        parserFactory = XmlPullParserFactory.newInstance();
        parserFactory.setNamespaceAware(false);
        XmlPullParser xpp = parserFactory.newPullParser();
        xpp.setInput(getResources().openRawResource(topicResId), "utf-8");

        Content content = null;

        for (int eventType = xpp.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xpp.nextToken()) {
            String name = xpp.getName();
            if (eventType == XmlPullParser.START_TAG) {
                if (name.equalsIgnoreCase("subtopic")) {
                    content = new Content();
                    content.setHeader(xpp.getAttributeValue(null, "name"));
                    content.setHasFullText(Boolean.parseBoolean(xpp.getAttributeValue(null, "show_all")));
                }
                if (name.equalsIgnoreCase("details")) {
                    content.setDetailId(Integer.parseInt(xpp.getAttributeValue(null, "id")));
                }
                if (name.equalsIgnoreCase("brief")) {
                    content.setShortDescription(xpp.getAttributeValue(null, "text"));
                }
                if(name.equalsIgnoreCase("url")) {
                    content.setDetailUri(Uri.parse(xpp.getAttributeValue(null, "link")));
                }
            }
            if (eventType == XmlPullParser.END_TAG) {
                if (name.equalsIgnoreCase("subtopic")) {
                    contents.add(content);
                }
            }
        }
        xpp.setInput(null);
    }*/

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
        setListAdapter(topicListAdapter);
        return inflater.inflate(R.layout.fragment_topics, container, false);
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
    public void onTopicClick(Content content, int position) {
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
}
