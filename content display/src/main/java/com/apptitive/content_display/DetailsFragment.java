package com.apptitive.content_display;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apptitive.content_display.adapter.DetailsListAdapter;
import com.apptitive.content_display.model.Content;
import com.apptitive.content_display.model.Detail;
import com.apptitive.content_display.model.JsonDetail;
import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailsFragment extends ListFragment {

    private XmlPullParserFactory parserFactory;
    private DetailProvider detailProvider;
    private DetailsListAdapter detailsListAdapter;
    private List<Detail> details;
    private Gson gson;

    public DetailsFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        // EasyTracker.getInstance(getActivity()).activityStart(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        //  EasyTracker.getInstance(getActivity()).activityStop(getActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            detailProvider = (DetailProvider) activity;
        } catch (ClassCastException cce) {
            Log.e(this.getTag(), "Parent activity must implement DetailProvider");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        detailsListAdapter = new DetailsListAdapter(getActivity(), jsonToDetail(Arrays.asList(gson.fromJson(detailProvider.getContent().getDetails(), JsonDetail[].class))));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setListAdapter(detailsListAdapter);
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    private List<Detail> jsonToDetail(List<JsonDetail> jsonDetails) {
        if (details == null)
            details = new ArrayList<Detail>();
        else details.clear();

        for (JsonDetail jsonDetail : jsonDetails) {
            Detail detail1 = new Detail();
            detail1.populateFrom(jsonDetail);
            details.add(detail1);
        }
        return details;
    }

    public void changeTopic(Content content) {
        jsonToDetail(Arrays.asList(gson.fromJson(content.getDetails(), JsonDetail[].class)));
        getListView().setAdapter(detailsListAdapter);
    }

    public interface DetailProvider {
        Content getContent();
    }
}
