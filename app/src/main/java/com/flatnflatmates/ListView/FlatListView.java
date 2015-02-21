package com.flatnflatmates.ListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatnflatmates.flatflatmates.R;

/**
 * Created by applect on 20/2/15.
 */
public class FlatListView extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.flat_list_view, container, false);
        return view;
    }
}
