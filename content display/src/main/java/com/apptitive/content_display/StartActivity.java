package com.apptitive.content_display;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.helper.DisplayPattern;
import com.apptitive.content_display.model.ContentMenu;
import com.apptitive.content_display.utilities.Constants;

import java.util.List;

public class StartActivity extends ActionBarActivity {

    private List<ContentMenu> contentMenuList;
    private LinearLayout llMain;
    private int currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DbManager.init(this);
        //SyncUtils.triggerInitialSync(this);

        ContentMenu contentMenu1 = new ContentMenu(1, "1", "Title 1", "add", 1, 1);
        ContentMenu contentMenu2 = new ContentMenu(2, "2", "Title 2", "add", 1, 2);
        ContentMenu contentMenu3 = new ContentMenu(3, "3", "Title 3", "add", 1, 3);

        ContentMenu contentMenu4 = new ContentMenu(4, "4", "Title 4", "add", 2, 1);
        ContentMenu contentMenu5 = new ContentMenu(5, "5", "Title 5", "add", 2, 2);
        ContentMenu contentMenu6 = new ContentMenu(6, "6", "Title 6", "add", 2, 3);

        ContentMenu contentMenu7 = new ContentMenu(7, "7", "Title 7", "add", 3, 1);

/*        DbManager.getInstance().addMenu(contentMenu1);
        DbManager.getInstance().addMenu(contentMenu2);
        DbManager.getInstance().addMenu(contentMenu3);

        DbManager.getInstance().addMenu(contentMenu7);

        DbManager.getInstance().addMenu(contentMenu4);
        DbManager.getInstance().addMenu(contentMenu5);
        DbManager.getInstance().addMenu(contentMenu6);*/

        llMain = (LinearLayout) findViewById(R.id.ll_main);
        contentMenuList = DbManager.getInstance().getAllMenus();

        for (ContentMenu c : contentMenuList) {
            Log.e("Menu Title ", c.getTitle());
        }

        for (currentMenu = 0; currentMenu < contentMenuList.size(); ) {
            int patternId = contentMenuList.get(currentMenu).getPatternId();

            if (patternId == 1) {
                ViewStub viewStub = new ViewStub(this, R.layout.menu_pattern_1);
                llMain.addView(viewStub);
                View view = viewStub.inflate();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                mlp.bottomMargin = 12;
                lp.height = 400;

                view.setLayoutParams(lp);
                view.setLayoutParams(mlp);

                populateContentMenuItem(view,R.id.sub_pattern_left_top,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.LeftToRight);
                populateContentMenuItem(view,R.id.sub_pattern_left_bottom,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.LeftToRight);
                populateContentMenuItem(view,R.id.sub_pattern_right,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.ToptoBottom);
            } else if (patternId == 2) {
                ViewStub viewStub = new ViewStub(this, R.layout.menu_pattern_2);
                llMain.addView(viewStub);
                View view = viewStub.inflate();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                mlp.bottomMargin = 12;
                lp.height = 400;

                view.setLayoutParams(lp);
                view.setLayoutParams(mlp);

                populateContentMenuItem(view,R.id.sub_pattern_left,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.ToptoBottom);
                populateContentMenuItem(view,R.id.sub_pattern_right_top,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.LeftToRight);
                populateContentMenuItem(view,R.id.sub_pattern_right_bottom,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.LeftToRight);
            } else if (patternId == 3) {
                ViewStub viewStub = new ViewStub(this, R.layout.menu_pattern_3);
                llMain.addView(viewStub);
                View view = viewStub.inflate();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                mlp.bottomMargin = 12;
                lp.height = 200;

                view.setLayoutParams(lp);
                view.setLayoutParams(mlp);

                populateContentMenuItem(view,R.id.sub_pattern_whole,contentMenuList.get(currentMenu++).getTitle(),DisplayPattern.Whole);
            }
        }

    }

    private void populateContentMenuItem(View view, int subPatternId, final String title, Enum displayPattern) {
        ViewStub stub = (ViewStub) view.findViewById(subPatternId);
        if (displayPattern.equals(DisplayPattern.LeftToRight)) {
            stub.setLayoutResource(R.layout.partial_view_left_right);
        } else if (displayPattern.equals(DisplayPattern.ToptoBottom)) {
            stub.setLayoutResource(R.layout.partial_view_top_to_bottom);
        } else if (displayPattern.equals(DisplayPattern.Whole)) {
            stub.setLayoutResource(R.layout.partial_view_whole);
        }
        View v = stub.inflate();
        TextView textView = (TextView) v.findViewById(R.id.tv_title);
        textView.setText(title);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("View Click",title);
                Intent i = new Intent(getBaseContext(), ContentActivity.class);
                i.putExtra(Constants.menu.EXTRA_MENU_ID, "1");
                i.putExtra(Constants.menu.EXTRA_MENU_TITLE, title);
                startActivity(i);
            }
        });
    }

}
