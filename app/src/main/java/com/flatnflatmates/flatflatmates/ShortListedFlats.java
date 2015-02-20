package com.flatnflatmates.flatflatmates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by applect on 20/2/15.
 */
public class ShortListedFlats extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.shortlisted_flats, container, false);
        return view;
    }
}
