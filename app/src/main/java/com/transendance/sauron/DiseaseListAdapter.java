package com.transendance.sauron;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiseaseListAdapter extends RecyclerView.Adapter<DiseaseHolder> implements OnEndOfListListener {

    Activity activity;
    List<DiseaseItem> diseaseItems;
    LayoutInflater inflater;
    private static boolean isLoading;
    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;
    private OnEndOfListListener onEndOfListListener;

    public DiseaseListAdapter(Activity activity, final List<DiseaseItem> diseaseItems)
    {
        this.activity = activity;
        this.diseaseItems = diseaseItems;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public void onEndOfList() {
        isLoading = true;
    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View menuItemView = inflater.inflate(R.layout.disease_item, parent, false);
        DiseaseHolder holder = new DiseaseHolder(menuItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {

        DiseaseItem item = diseaseItems.get(position);

        try{
            holder.diseaseName.setText(item.getName());
            holder.diseaseSymptoms.setText(item.getSymptoms());

        }catch (Exception e){
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return diseaseItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
