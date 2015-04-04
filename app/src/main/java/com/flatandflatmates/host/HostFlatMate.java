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
 * Created by applect on 21/2/15.
 */
public class HostFlatMate extends Fragment implements View.OnClickListener {

    Button plusMaxPplButton;
    Button minusMaxPplButton;
    TextView textMaxPplView;
    Integer sumMaxPpl = 1;

    Button plusBathRoomButton;
    Button minusBathRoomButton;
    TextView textBathRoomView;
    Integer sumBathRoom = 0;

    Button plusBedRoomsButton;
    Button minusBedRoomsButton;
    TextView textBedRoomsView;
    Integer sumBedRoom = 0;

    Button plusBedsButton;
    Button minusBedsButton;
    TextView textBedView;
    Integer sumBeds = 0;

    Button nextButtonClick;
    HashMap<String, Integer> hashMap;
    public static final String SPACE_INTENT = "com.flatandflatmate.intent.action.FLATMATE_DETAILS";

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.host_flatmate, container, false);

        plusMaxPplButton = (Button) layout.findViewById(R.id.maxPeopleFMPlusButton);
        minusMaxPplButton = (Button) layout.findViewById(R.id.maxPeopleFMMinusButton);
        plusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomFMPlusButton);
        minusBathRoomButton = (Button) layout.findViewById(R.id.bathFMRoomMinusButton);
        plusBedRoomsButton = (Button) layout.findViewById(R.id.bedRoomFMPlusButton);
        minusBedRoomsButton = (Button) layout.findViewById(R.id.bedRoomFMMinusButton);
        plusBedsButton = (Button) layout.findViewById(R.id.bedFMPlushBtn);
        minusBedsButton = (Button) layout.findViewById(R.id.bedFMMinusButton);
        textMaxPplView = (TextView) layout.findViewById(R.id.maxPeopleFMTextView);
        textBathRoomView = (TextView) layout.findViewById(R.id.bathFMRoomTextView);
        textBedRoomsView = (TextView) layout.findViewById(R.id.bedRoomFMTextview);
        textBedView = (TextView) layout.findViewById(R.id.bedFMTextView);
        nextButtonClick = (Button) layout.findViewById(R.id.flatMateNextButton);

        plusMaxPplButton.setOnClickListener(this);
        minusMaxPplButton.setOnClickListener(this);
        plusBathRoomButton.setOnClickListener(this);
        minusBathRoomButton.setOnClickListener(this);
        plusBedRoomsButton.setOnClickListener(this);
        minusBedRoomsButton.setOnClickListener(this);
        plusBedsButton.setOnClickListener(this);
        minusBedsButton.setOnClickListener(this);
        nextButtonClick.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.maxPeopleFMPlusButton:
                if( sumMaxPpl <= 16  ) {
                    sumMaxPpl++;
                    textMaxPplView.setText( sumMaxPpl+"" );
                    if( sumMaxPpl >= 16 ){
                        sumMaxPpl = 17;
                        textMaxPplView.setText("16+");
                    }
                }
                break;
            case R.id.maxPeopleFMMinusButton:
                if( sumMaxPpl >= 1  ) {
                    sumMaxPpl--;
                    textMaxPplView.setText( sumMaxPpl+"" );
                    if( sumMaxPpl <= 1 ){
                        sumMaxPpl = 1;
                        textMaxPplView.setText("1");
                    }
                }
                break;
            case R.id.bathRoomFMPlusButton:
                if( sumBathRoom <= 10  ) {
                    sumBathRoom++;
                    textBathRoomView.setText( sumBathRoom+"" );
                    if( sumBathRoom >= 10 ){
                        sumBathRoom = 11;
                        textBathRoomView.setText("10+");
                    }
                }
                break;
            case R.id.bathFMRoomMinusButton:
                if( sumBathRoom >= 0  ) {
                    sumBathRoom--;
                    textBathRoomView.setText( sumBathRoom+"" );
                    if( sumBathRoom <= 0 ){
                        sumBathRoom = 0;
                        textBathRoomView.setText("0");
                    }
                }
                break;
            case R.id.bedRoomFMPlusButton:
                if( sumBedRoom <= 8  ) {
                    sumBedRoom++;
                    textBedRoomsView.setText( sumBedRoom+"" );
                    if( sumBedRoom >= 8 ){
                        sumBedRoom = 9;
                        textBedRoomsView.setText("8+");
                    }
                }
                break;
            case R.id.bedRoomFMMinusButton:
                if( sumBedRoom >= 0  ) {
                    sumBedRoom--;
                    textBedRoomsView.setText( sumBedRoom+"" );
                    if( sumBedRoom <= 0 ){
                        sumBedRoom = 0;
                        textBedRoomsView.setText("0");
                    }
                }
                break;
            case R.id.bedFMPlushBtn:
                if( sumBeds <= 8  ) {
                    sumBeds++;
                    textBedView.setText( sumBeds+"" );
                    if( sumBeds >= 8 ){
                        sumBeds = 9;
                        textBedView.setText("8+");
                    }
                }
                break;
            case R.id.bedFMMinusButton:
                if( sumBeds >= 0  ) {
                    sumBeds--;
                    textBedView.setText( sumBeds+"" );
                    if( sumBeds <= 0 ){
                        sumBeds = 0;
                        textBedView.setText("0");
                    }
                }
                break;
            case R.id.flatMateNextButton:
                if( sumMaxPpl != 0 ) {
                    hashMap = new HashMap<String, Integer>();
                    hashMap.put("maxPpl",sumMaxPpl);
                    hashMap.put("bathRooms",sumBathRoom);
                    hashMap.put("bedRooms",sumBedRoom);
                    hashMap.put("beds",sumBeds);
                    Intent navClick = new Intent(getActivity(),GoogleMapsActivity.class);
                    navClick.setAction(SPACE_INTENT);
                    navClick.putExtra("spaceDetails", hashMap);
                    startActivity(navClick);
                }
                break;
        }
    }
}
