package com.transendance.sauron;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ChatHolder extends RecyclerView.ViewHolder {

    TextView docName;

    public ChatHolder(View menuItemView){
        super(menuItemView);

        docName = menuItemView.findViewById(R.id.doc_display_name);

        menuItemView.setOnClickListener(openChat);
    }

    private View.OnClickListener openChat = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent toConvoIntent = new Intent(MainActivity.activity, ConvoAct.class);
            toConvoIntent.putExtra("docName", docName.getText().toString());
            MainActivity.activity.startActivity(toConvoIntent);
        }
    };
}
