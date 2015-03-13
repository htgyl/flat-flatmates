package com.flatnflatmates.flatflatmates.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.flatnflatmates.flatflatmates.GoogleMaps.GoogleMapsActivity;
import com.flatnflatmates.flatflatmates.R;

public class NavigationDrawerClickHandleActivity extends ActionBarActivity {


    private static final int FLATLISTS= 0;
    private static final int SEARCH= 1;
    private static final int HOSTOPTIONS = 2;
    //private static final int SHORTLIST= 3;
    private static final int FRAGMENT_COUNT = 3;
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.navigation_drawer_click_handle );
        FragmentManager fm = getSupportFragmentManager(  );
        fragments[HOSTOPTIONS] = fm.findFragmentById( R.id.hostingOptionsFragment );
        fragments[FLATLISTS] = fm.findFragmentById( R.id.flatListViewFragment );
        fragments[SEARCH] = fm.findFragmentById( R.id.searchOptionsFragment );
        //fragments[SHORTLIST] = fm.findFragmentById( R.id.shortlistedFlatsFragment );
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
        if( fragmentIndex == 3 ){
            Intent navClick = new Intent(this, GoogleMapsActivity.class);
            startActivity(navClick);
        }
        transaction.commit();
    }
}
