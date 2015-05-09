package com.flatandflatmates.GoogleMaps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.flatandflatmates.HttpClient.ServiceProvider;
import com.flatandflatmates.Interfaces.GetPlaces;
import com.flatandflatmates.JavaObjects.GeometryLocation;
import com.flatandflatmates.JavaObjects.PlaceDetail;
import com.flatandflatmates.JavaObjects.PlaceDetailsResult;
import com.flatandflatmates.JavaObjects.Places;
import com.flatandflatmates.JavaObjects.Prediction;
import com.flatandflatmates.R;
import com.flatandflatmates.host.HostPropertyFragment;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by applect on 20/2/15.
 */
public class GoogleMapsActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleMap gMap;
    private String myKey = "AIzaSyBEAbh1uFPpWIG-KmBU2Y5Fyck4xcszx_0";
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Button searchButton;
    AutoCompleteTextView atvPlaces;
    private int spaceType;
    private static final String BASE_DOMAIN = "https://maps.googleapis.com/maps/api/place";
    HashMap<String, String> hashMapSpaceDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (servicesOk()) {
            setContentView(R.layout.google_maps);
            if (initMap()) {

                Intent intent = getIntent();
                if (HostPropertyFragment.SPACE_INTENT.equals(intent.getAction())) {
                    hashMapSpaceDetails = (HashMap<String, String>) intent.getSerializableExtra("spaceDetails");
                    spaceType = 1;
                }

                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .build();
                mLocationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(60 * 1000)        // 60 seconds, in milliseconds
                        .setFastestInterval(10 * 1000); // 10 second, in milliseconds

                atvPlaces = (AutoCompleteTextView) findViewById(R.id.atv_places);
                atvPlaces.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        getPlacesData(s.toString());
                        //placesTask = new PlacesTask();
                        //placesTask.execute(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // TODO Auto-generated method stub
                    }
                });
            }
        }
    }

    public boolean servicesOk() {
        int isAvaliable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvaliable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvaliable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvaliable, this, GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't Resolve the Google Play", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private boolean initMap() {
        if (gMap == null) {
            MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            gMap = mapFrag.getMap();
            setUpMap();
        }
        return (gMap != null);
    }

    private void setUpMap() {
        //gMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void getPlacesData(String input){

        try {
            input = URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // Sensor enabled
        String sensor = "false";

        // place type to be searched
        String types = "geocode";

        // Building the parameters to the web service
        setupHttpClientGetPlaces(sensor, types, myKey, input);

    }

    private void setupHttpClientGetPlaces(String sensor, String type, String key, String input ){
        // Create a very simple REST adapter which points the GitHub API endpoint.
        GetPlaces client = ServiceProvider.createService(GetPlaces.class, BASE_DOMAIN);

        // Fetch and print a list of the contributors to this library.
        List<Places> places = null;
        try {
            client.listPlaces(sensor, type, key, input, new Callback<Places>() {
                @Override
                public void success(Places places, Response response) {

                    final List<Prediction> predictions = places.getPredictions();
                    ArrayAdapter<Prediction> adapter = new ArrayAdapter<Prediction>(GoogleMapsActivity.this,android.R.layout.simple_list_item_1, predictions);
                    atvPlaces.setAdapter(adapter);

                    atvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {

                            Prediction clickedPrediction = predictions.get(pos);
                            getPlaceDetailData(clickedPrediction.getReference());
                        };
                    });
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("Error in Error", error.toString());
                }
            });
        } catch (RetrofitError e){
            Log.d("Error Here", e.toString());
        }
    }

    private void getPlaceDetailData(String input){

        try {
            input = URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // reference of place
        String reference = input;

        // Sensor enabled
        String sensor = "false";

        // Building the parameters to the web service
        setupHttpClientGetPlaceDetails(sensor, myKey, reference);

    }

    private void setupHttpClientGetPlaceDetails(String sensor, String key, String input ){
        // Create a very simple REST adapter which points the GitHub API endpoint.
        GetPlaces client = ServiceProvider.createService(GetPlaces.class, BASE_DOMAIN);

        // Fetch and print a list of the contributors to this library.
        try {
            client.getPlaceDetails(sensor, key, input, new Callback<PlaceDetail>() {
                @Override
                public void success(PlaceDetail placeDetails, Response response) {

                    PlaceDetailsResult placeDetailResult = placeDetails.getResult();
                    GeometryLocation geometryLocation = placeDetailResult.getGeometry();
                    com.flatandflatmates.JavaObjects.Location location = geometryLocation.getLocation();

                    showLocations( location );
                    Log.d("TAGBBB", String.valueOf(location.getLat()));
                    Log.d("TAGBBC", String.valueOf(location.getLng()));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("Error in Error", error.toString());
                }
            });
        } catch (RetrofitError e){
            Log.d("Error Here", e.toString());
        }
    }

    private void showLocations(com.flatandflatmates.JavaObjects.Location location){
        MarkerOptions markerOptions = null;
        LatLng position = null;
        gMap.clear();

        markerOptions = new MarkerOptions();
        position = new LatLng(location.getLat(),location.getLng());
        markerOptions.position(position);
        markerOptions.title("I am Here");
        gMap.addMarker(markerOptions);

        if(position!=null){
            CameraUpdate cameraPosition = CameraUpdateFactory.newLatLng(position);
            gMap.animateCamera(cameraPosition);
        }
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

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
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
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
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

        handleNewLocation(location);
    }

    //Handles the new Location
    private void handleNewLocation(Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //Add marker to the new Location
        MarkerOptions options = new MarkerOptions().position(latLng).title("I am here!");
        gMap.addMarker(options);
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}
