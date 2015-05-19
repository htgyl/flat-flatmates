package com.flatandflatmates.host;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flatandflatmates.GoogleMaps.GoogleMapsActivity;
import com.flatandflatmates.R;

import java.util.HashMap;

/**
 * Created by applect on 21/2/15.
 */
public class HostPropertyDetailsFragment extends Fragment implements View.OnClickListener {

    TextView plusMaxPpl;
    TextView minusMaxPpl;
    TextView textMaxPplView;
    Integer sumMaxPpl = 1;

    TextView plusBathRoom;
    TextView minusBathRoom;
    TextView textBathRoomView;
    Integer sumBathRoom = 0;

    TextView plusRooms;
    TextView minusRooms;
    TextView textRoomsView;
    Integer sumRoom = 0;

    TextView plusBalcony;
    TextView minusBalcony;
    TextView textBalconyView;
    Integer sumBalcony = 0;

    TextView nextClick;
    HashMap<String, Integer> hashMap;

    public static final String SPACE_INTENT = "com.flatandflatmate.intent.action.FLAT_DETAILS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.host_property, container, false);

        plusMaxPpl = (TextView) layout.findViewById(R.id.prop_plus_ttl_ppl);
        minusMaxPpl = (TextView) layout.findViewById(R.id.prop_minus_ttl_ppl);
        plusBathRoom = (TextView) layout.findViewById(R.id.prop_plus_bathroom);
        minusBathRoom = (TextView) layout.findViewById(R.id.prop_minus_bathroom);
        plusRooms = (TextView) layout.findViewById(R.id.prop_plus_room_numbers);
        minusRooms = (TextView) layout.findViewById(R.id.prop_minus_room_numbers);
        plusBalcony = (TextView) layout.findViewById(R.id.prop_plus_balcony);
        minusBalcony = (TextView) layout.findViewById(R.id.prop_minus_balcony);
        textMaxPplView = (TextView) layout.findViewById(R.id.prop_ttl_ppl_numbers_val);
        textBathRoomView = (TextView) layout.findViewById(R.id.prop_bathroom_numbers_val);
        textRoomsView = (TextView) layout.findViewById(R.id.prop_room_numbers_val);
        textBalconyView = (TextView) layout.findViewById(R.id.prop_balcony_numbers_val);
        nextClick = (TextView) layout.findViewById(R.id.prop_next_button);

        plusMaxPpl.setOnClickListener(this);
        minusMaxPpl.setOnClickListener(this);
        plusBathRoom.setOnClickListener(this);
        minusBathRoom.setOnClickListener(this);
        plusRooms.setOnClickListener(this);
        minusRooms.setOnClickListener(this);
        plusBalcony.setOnClickListener(this);
        minusBalcony.setOnClickListener(this);

        nextClick.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prop_plus_ttl_ppl:
                if (sumMaxPpl <= 16) {
                    sumMaxPpl++;
                    textMaxPplView.setText(sumMaxPpl + "");
                    if (sumMaxPpl >= 16) {
                        sumMaxPpl = 17;
                        textMaxPplView.setText("16+");
                    }
                }
                break;
            case R.id.prop_minus_ttl_ppl:
                if (sumMaxPpl >= 1) {
                    sumMaxPpl--;
                    textMaxPplView.setText(sumMaxPpl + "");
                    if (sumMaxPpl <= 1) {
                        sumMaxPpl = 1;
                        textMaxPplView.setText("1");
                    }
                }
                break;
            case R.id.prop_plus_bathroom:
                if (sumBathRoom <= 10) {
                    sumBathRoom++;
                    textBathRoomView.setText(sumBathRoom + "");
                    if (sumBathRoom >= 10) {
                        sumBathRoom = 11;
                        textBathRoomView.setText("10+");
                    }
                }
                break;
            case R.id.prop_minus_bathroom:
                if (sumBathRoom >= 0) {
                    sumBathRoom--;
                    textBathRoomView.setText(sumBathRoom + "");
                    if (sumBathRoom <= 0) {
                        sumBathRoom = 0;
                        textBathRoomView.setText("0");
                    }
                }
                break;
            case R.id.prop_plus_room_numbers:
                if (sumRoom <= 16) {
                    sumRoom++;
                    textRoomsView.setText(sumRoom + "");
                    if (sumRoom >= 16) {
                        sumRoom = 17;
                        textRoomsView.setText("16+");
                    }
                }
                break;
            case R.id.prop_minus_room_numbers:
                if (sumRoom >= 0) {
                    sumRoom--;
                    textRoomsView.setText(sumRoom + "");
                    if (sumRoom <= 0) {
                        sumRoom = 0;
                        textRoomsView.setText("0");
                    }
                }
                break;
            case R.id.prop_plus_balcony:
                if (sumBalcony <= 8) {
                    sumBalcony++;
                    textBalconyView.setText(sumBalcony + "");
                    if (sumBalcony >= 8) {
                        sumBalcony = 9;
                        textBalconyView.setText("8+");
                    }
                }
                break;
            case R.id.prop_minus_balcony:
                if (sumBalcony >= 0) {
                    sumBalcony--;
                    textBalconyView.setText(sumBalcony + "");
                    if (sumBalcony <= 0) {
                        sumBalcony = 0;
                        textBalconyView.setText("0");
                    }
                }
                break;
            case R.id.prop_next_button:
                if( sumMaxPpl != 0 ) {
                    hashMap = new HashMap<String, Integer>();
                    hashMap.put("maxPpl",sumMaxPpl);
                    hashMap.put("bathRooms",sumBathRoom);
                    hashMap.put("bedRooms",sumRoom);
                    hashMap.put("kitchens",sumBalcony);
                    Intent navClick = new Intent(getActivity(),GoogleMapsActivity.class);
                    navClick.setAction(SPACE_INTENT);
                    navClick.putExtra("spaceDetails", hashMap);
                    startActivity(navClick);
                }
                break;
        }
    }
}
