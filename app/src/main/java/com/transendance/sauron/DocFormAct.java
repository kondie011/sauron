package com.transendance.sauron;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DocFormAct extends AppCompatActivity {

    private Toolbar toolbar;
    public static Activity activity;
    public static Dialog docDialog;
    String[] drs= {"Dr Tshinyiwa", "Dr Lizy", "Dr Kudoho", "Dr Cat", "Dr Who", "Dr Alphy", "Dr Yellow", "Dr Something"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_form);
        activity = this;

        toolbar = findViewById(R.id.doc_form_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.done_nav:
                docDialog = new Dialog(this.activity);
                docDialog.setContentView(R.layout.doc_choise_dialog);
                docDialog.setCancelable(true);

                RecyclerView docList = docDialog.findViewById(R.id.doc_list);
                LinearLayoutManager layMan = new LinearLayoutManager(activity);
                List<DialogDocItem> dialogDocItems = new ArrayList<>();
                DialogDocListAdapter dialogDocListAdapter = new DialogDocListAdapter(activity, dialogDocItems);
                docList.setLayoutManager(layMan);
                docList.setAdapter(dialogDocListAdapter);

                for (int c=0; c<drs.length; c++){
                    DialogDocItem dialogDocItem = new DialogDocItem();
                    dialogDocItem.setName(drs[c]);

                    dialogDocItems.add(dialogDocItem);
                }
                dialogDocListAdapter.notifyDataSetChanged();
                docDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
