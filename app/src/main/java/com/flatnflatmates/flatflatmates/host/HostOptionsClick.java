package com.flatnflatmates.flatflatmates.host;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.flatnflatmates.flatflatmates.R;

public class HostOptionsClick extends ActionBarActivity {

    private static final int FLAT= 0;
    private static final int FLATMATE= 1;
    private static final int ROOM= 2;
    private static final int PG = 3;
    private static final int FRAGMENT_COUNT = 4;
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_options_click_handle);
        FragmentManager fm = getSupportFragmentManager();
        fragments[FLAT] = fm.findFragmentById( R.id.hostFlatFragment );
        fragments[FLATMATE] = fm.findFragmentById( R.id.hostFlatMateFragment );
        fragments[ROOM] = fm.findFragmentById( R.id.hostRoomFragment );
        fragments[PG] = fm.findFragmentById( R.id.hostPgFragment );
        FragmentTransaction transaction = fm.beginTransaction();
        for(int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
        }
        transaction.commit();
        Bundle param = getIntent(  ).getExtras(  );
        int position = param.getInt( "position" );
        showFragment( position, false );
    }

    //Method which shows the Fragment dynamically.
    private void showFragment(int fragmentIndex, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if ( i == fragmentIndex ) {
                transaction.show(fragments[i]);
            } else {
                transaction.hide(fragments[i]);
            }
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
