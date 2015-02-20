package com.flatnflatmates.flatflatmates;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by applect on 20/2/15.
 */
public class HostingOptions extends Fragment {

    private RecyclerView recyclerView;
    private HostOptionsInfoAdapter adapter;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.hosting_options, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.hostingOptions);
        adapter = new HostOptionsInfoAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick( View view, int position ) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return layout;
    }

    public static List<HostOptionsInformation> getData(){
        List<HostOptionsInformation> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_number1, R.drawable.ic_number1, R.drawable.ic_number1};
        String[] titles = { "Flat", "Flat Mate", "PG" };
        for(int i =0 ; i < titles.length && i < icons.length; i++){
            HostOptionsInformation current = new HostOptionsInformation();
            current.inconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
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
}
