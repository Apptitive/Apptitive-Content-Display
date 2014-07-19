package com.apptitive.content_display;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.apptitive.content_display.adapter.ContentMenuAdapter;
import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.ContentMenu;
import com.apptitive.content_display.sync.SyncUtils;

import java.util.List;

public class StartActivity extends ActionBarActivity {

    private ContentMenuAdapter contentMenuAdapter;
    private List<ContentMenu> contentMenuList;
    private ListView lvContentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DbManager.init(this);


        ContentMenu contentMenu1 = new ContentMenu(100,"100","Ramjan","add",1,2);
        ContentMenu contentMenu2 = new ContentMenu(200,"200","Sob e Kadar","add",1,2);
        ContentMenu contentMenu3 = new ContentMenu(300,"300","Tarabih","add",1,2);
        ContentMenu contentMenu4 = new ContentMenu(400,"400","Niot Dua","add",1,2);
        ContentMenu contentMenu5 = new ContentMenu(500,"500","Eid","add",1,2);
        ContentMenu contentMenu6 = new ContentMenu(600,"600","Salat","add",1,2);
        DbManager.getInstance().addMenu(contentMenu1);
        DbManager.getInstance().addMenu(contentMenu2);
        DbManager.getInstance().addMenu(contentMenu3);
        DbManager.getInstance().addMenu(contentMenu4);
        DbManager.getInstance().addMenu(contentMenu5);
        DbManager.getInstance().addMenu(contentMenu6);
        SyncUtils.triggerManualSync();
        lvContentMenu = (ListView) findViewById(R.id.lv_content_menu);
        contentMenuList = DbManager.getInstance().getAllMenus();
        contentMenuAdapter = new ContentMenuAdapter(this, R.layout.list_item_content_menu, contentMenuList);
        lvContentMenu.setAdapter(contentMenuAdapter);

        Log.e("Call","Called");
        for (ContentMenu m : contentMenuList){
            Log.e("Menu Title ",m.getTitle());
        }

/*        ViewStub stubActivityStart = (ViewStub)findViewById(R.id.view_stub_activity_start);
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
        stubRight.inflate();*/
    }

}
