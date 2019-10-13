package com.transendance.sauron;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.snackbar.Snackbar;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Activity activity;
    public static List<DiseaseItem> diseaseItems;
    public static LinearLayoutManager layMan;
    public static RecyclerView diseaseList;
    public static DiseaseListAdapter diseaseListAdapter;
    private Button emergencyButt, contactDocButt;
    LocationHelper locationHelper;
    Button searchButt;
    EditText searchEdit;
    private ImageView tb;
    String[] symptoms = {"Headache/shivering", "Burning sensation in chest", "Vision problems", "Frequent Urination", "Runny Stomach"};

    String[] diseases = {"Cold" ,"Heart Burn", "High Blood", "Diabetes", "Diarrhea"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        locationHelper = new LocationHelper(activity);
        locationHelper.checkpermission();

        setUpDiseaseList();


        emergencyButt = findViewById(R.id.emergency_butt);
        contactDocButt = findViewById(R.id.contact_doc);
        tb = findViewById(R.id.tb);
        searchButt = findViewById(R.id.search_butt);
        searchEdit = findViewById(R.id.search_text);

        emergencyButt.setOnClickListener(alertAuthorities);
        contactDocButt.setOnClickListener(contactDoc);
        searchButt.setOnClickListener(search);
        tb.setOnClickListener(openMap);
    }

    private View.OnClickListener search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diseaseItems.clear();
            String txt = searchEdit.getText().toString();
            for (int c=0; c<diseases.length; c++){
                String[] splt = txt.split(" ");
                for (int i=0; i<splt.length; i++) {
                    if (diseases[c].toLowerCase().contains(splt[i]) || symptoms[c].toLowerCase().contains(splt[i])) {
                        DiseaseItem item = new DiseaseItem();
                        item.setName(diseases[c]);
                        item.setSymptoms(symptoms[c]);
                        diseaseItems.add(item);
                    }
                }
            }
            diseaseListAdapter.notifyDataSetChanged();
        }
    };

    private View.OnClickListener openMap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toMapIntent = new Intent(activity, MapAct.class);
            startActivity(toMapIntent);
        }
    };

    private View.OnClickListener contactDoc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(activity).setCancelable(false).setMessage("Do you want to add new symptoms?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent toDocFormIntent = new Intent(activity, DocFormAct.class);
                            startActivity(toDocFormIntent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent toChatsIntent = new Intent(activity, ChatsAct.class);
                            startActivity(toChatsIntent);
                        }
                    }).show();
        }
    };

    private View.OnClickListener alertAuthorities = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(activity).setCancelable(false).setMessage("Are you sure you want an ambulance?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new SendSms().execute("Drey needs an ambulance at 39 Rissik, Johannesburg");
                            showDoneAlert();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        }
    };

    private void showDoneAlert(){
        new AlertDialog.Builder(activity).setCancelable(false).setMessage("Your location has been shared with EMS. You should expect a call")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void setUpDiseaseList(){
        diseaseList = findViewById(R.id.disease_list);
        layMan = new LinearLayoutManager(activity);
        layMan.setOrientation(RecyclerView.VERTICAL);
        diseaseList.setLayoutManager(layMan);
        diseaseItems = new ArrayList<>();
        diseaseListAdapter = new DiseaseListAdapter(activity, diseaseItems);
        diseaseList.setAdapter(diseaseListAdapter);

        for (int c=0; c<diseases.length; c++){
            DiseaseItem item = new DiseaseItem();
            item.setName(diseases[c]);
            item.setSymptoms(symptoms[c]);
            diseaseItems.add(item);
        }
        diseaseListAdapter.notifyDataSetChanged();
    }
}
