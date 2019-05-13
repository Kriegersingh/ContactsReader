package com.blackbox.shashank.contactsreader.Models;

import android.graphics.Bitmap;
import android.net.Uri;

public class ContactsModel {

    public  String name;
    public String number;
    public String id;
    public Bitmap photo;
    public Uri photoUri;


    public ContactsModel(){

    }


    public ContactsModel (String name,String number,String id,Bitmap photo,Uri photoUri){
        this.name =name;
        this.number=number;
        this.id =id;
        this.photo=photo;
        this.photoUri=photoUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }
}
