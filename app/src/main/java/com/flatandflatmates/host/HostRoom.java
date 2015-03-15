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

/**
 * Created by applect on 21/2/15.
 */
public class HostRoom extends Fragment implements View.OnClickListener {

    Button plusMaxPplButton;
    Button minusMaxPplButton;
    TextView textMaxPplView;
    Integer sumMaxPpl = 1;

    Button plusBathRoomButton;
    Button minusBathRoomButton;
    TextView textBathRoomView;
    Integer sumBathRoom = 0;
    Button nextBtnClick;

    Button plusBedsButton;
    Button minusBedsButton;
    TextView textBedView;
    Integer sumBeds = 0;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.host_room, container, false);

        plusMaxPplButton = (Button) layout.findViewById(R.id.maxPeoplePerRoomPlusButton);
        minusMaxPplButton = (Button) layout.findViewById(R.id.maxPeoplePerRoomMinusButton);
        plusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomPerRoomPlusButton);
        minusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomPerRoomMinusButton);
        plusBedsButton = (Button) layout.findViewById(R.id.bedPerRoomPlusButton);
        minusBedsButton = (Button) layout.findViewById(R.id.bedPerRoomMinusButton);
        textMaxPplView = (TextView) layout.findViewById(R.id.maxPeoplePerRoomTextView);
        textBathRoomView = (TextView) layout.findViewById(R.id.bathRoomPerRoomTextView);
        textBedView = (TextView) layout.findViewById(R.id.bedPerRoomTextview);
        nextBtnClick = (Button) layout.findViewById(R.id.roomNextButton);

        plusMaxPplButton.setOnClickListener(this);
        minusMaxPplButton.setOnClickListener(this);
        plusBathRoomButton.setOnClickListener(this);
        minusBathRoomButton.setOnClickListener(this);
        plusBedsButton.setOnClickListener(this);
        minusBedsButton.setOnClickListener(this);
        nextBtnClick.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.maxPeoplePerRoomPlusButton:
                if( sumMaxPpl <= 8  ) {
                    sumMaxPpl++;
                    textMaxPplView.setText( sumMaxPpl+"" );
                    if( sumMaxPpl >= 8 ){
                        sumMaxPpl = 9;
                        textMaxPplView.setText("8+");
                    }
                }
                break;
            case R.id.maxPeoplePerRoomMinusButton:
                if( sumMaxPpl >= 1  ) {
                    sumMaxPpl--;
                    textMaxPplView.setText( sumMaxPpl+"" );
                    if( sumMaxPpl <= 1 ){
                        sumMaxPpl = 1;
                        textMaxPplView.setText("1");
                    }
                }
                break;
            case R.id.bathRoomPerRoomPlusButton:
                if( sumBathRoom <= 2  ) {
                    sumBathRoom++;
                    textBathRoomView.setText( sumBathRoom+"" );
                    if( sumBathRoom >= 2 ){
                        sumBathRoom = 3;
                        textBathRoomView.setText("2+");
                    }
                }
                break;
            case R.id.bathRoomPerRoomMinusButton:
                if( sumBathRoom >= 0  ) {
                    sumBathRoom--;
                    textBathRoomView.setText( sumBathRoom+"" );
                    if( sumBathRoom <= 0 ){
                        sumBathRoom = 0;
                        textBathRoomView.setText("0");
                    }
                }
                break;
            case R.id.bedPerRoomPlusButton:
                if( sumBeds <= 8  ) {
                    sumBeds++;
                    textBedView.setText( sumBeds+"" );
                    if( sumBeds >= 8 ){
                        sumBeds = 9;
                        textBedView.setText("8+");
                    }
                }
                break;
            case R.id.bedPerRoomMinusButton:
                if( sumBeds >= 0  ) {
                    sumBeds--;
                    textBedView.setText( sumBeds+"" );
                    if( sumBeds <= 0 ){
                        sumBeds = 0;
                        textBedView.setText("0");
                    }
                }
                break;
            case R.id.roomNextButton:
                if( sumMaxPpl != 0 ) {
                    Intent navClick = new Intent(getActivity(),GoogleMapsActivity.class);
                    startActivity(navClick);
                }
                break;
        }
    }
}
