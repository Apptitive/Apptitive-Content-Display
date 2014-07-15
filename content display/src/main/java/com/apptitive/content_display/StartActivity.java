package com.apptitive.content_display;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import static com.apptitive.content_display.R.layout.test_inner;

public class StartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

}
