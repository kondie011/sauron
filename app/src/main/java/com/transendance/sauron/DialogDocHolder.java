package com.transendance.sauron;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DialogDocHolder  extends RecyclerView.ViewHolder {

    TextView docName;

    public DialogDocHolder(View menuItemView){
        super(menuItemView);

        docName = menuItemView.findViewById(R.id.doc_display_name);

        menuItemView.setOnClickListener(openChat);
    }

    private View.OnClickListener openChat = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DocFormAct.docDialog.dismiss();
            DocFormAct.activity.finish();
            Intent toConvoIntent = new Intent(DocFormAct.activity, ConvoAct.class);
            toConvoIntent.putExtra("docName", docName.getText().toString());
            DocFormAct.activity.startActivity(toConvoIntent);
        }
    };
}
