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
import com.apptitive.content_display.utilities.LogUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sharif on 7/16/2014.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter implements JsonArrayCompleteListener<JSONArray> {

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        LogUtil.LOGE("inside Syncadapter");
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
        Gson gson = new Gson();
        if (requestCode == Constants.MENU_REQUEST_CODE) {
            List<ContentMenu> menus = Arrays.asList(gson.fromJson(result.toString(), ContentMenu[].class));
            DbManager.getInstance().addMenu(menus);
        } else if (requestCode == Constants.CONTENT_REQUEST_CODE) {
            DbManager.getInstance().addDbContent(getParsedDbContentResult(result));
            LogUtil.LOGE("successful");
        }
    }

    private List<DbContent> getParsedDbContentResult(JSONArray result) {
        List<DbContent> dbContents = new ArrayList<DbContent>();
        for (int i = 0; i < result.length(); i++) {
            try {
                JSONObject jsonObject = (JSONObject) result.get(i);
                DbContent dbContent = new DbContent();
                dbContent.setActionId((Integer) jsonObject.get("actionId"));
                dbContent.setContentId(jsonObject.get("contentId").toString());
                dbContent.setMenuId(jsonObject.get("menuId").toString());
                dbContent.setHeader(jsonObject.get("header").toString());
                dbContent.setShortDescription(jsonObject.get("shortDescription").toString());
                dbContent.setDetails(jsonObject.get("details").toString());
                dbContent.setViewType(jsonObject.get("viewType").toString());
                dbContent.setActionType(jsonObject.get("actionType").toString());
                dbContents.add(dbContent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return dbContents;
    }
}
