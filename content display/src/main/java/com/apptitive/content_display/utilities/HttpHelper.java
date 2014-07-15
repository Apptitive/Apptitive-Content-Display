package com.apptitive.content_display.utilities;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apptitive.content_display.interfaces.JsonArrayCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sharif on 7/14/2014.
 */
public class HttpHelper {
    private RequestQueue mRequestQueue;
    private static HttpHelper uniqueInstance = new HttpHelper();
    private static Context context;
    private static JsonArrayCompleteListener<JSONArray> jsonCallBack;

    public static final String TAG = "VolleyTag";
    private ImageLoader mImageLoader;

    public static HttpHelper getInstance(Context context,JsonArrayCompleteListener<JSONArray> jsonCallBack) {
        HttpHelper.context = context;
        HttpHelper.jsonCallBack = jsonCallBack;
        return uniqueInstance;
    }
    public static HttpHelper getInstance(Context context) {
        HttpHelper.context = context;
        return uniqueInstance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void getJsonArray(String url) {

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            jsonCallBack.onJsonArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                LogUtil.LOGE("response error");
            }
        });
        addToRequestQueue(req);
    }


    public  void getJsonObject(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
              LogUtil.LOGE(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.LOGE("error");
            }
        });
        addToRequestQueue(jsonObjectRequest);
    }
}
