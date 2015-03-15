package com.flatandflatmates.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatandflatmates.R;
import com.flatandflatmates.host.HostImageUpload;

/**
 * Created by applect on 20/2/15.
 */
public class SearchOptions extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.host_description, container, false);
        return view;
    }
}
