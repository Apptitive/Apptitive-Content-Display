package com.apptitive.content_display.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.apptitive.content_display.helper.DbManager;
import com.apptitive.content_display.interfaces.JsonArrayCompleteListener;
import com.apptitive.content_display.model.ContentMenu;
import com.apptitive.content_display.model.DbContent;
import com.apptitive.content_display.utilities.Config;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.HttpHelper;
import com.apptitive.content_display.utilities.JsonParser;
import com.apptitive.content_display.utilities.LogUtil;
import com.google.gson.Gson;

import org.json.JSONArray;

/**
 * Created by Sharif on 7/16/2014.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter implements JsonArrayCompleteListener<JSONArray> {

    private DbManager dbManager;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        LogUtil.LOGE("inside Syncadapter");
        DbManager.init(getContext());
        dbManager = DbManager.getInstance();
        HttpHelper httpHelper = HttpHelper.getInstance(getContext(), this);
        httpHelper.getJsonArray(Config.getMenuUrl(), Constants.MENU_REQUEST_CODE);
        httpHelper.getJsonArray(Config.getTopicUrl(), Constants.CONTENT_REQUEST_CODE);

        // for imageloading
        /*ImageLoader imageLoader = HttpHelper.getInstance(this).getImageLoader();
        NetworkImageView imgNetWorkView=(NetworkImageView)findViewById(R.id.imgDemo);
        imgNetWorkView.setImageUrl(Config.getImageUrl(this)+"1.9.png", imageLoader);*/

    }

    @Override
    public void onJsonArray(JSONArray result, int requestCode) {
        if (requestCode == Constants.MENU_REQUEST_CODE) {
            ContentMenu.updateDb(dbManager, JsonParser.getParsedContentMenus(result.toString()));
        } else if (requestCode == Constants.CONTENT_REQUEST_CODE) {
            DbContent.updateDb(dbManager, JsonParser.getParsedDbContents(result));
            LogUtil.LOGE("successful");
        }
    }


}
