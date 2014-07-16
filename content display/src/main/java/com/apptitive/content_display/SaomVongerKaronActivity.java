package com.apptitive.content_display;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;

import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.Utilities;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;


public class SaomVongerKaronActivity extends BaseActionBar implements DetailsFragment.DetailProvider {

    private int iconDrawableId;
    private String topicTitle;
    private XmlPullParserFactory parserFactory;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            topicTitle = extras.getString(Constants.content.EXTRA_MENU_TITLE);
            iconDrawableId = extras.getInt(Constants.content.EXTRA_ICON_ID);
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ActionBarInnerBg)));
        actionBar.setTitle(Utilities.getBanglaSpannableString(topicTitle, this));
        actionBar.setIcon(getResources().getDrawable(iconDrawableId));
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_saom_vonger_karon);
    }

    /*private Content getTopic(int topicResId) throws XmlPullParserException, IOException {
        parserFactory = XmlPullParserFactory.newInstance();
        parserFactory.setNamespaceAware(false);
        XmlPullParser xpp = parserFactory.newPullParser();
        xpp.setInput(getResources().openRawResource(topicResId), "utf-8");

        Content content = new Content();

        for (int eventType = xpp.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xpp.next()) {
            String name = xpp.getName();
            if (eventType == XmlPullParser.START_TAG) {
                if (name.equalsIgnoreCase("topic")) {
                    content.setHeader(xpp.getAttributeValue(null, "name"));
                    content.setHasFullText(false);
                }
                if (name.equalsIgnoreCase("details")) {
                    content.setDetailId(Integer.parseInt(xpp.getAttributeValue(null, "id")));
                }
            }
        }
        xpp.setInput(null);

        return content;
    }*/

    @Override
    public Content getContent() {
        /*try {
            return getTopic(topicResId);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return new Content();
    }
}
