package com.blackbox.shashank.contactsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Intent intent=getIntent();
        String contactId=intent.getStringExtra("contactId");
        TextView textView= findViewById(R.id.email);
        textView.setText(contactId);

    }



}
