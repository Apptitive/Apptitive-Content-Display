package com.apptitive.content_display;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.ContentMenu;
import com.apptitive.content_display.sync.SyncUtils;
import java.util.List;

public class StartActivity extends ActionBarActivity {

    private List<ContentMenu> contentMenuList;
    private LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DbManager.init(this);
        SyncUtils.triggerInitialSync(this);

        llMain = (LinearLayout) findViewById(R.id.ll_main);
        contentMenuList = DbManager.getInstance().getAllMenus();

        ViewStub viewStub = new ViewStub(this, R.layout.menu_pattern_1);
        llMain.addView(viewStub);
        View view = viewStub.inflate();
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mlp.bottomMargin = 12;
        lp.height = 400;

        view.setLayoutParams(lp);
        view.setLayoutParams(mlp);


        ViewStub stubLeftTop = (ViewStub) view.findViewById(R.id.sub_pattern_left_top);
        stubLeftTop.setLayoutResource(R.layout.partial_view_left_right);
        View v = stubLeftTop.inflate();
        TextView textView = (TextView) v.findViewById(R.id.tv_title);
        textView.setText(contentMenuList.get(0).getTitle());

        ViewStub stubLeftBottom = (ViewStub) view.findViewById(R.id.sub_pattern_left_bottom);
        stubLeftBottom.setLayoutResource(R.layout.partial_view_left_right);
        stubLeftBottom.inflate();

        ViewStub stubRight = (ViewStub) view.findViewById(R.id.sub_pattern_right);
        stubRight.setLayoutResource(R.layout.partial_view_top_to_bottom);
        stubRight.inflate();

        ViewStub viewStub2 = new ViewStub(this, R.layout.menu_pattern_1);
        llMain.addView(viewStub2);
        View view2 = viewStub2.inflate();
        view2.setLayoutParams(lp);
        view2.setLayoutParams(mlp);

        ViewStub stubLeftTop2 = (ViewStub) view2.findViewById(R.id.sub_pattern_left_top);
        stubLeftTop2.setLayoutResource(R.layout.partial_view_left_right);
        View v2 = stubLeftTop2.inflate();
        TextView textView2 = (TextView) v2.findViewById(R.id.tv_title);
        textView2.setText(contentMenuList.get(0).getTitle());

        ViewStub stubLeftBottom2 = (ViewStub) view2.findViewById(R.id.sub_pattern_left_bottom);
        stubLeftBottom2.setLayoutResource(R.layout.partial_view_left_right);
        stubLeftBottom2.inflate();

        ViewStub stubRight2 = (ViewStub) view2.findViewById(R.id.sub_pattern_right);
        stubRight2.setLayoutResource(R.layout.partial_view_top_to_bottom);
        stubRight2.inflate();
    }

}
