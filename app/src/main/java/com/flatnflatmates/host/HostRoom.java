package com.flatnflatmates.host;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flatnflatmates.flatflatmates.R;

/**
 * Created by applect on 21/2/15.
 */
public class HostRoom extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.host_room, container, false);
        return layout;
    }
}
