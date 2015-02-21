package com.flatnflatmates.host;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flatnflatmates.flatflatmates.R;

/**
 * Created by applect on 21/2/15.
 */
public class HostPg extends Fragment implements View.OnClickListener {

    Button plusMaxPplButton;
    Button minusMaxPplButton;
    TextView textMaxPplView;
    Integer sumMaxPpl = 0;

    Button plusBathRoomButton;
    Button minusBathRoomButton;
    TextView textBathRoomView;
    Integer sumBathRoom = 0;

    Button plusBedsButton;
    Button minusBedsButton;
    TextView textBedView;
    Integer sumBeds = 0;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.host_pg, container, false);

        plusMaxPplButton = (Button) layout.findViewById(R.id.maxPeoplePerRoomPGPlusButton);
        minusMaxPplButton = (Button) layout.findViewById(R.id.maxPeoplePerRoomPGMinusButton);
        plusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomPGPlusButton);
        minusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomPGMinusButton);
        plusBedsButton = (Button) layout.findViewById(R.id.bedPerRoomPGPlusButton);
        minusBedsButton = (Button) layout.findViewById(R.id.bedPerRoomPGMinusButton);
        textMaxPplView = (TextView) layout.findViewById(R.id.maxPeoplePerRoomPGTextView);
        textBathRoomView = (TextView) layout.findViewById(R.id.bathRoomPGTextView);
        textBedView = (TextView) layout.findViewById(R.id.bedPerRoomPGTextview);

        plusMaxPplButton.setOnClickListener(this);
        minusMaxPplButton.setOnClickListener(this);
        plusBathRoomButton.setOnClickListener(this);
        minusBathRoomButton.setOnClickListener(this);
        plusBedsButton.setOnClickListener(this);
        minusBedsButton.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick( View v ) {
        switch ( v.getId() ) {
            case R.id.maxPeoplePerRoomPGPlusButton:
                if( sumMaxPpl <= 8  ) {
                    sumMaxPpl++;
                    textMaxPplView.setText( sumMaxPpl+"" );
                    if( sumMaxPpl >= 8 ){
                        sumMaxPpl = 9;
                        textMaxPplView.setText("8+");
                    }
                }
                break;
            case R.id.maxPeoplePerRoomPGMinusButton:
                if( sumMaxPpl >= 0  ) {
                    sumMaxPpl--;
                    textMaxPplView.setText( sumMaxPpl+"" );
                    if( sumMaxPpl <= 0 ){
                        sumMaxPpl = 0;
                        textMaxPplView.setText("0");
                    }
                }
                break;
            case R.id.bathRoomPGPlusButton:
                if( sumBathRoom <= 2  ) {
                    sumBathRoom++;
                    textBathRoomView.setText( sumBathRoom+"" );
                    if( sumBathRoom >= 2 ){
                        sumBathRoom = 3;
                        textBathRoomView.setText("2+");
                    }
                }
                break;
            case R.id.bathRoomPGMinusButton:
                if( sumBathRoom >= 0  ) {
                    sumBathRoom--;
                    textBathRoomView.setText( sumBathRoom+"" );
                    if( sumBathRoom <= 0 ){
                        sumBathRoom = 0;
                        textBathRoomView.setText("0");
                    }
                }
                break;
            case R.id.bedPerRoomPGPlusButton:
                if( sumBeds <= 8  ) {
                    sumBeds++;
                    textBedView.setText( sumBeds+"" );
                    if( sumBeds >= 8 ){
                        sumBeds = 9;
                        textBedView.setText("8+");
                    }
                }
                break;
            case R.id.bedPerRoomPGMinusButton:
                if( sumBeds >= 0  ) {
                    sumBeds--;
                    textBedView.setText( sumBeds+"" );
                    if( sumBeds <= 0 ){
                        sumBeds = 0;
                        textBedView.setText("0");
                    }
                }
                break;
        }
    }
}
