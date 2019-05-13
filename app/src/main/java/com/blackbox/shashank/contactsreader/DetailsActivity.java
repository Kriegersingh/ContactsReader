package com.blackbox.shashank.contactsreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailsActivity extends androidx.fragment.app.Fragment     {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.contact_details, container, false);

        return view;
    }


}