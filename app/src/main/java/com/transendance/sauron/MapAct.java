package com.transendance.sauron;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;

public class MapAct extends AppCompatActivity {


    Dialog fullMapDialog;
    Toolbar toolbar;

    public static Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.full_map_dialog);
        activity = this;

        toolbar = findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

//        openFullMap();
    }
//
//    private void openFullMap() {
//        try{
//            final AndroidXMapFragment mapFragment = (AndroidXMapFragment)
//                    getSupportFragmentManager().findFragmentById(R.id.mapfragment);
//            // initialize the Map Fragment and
//            // retrieve the map that is associated to the fragment
//            mapFragment.init(new OnEngineInitListener() {
//                @Override
//                public void onEngineInitializationCompleted(
//                        OnEngineInitListener.Error error) {
//                    if (error == OnEngineInitListener.Error.NONE) {
//                        // now the map is ready to be used
//                        Map map = mapFragment.getMap();
//                        map.setCenter(new GeoCoordinate(-26.1574353, 28.3151294), Map.Animation.NONE);
//                        // ...
//                    } else {
//                        System.out.println("ERROR: Cannot initialize AndroidXMapFragment");
//                    }
//                }
//            });
//
//        }catch (Exception e){
//            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
//        }
//    }
}
