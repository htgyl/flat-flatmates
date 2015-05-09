package com.flatandflatmates.host;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.flatandflatmates.R;

public class HostOptionsClick extends ActionBarActivity {

    private static final int PROPERTY= 0;

    private Fragment propTypeFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_options_click_handle);

        FragmentManager fm = getSupportFragmentManager();
        propTypeFragment = fm.findFragmentById( R.id.host_property_fragment );

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(propTypeFragment);
        transaction.commit();

        Bundle param = getIntent( ).getExtras( );
        int position = param.getInt("position");
        showFragment(position, false);
    }

    //Method which shows the Fragment dynamically.
    private void showFragment(int fragmentIndex, boolean addToBackStack) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.show( propTypeFragment );

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
