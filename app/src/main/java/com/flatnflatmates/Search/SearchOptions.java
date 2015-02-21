package com.flatnflatmates.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatnflatmates.flatflatmates.R;

/**
 * Created by applect on 20/2/15.
 */
public class SearchOptions extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_options, container, false);
        return view;
    }
}
