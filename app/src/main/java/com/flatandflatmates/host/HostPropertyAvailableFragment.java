package com.flatandflatmates.host;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flatandflatmates.GoogleMaps.GoogleMapsActivity;
import com.flatandflatmates.R;

import java.util.HashMap;

/**
 * Created by Hitesh Goel on 21/2/15.
 */
public class HostPropertyAvailableFragment extends Fragment implements View.OnClickListener {

    TextView plusBedRoomsTextView;
    TextView minusBedRoomsTextView;
    TextView textBedRoomsView;
    Integer sumBedRoom = 0;

    TextView nextButtonClick;
    HashMap<String, Integer> hashMap;
    public static final String SPACE_INTENT = "com.flatandflatmate.intent.action.FLATMATE_DETAILS";

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.host_property_available, container, false);

        plusBedRoomsTextView = (TextView) layout.findViewById(R.id.avlbl_plus_room_numbers);
        minusBedRoomsTextView = (TextView) layout.findViewById(R.id.avlbl_minus_room_numbers);
        textBedRoomsView = (TextView) layout.findViewById(R.id.avlbl_room_numbers);
        nextButtonClick = (TextView) layout.findViewById(R.id.avlb_next_button);


        plusBedRoomsTextView.setOnClickListener(this);
        minusBedRoomsTextView.setOnClickListener(this);
        nextButtonClick.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.avlbl_plus_room_numbers:
                if( sumBedRoom <= 8  ) {
                    sumBedRoom++;
                    textBedRoomsView.setText( sumBedRoom+"" );
                    if( sumBedRoom >= 8 ){
                        sumBedRoom = 9;
                        textBedRoomsView.setText("8+");
                    }
                }
                break;
            case R.id.avlbl_minus_room_numbers:
                if( sumBedRoom >= 0  ) {
                    sumBedRoom--;
                    textBedRoomsView.setText( sumBedRoom+"" );
                    if( sumBedRoom <= 0 ){
                        sumBedRoom = 0;
                        textBedRoomsView.setText("0");
                    }
                }
                break;
            case R.id.avlb_next_button:
                hashMap = new HashMap<String, Integer>();
                hashMap.put("bedRooms",sumBedRoom);
                Intent navClick = new Intent(getActivity(),GoogleMapsActivity.class);
                navClick.setAction(SPACE_INTENT);
                navClick.putExtra("spaceDetails", hashMap);
                startActivity(navClick);
                break;
        }
    }
}
