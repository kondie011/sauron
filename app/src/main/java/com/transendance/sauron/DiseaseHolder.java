package com.transendance.sauron;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DiseaseHolder extends RecyclerView.ViewHolder {

    TextView diseaseName, diseaseSymptoms;

    public DiseaseHolder(View menuItemView){
        super(menuItemView);

        diseaseName = menuItemView.findViewById(R.id.disease_name);
        diseaseSymptoms = menuItemView.findViewById(R.id.disease_symptom);

        menuItemView.setOnClickListener(openOption);
    }

    private View.OnClickListener openOption = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent toTipsIntent = new Intent(MainActivity.activity, TipLay.class);
            MainActivity.activity.startActivity(toTipsIntent);
        }
    };
}
