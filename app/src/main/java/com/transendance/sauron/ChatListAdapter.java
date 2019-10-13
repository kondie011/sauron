package com.transendance.sauron;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatListAdapter  extends RecyclerView.Adapter<ChatHolder> implements OnEndOfListListener {

    Activity activity;
    List<ChatItem> chatItems;
    LayoutInflater inflater;
    private static boolean isLoading;
    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;
    private OnEndOfListListener onEndOfListListener;

    public ChatListAdapter(Activity activity, final List<ChatItem> chatItems)
    {
        this.activity = activity;
        this.chatItems = chatItems;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public void onEndOfList() {
        isLoading = true;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View menuItemView = inflater.inflate(R.layout.chat_item, parent, false);
        ChatHolder holder = new ChatHolder(menuItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

        ChatItem item = chatItems.get(position);

        try{
            holder.docName.setText(item.getName());

        }catch (Exception e){
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
