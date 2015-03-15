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
public class HostFlat extends Fragment implements View.OnClickListener {

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

    Button plusKitchenButton;
    Button minusKitchenButton;
    TextView textKitchenView;
    Integer sumKitchen = 0;

    Button nextButtonClick;
    HashMap<String, Integer> hashMap;
    public static final String SPACE_INTENT = "com.flatandflatmate.intent.action.FLAT_DETAILS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.host_flat, container, false);

        plusMaxPplButton = (Button) layout.findViewById(R.id.maxPeoplePlusButton);
        minusMaxPplButton = (Button) layout.findViewById(R.id.maxPeopleMinusButton);
        plusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomPlusButton);
        minusBathRoomButton = (Button) layout.findViewById(R.id.bathRoomMinusButton);
        plusBedRoomsButton = (Button) layout.findViewById(R.id.bedRoomPlusButton);
        minusBedRoomsButton = (Button) layout.findViewById(R.id.bedRoomMinusButton);
        plusKitchenButton = (Button) layout.findViewById(R.id.kitchenPlushBtn);
        minusKitchenButton = (Button) layout.findViewById(R.id.kitchenMinusButton);
        textMaxPplView = (TextView) layout.findViewById(R.id.maxPeopleTextView);
        textBathRoomView = (TextView) layout.findViewById(R.id.bathRoomTextView);
        textBedRoomsView = (TextView) layout.findViewById(R.id.bedRoomTextview);
        textKitchenView = (TextView) layout.findViewById(R.id.kitchenTextView);
        nextButtonClick = (Button) layout.findViewById(R.id.flatNextButton);

        plusMaxPplButton.setOnClickListener(this);
        minusMaxPplButton.setOnClickListener(this);
        plusBathRoomButton.setOnClickListener(this);
        minusBathRoomButton.setOnClickListener(this);
        plusBedRoomsButton.setOnClickListener(this);
        minusBedRoomsButton.setOnClickListener(this);
        plusKitchenButton.setOnClickListener(this);
        minusKitchenButton.setOnClickListener(this);

        nextButtonClick.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.maxPeoplePlusButton:
                if (sumMaxPpl <= 16) {
                    sumMaxPpl++;
                    textMaxPplView.setText(sumMaxPpl + "");
                    if (sumMaxPpl >= 16) {
                        sumMaxPpl = 17;
                        textMaxPplView.setText("16+");
                    }
                }
                break;
            case R.id.maxPeopleMinusButton:
                if (sumMaxPpl >= 1) {
                    sumMaxPpl--;
                    textMaxPplView.setText(sumMaxPpl + "");
                    if (sumMaxPpl <= 1) {
                        sumMaxPpl = 1;
                        textMaxPplView.setText("1");
                    }
                }
                break;
            case R.id.bathRoomPlusButton:
                if (sumBathRoom <= 10) {
                    sumBathRoom++;
                    textBathRoomView.setText(sumBathRoom + "");
                    if (sumBathRoom >= 10) {
                        sumBathRoom = 11;
                        textBathRoomView.setText("10+");
                    }
                }
                break;
            case R.id.bathRoomMinusButton:
                if (sumBathRoom >= 0) {
                    sumBathRoom--;
                    textBathRoomView.setText(sumBathRoom + "");
                    if (sumBathRoom <= 0) {
                        sumBathRoom = 0;
                        textBathRoomView.setText("0");
                    }
                }
                break;
            case R.id.bedRoomPlusButton:
                if (sumBedRoom <= 16) {
                    sumBedRoom++;
                    textBedRoomsView.setText(sumBedRoom + "");
                    if (sumBedRoom >= 16) {
                        sumBedRoom = 17;
                        textBedRoomsView.setText("16+");
                    }
                }
                break;
            case R.id.bedRoomMinusButton:
                if (sumBedRoom >= 0) {
                    sumBedRoom--;
                    textBedRoomsView.setText(sumBedRoom + "");
                    if (sumBedRoom <= 0) {
                        sumBedRoom = 0;
                        textBedRoomsView.setText("0");
                    }
                }
                break;
            case R.id.kitchenPlushBtn:
                if (sumKitchen <= 8) {
                    sumKitchen++;
                    textKitchenView.setText(sumKitchen + "");
                    if (sumKitchen >= 8) {
                        sumKitchen = 9;
                        textKitchenView.setText("8+");
                    }
                }
                break;
            case R.id.kitchenMinusButton:
                if (sumKitchen >= 0) {
                    sumKitchen--;
                    textKitchenView.setText(sumKitchen + "");
                    if (sumKitchen <= 0) {
                        sumKitchen = 0;
                        textKitchenView.setText("0");
                    }
                }
                break;
            case R.id.flatNextButton:
                if( sumMaxPpl != 0 ) {
                    hashMap.put("maxPpl",sumMaxPpl);
                    hashMap.put("bathRooms",sumBathRoom);
                    hashMap.put("bedRooms",sumBedRoom);
                    hashMap.put("kitchens",sumKitchen);
                    Intent navClick = new Intent(getActivity(),GoogleMapsActivity.class);
                    navClick.setAction(SPACE_INTENT);
                    navClick.putExtra("spaceDetails", hashMap);
                    startActivity(navClick);
                }
                break;
        }
    }
}
