package com.transendance.sauron;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatsAct extends AppCompatActivity {

    private Toolbar toolbar;
    public static List<ChatItem> chatItems;
    public static LinearLayoutManager layMan;
    public static RecyclerView chatsList;
    public static ChatListAdapter chatListAdapter;

    String[] drs= {"Dr Tshinyiwa", "Dr Lizy", "Dr Kudoho", "Dr Cat", "Dr Who", "Dr Alphy", "Dr Yellow", "Dr Something"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats_view);

        toolbar = findViewById(R.id.chats_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        try {
            setUpChatsList();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpChatsList(){
        chatsList = findViewById(R.id.chats_list);
        layMan = new LinearLayoutManager(this);
        layMan.setOrientation(RecyclerView.VERTICAL);
        chatsList.setLayoutManager(layMan);
        chatItems = new ArrayList<>();
        chatListAdapter = new ChatListAdapter(this, chatItems);
        chatsList.setAdapter(chatListAdapter);

        for (int c=0; c<drs.length; c++){
            ChatItem item = new ChatItem();
            item.setName(drs[c]);
            chatItems.add(item);
        }
        chatListAdapter.notifyDataSetChanged();
    }
}
