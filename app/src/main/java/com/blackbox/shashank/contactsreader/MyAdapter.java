package com.blackbox.shashank.contactsreader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blackbox.shashank.contactsreader.Models.ContactsModel;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {

    private List<ContactsModel> contactsList;

    public MyAdapter(List<ContactsModel> contactsList){
        this.contactsList=contactsList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_row, viewGroup, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {

        ContactsModel contact=contactsList.get(i);

        myviewHolder.name.setText(contact.getName());
        myviewHolder.number.setText(contact.getNumber());
        myviewHolder.image.setImageBitmap(contact.getPhoto());

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

       TextView name;
       TextView number;
       ImageView image;

       public MyviewHolder(View view){
           super(view);

           name = view.findViewById(R.id.name);
           number =view.findViewById(R.id.number);
           image=view.findViewById(R.id.image);

       }
   }

}
