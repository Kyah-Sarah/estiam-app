package com.sarahman.projects.graph.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sarahman.projects.graph.BuildConfig;
import com.sarahman.projects.graph.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BottomSheetDialogFragment {

    private Context context;
    private String stationNameValue;
    private String updatedAtValue;
    private int totalBikesValue;
    private int freeBikesValue;

    private static final String STATION_NAME = "StationName";
    private static final String UPDATED_AT = "UpdatedAt";
    private static final String TOTAL_BIKES = "TotalBikes";
    private static final String FREE_BIKES = "FreeBikes";

    private TextView stationName;
    private TextView updatedAt;
    private TextView totalBikes;
    private TextView freeBikes;

    public DetailsFragment() { }

    public static DetailsFragment newInstance(String stationNameValue, String updatedAtValue, int totalBikesValue, int freeBikesValue) {
        Bundle bundle = new Bundle();
        bundle.putString(STATION_NAME, stationNameValue);
        bundle.putString(UPDATED_AT, updatedAtValue);
        bundle.putInt(TOTAL_BIKES, totalBikesValue);
        bundle.putInt(FREE_BIKES, freeBikesValue);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(STATION_NAME) && bundle.containsKey(UPDATED_AT) && bundle.containsKey(TOTAL_BIKES) && bundle.containsKey(FREE_BIKES)) {
                stationNameValue = bundle.getString(STATION_NAME);
                updatedAtValue = bundle.getString(UPDATED_AT);
                totalBikesValue = bundle.getInt(TOTAL_BIKES);
                freeBikesValue = bundle.getInt(FREE_BIKES);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        stationName = rootView.findViewById(R.id.stationName);
        updatedAt = rootView.findViewById(R.id.updatedAt);
        totalBikes = rootView.findViewById(R.id.totalBikes);
        freeBikes = rootView.findViewById(R.id.freeBikes);

        stationName.setText(stationNameValue);
        updatedAt.setText(updatedAtValue);
        totalBikes.setText(String.valueOf(totalBikesValue));
        freeBikes.setText(String.valueOf(""+freeBikesValue + " / "));

        return rootView;
    }
}
