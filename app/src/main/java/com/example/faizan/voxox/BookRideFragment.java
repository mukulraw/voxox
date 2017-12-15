package com.example.faizan.voxox;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import github.nisrulz.easydeviceinfo.base.EasyLocationMod;


public class BookRideFragment extends Fragment {
    private SupportMapFragment mSupportMapFragment;
    private TextView searchBar;
    ImageView imageView;
    RelativeLayout searchBarMain, carLayout, confirmLayout;
    LinearLayout cabSelection, cabConfirm;
    Button continueBtn, continuebtnfirst;

    public static BookRideFragment newInstance() {
        BookRideFragment fragment = new BookRideFragment();
        return fragment;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_book_ride, container, false);
        searchBarMain = (RelativeLayout) view.findViewById(R.id.searchBarMain);
        carLayout = (RelativeLayout) view.findViewById(R.id.carLayout);
        cabConfirm = (LinearLayout) view.findViewById(R.id.cabConfirm);
        cabSelection = (LinearLayout) view.findViewById(R.id.cabSelection);
        continueBtn = (Button) view.findViewById(R.id.continuebtn);
        confirmLayout = (RelativeLayout) view.findViewById(R.id.confirmLayout);
        continuebtnfirst = (Button) view.findViewById(R.id.continuebtnFirst);

        continuebtnfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carLayout.setVisibility(View.VISIBLE);
                searchBarMain.setVisibility(View.GONE);
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Objects.equals(continueBtn.getText().toString(), "Confirm"))
                {

                    carLayout.setVisibility(View.GONE);
                    confirmLayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    cabSelection.setVisibility(View.GONE);
                    cabConfirm.setVisibility(View.VISIBLE);
                    continueBtn.setText("Confirm");
                }


            }
        });

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.bookRideFragment);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.bookRideFragment, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);

                        EasyLocationMod easyLocationMod = new EasyLocationMod(getContext());

                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }

                        double[] l = easyLocationMod.getLatLong();
                        String lat = String.valueOf(l[0]);
                        String lon = String.valueOf(l[1]);

                        LatLng myLocation = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        try{
                            List<Address> listAdresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon),1);
                            if(null != listAdresses && listAdresses.size() > 0 ){
                                String address = listAdresses.get(0).getAddressLine(0);
                                String state = listAdresses.get(0).getAdminArea();
                                String country = listAdresses.get(0).getCountryName();
                                String subLocality = listAdresses.get(0).getSubLocality();

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                .title(""+subLocality+", "+ state+", "+ country +"")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        }

                        googleMap.setMyLocationEnabled(false);
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);



                    }

                }

            });



        }


        return view;

    }



}