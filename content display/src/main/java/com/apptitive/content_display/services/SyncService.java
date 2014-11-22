package com.apptitive.content_display.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.model.ContentMenu;
import com.apptitive.content_display.model.DbContent;
import com.apptitive.content_display.utilities.Config;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.HttpHelper;
import com.apptitive.content_display.utilities.JsonParser;
import com.apptitive.content_display.utilities.LogUtil;

import org.json.JSONArray;

public class SyncService extends Service {
    private DbManager dbManager;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HttpHelper httpHelper = HttpHelper.getInstance(this);
        DbManager.init(this);
        dbManager = DbManager.getInstance();

        JsonArrayRequest menuRequest = new JsonArrayRequest(Config.getMenuUrl(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        LogUtil.LOGE(response.toString());
                        ContentMenu.updateDb(dbManager, JsonParser.getParsedContentMenus(response.toString()));
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        httpHelper.addToRequestQueue(menuRequest);

        JsonArrayRequest topicRequest = new JsonArrayRequest(Config.getTopicUrl(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        LogUtil.LOGE("topic url" + response.toString());
                        DbContent.updateDb(dbManager, JsonParser.getParsedDbContents(response));
                        Intent localIntent = new Intent(Constants.ACTION_RESPONSE);
                        LocalBroadcastManager.getInstance(SyncService.this).sendBroadcast(
                                localIntent);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        httpHelper.addToRequestQueue(topicRequest);
        LogUtil.LOGE("inside test service");
        return START_STICKY;
    }
}
