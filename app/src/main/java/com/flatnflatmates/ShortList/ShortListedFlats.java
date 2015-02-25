package com.flatnflatmates.ShortList;

import android.app.Dialog;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flatnflatmates.flatflatmates.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by applect on 20/2/15.
 */
public class ShortListedFlats extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleMap gMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        if(servicesOk()){
            view = inflater.inflate(R.layout.google_maps, container, false);
            if( initMap() ){
               mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .build();
                mLocationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(60 * 1000)        // 10 seconds, in milliseconds
                        .setFastestInterval(10 * 1000); // 1 second, in milliseconds
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initMap();
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    private void handleNewLocation(Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!");
        gMap.addMarker(options);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    public boolean servicesOk(){
        int isAvaliable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());

        if(isAvaliable == ConnectionResult.SUCCESS){
            return true;
        }
        else if ( GooglePlayServicesUtil.isUserRecoverableError(isAvaliable) ){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvaliable, getActivity(), GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(getActivity(),"Can't Resolve the Google Play", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void setUpMap() {
        gMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private boolean initMap(){
        if( gMap == null )
        {
            MapFragment mapFrag = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
            gMap = mapFrag.getMap();
            setUpMap();
        }
        return ( gMap != null );
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation( location );
    }
}
