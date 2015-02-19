package com.flatnflatmates.flatflatmates;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */

public class NavigationDrawerFragment extends Fragment {

    /*
    STEPS TO HANDLE THE RECYCLER CLICK
    1 Create a class that EXTENDS RecylcerView.OnItemTouchListener
    2 Create an interface inside that class that supports click and long click and indicates the View that was clicked and the position where it was clicked
    3 Create a GestureDetector to detect ACTION_UP single tap and Long Press events
    4 Return true from the singleTap to indicate your GestureDetector has consumed the event.
    5 Find the childView containing the coordinates specified by the MotionEvent and if the childView is not null and the listener is not null either, fire a long click event
    6 Use the onInterceptTouchEvent of your RecyclerView to check if the childView is not null, the listener is not null and the gesture detector consumed the touch event
    7 if above condition holds true, fire the click event
    8 return false from the onInterceptedTouchEvent to give a chance to the childViews of the RecyclerView to process touch events if any.
    9 Add the onItemTouchListener object for our RecyclerView that uses our class created in step 1
     */

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean navDrwOpen;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private NavigationInfoAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        navDrwOpen = Boolean.valueOf(readPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new NavigationInfoAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick( View view, int position ) {
                    Intent hostIntent = new Intent( getActivity(), NavigationDrawerActivity.class);
                    startActivity( hostIntent );
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return layout;
    }

    public static List<NavigationInformation> getData(){
        List<NavigationInformation> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_number1, R.drawable.ic_number1, R.drawable.ic_number1, R.drawable.ic_number1};
        String[] titles = {"Add Food", "Add Friend", "Search", "User"};
        for(int i =0 ; i < titles.length && i < icons.length; i++){
            NavigationInformation current = new NavigationInformation();
            current.inconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    public void setUp( int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar){
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.openNavDrawer, R.string.closeNavDrawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, navDrwOpen + "");
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if(!navDrwOpen && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        public RecyclerTouchListener(Context context, RecyclerView rv, ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp( MotionEvent event){
                    return true;
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if( child != null  && clickListener != null && gestureDetector.onTouchEvent(e) ){
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }
    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    public static void saveToPreferences( Context context, String preferenceName, String preferenceValue  ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readPreferences( Context context, String preferenceName, String preferenceValue  ){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }
}
