package com.apptitive.content_display;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.Menu;

import java.util.List;

public class StartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DbManager.init(this);

        List<Menu> menuList = DbManager.getInstance().getAllMenus();

        ViewStub stubActivityStart = (ViewStub)findViewById(R.id.view_stub_activity_start);
        stubActivityStart.setLayoutResource(R.layout.menu_pattern_1);
        View activityStart = stubActivityStart.inflate();

        ViewStub stubLeftTop = (ViewStub)activityStart.findViewById(R.id.sub_pattern_left_top);
        stubLeftTop.setLayoutResource(R.layout.partial_view_left_right);
        View v = stubLeftTop.inflate();
        TextView textView = (TextView)v.findViewById(R.id.tv_title);
        textView.setText(menuList.get(0).getTitle());

        ViewStub stubLeftBottom = (ViewStub)activityStart.findViewById(R.id.sub_pattern_left_bottom);
        stubLeftBottom.setLayoutResource(R.layout.partial_view_left_right);
        stubLeftBottom.inflate();

        ViewStub stubRight = (ViewStub)activityStart.findViewById(R.id.sub_pattern_right);
        stubRight.setLayoutResource(R.layout.partial_view_top_to_bottom);
        stubRight.inflate();
    }

}
