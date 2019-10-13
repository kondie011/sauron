package com.transendance.sauron;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DialogDocListAdapter extends RecyclerView.Adapter<DialogDocHolder> implements OnEndOfListListener {

    Activity activity;
    List<DialogDocItem> dialogDocItems;
    LayoutInflater inflater;
    private static boolean isLoading;
    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;
    private OnEndOfListListener onEndOfListListener;

    public DialogDocListAdapter(Activity activity, final List<DialogDocItem> dialogDocItems)
    {
        this.activity = activity;
        this.dialogDocItems = dialogDocItems;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public void onEndOfList() {
        isLoading = true;
    }

    @NonNull
    @Override
    public DialogDocHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View menuItemView = inflater.inflate(R.layout.chat_item, parent, false);
        DialogDocHolder holder = new DialogDocHolder(menuItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DialogDocHolder holder, int position) {

        DialogDocItem item = dialogDocItems.get(position);

        try{
            holder.docName.setText(item.getName());

        }catch (Exception e){
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dialogDocItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
