package com.flatnflatmates.flatflatmates.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.flatnflatmates.flatflatmates.R;

public class MainActivity extends FragmentActivity {
    private boolean isResumed = false;
    private static final int LOGIN = 0;
    private static final int FRAGMENT_COUNT = 1;
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
    private UiLifecycleHelper uiHelper;

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call( Session session, SessionState state, Exception exception ) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
        setContentView( R.layout.main );
        FragmentManager fm = getSupportFragmentManager();
        fragments[LOGIN] = fm.findFragmentById(R.id.loginFragment);
        FragmentTransaction transaction = fm.beginTransaction();
        for(int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
        }
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
        isResumed = true;
    }

    //Called When there is a  change in session state.
    private void onSessionStateChange( Session session,  SessionState state,  Exception exception ) {
        // Only make changes if the activity is visible
        if ( isResumed ) {
            FragmentManager manager = getSupportFragmentManager();
            // Get the number of entries in the back stack
            int backStackSize = manager.getBackStackEntryCount();
            // Clear the back stack
            for (int i = 0; i < backStackSize; i++) {
                manager.popBackStack();
            }
            if ( state.isOpened() ) {
                // If the session state is open:
                // Show the authenticated fragment
                Intent myIntent = new Intent(this, MenuActivity.class );
                startActivity(myIntent);
            } else if ( state.isClosed() ) {
                // If the session state is closed:
                // Show the login fragment
                showFragment(LOGIN, false);
            }
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Session session = Session.getActiveSession();
        if ( session != null  &&  session.isOpened() ) {
            // if the session is already open,
            Intent myIntent = new Intent(this, MenuActivity.class );
            startActivity(myIntent);
        } else {
            // otherwise present the login screen
            // and ask the person to login.
            showFragment(LOGIN, false);
        }
    }

    //Method which shows the Fragment dynamically.
    private void showFragment(int fragmentIndex, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == fragmentIndex) {
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
    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
        isResumed = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    //This method get back the result data from the new intent Activated.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}