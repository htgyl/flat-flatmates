package com.flatnflatmates.flatflatmates.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatnflatmates.flatflatmates.R;
import com.flatnflatmates.flatflatmates.host.HostImageUpload;

/**
 * Created by applect on 20/2/15.
 */
public class SearchOptions extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.host_description, container, false);
        Intent intent = new Intent(getActivity(), HostImageUpload.class);
        startActivity(intent);
        return view;
    }
}