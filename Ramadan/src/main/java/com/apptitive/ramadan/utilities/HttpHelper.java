package com.apptitive.ramadan.utilities;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.apptitive.ramadan.interfaces.JsonTaskCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Sharif on 7/14/2014.
 */
public class HttpHelper {
    private RequestQueue mRequestQueue;
    private String book_url;

    private static HttpHelper uniqueInstance = new HttpHelper();
    private static Context context;
    private static JsonTaskCompleteListener<JSONArray> jsonCallBack;
    public static final String TAG = "VolleyTag";

    public static HttpHelper getInstance(Context context,
                                           JsonTaskCompleteListener<JSONArray> jsonCallBack) {
        HttpHelper.context = context;
        HttpHelper.jsonCallBack = jsonCallBack;
        return uniqueInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
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
                Log.e("response error", "error");
            }
        });
        addToRequestQueue(req);
    }
}
