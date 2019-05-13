package com.blackbox.shashank.contactsreader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.blackbox.shashank.contactsreader.Models.ContactsModel;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 3000;

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<ContactsModel> list;
    ListView listView;
    SimpleCursorAdapter simpleCursorAdapter;
    String name,phonenumber;
    ArrayList<String> contactList;
    ArrayList<String> contactId;
    String id;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.list);
        contactList=new ArrayList<>();
        contactId =new ArrayList<>();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {

            get();

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String contact_id=contactId.get(i).toString();

                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("contactId",contact_id);
                startActivity(intent);
            }
        });

//        String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_URI, ContactsContract.CommonDataKinds.Phone._ID,};
////
//        int[] to = {android.R.id.text1, android.R.id.text2};

//        recyclerView = findViewById(R.id.recycler_view);
//
//
//        myAdapter = new MyAdapter(list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(myAdapter);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    get();

                } else {

                    Toast.makeText(this, "Allow to see contacts", Toast.LENGTH_SHORT);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    get();
                }
                return;
            }

            default:
                // other 'case' lines to check for other
                // permissions this app might request
        }
    }


    public void get(){

        Cursor cursor =getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            id=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY));

            contactList.add(name + " "  + ":" + " " + phonenumber);
            contactId.add(id);

        }

        cursor.close();


        arrayAdapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_2,android.R.id.text2,contactList);

//        String[] from={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID};
//
//        int [] to ={android.R.id.text1,android.R.id.text2};
//        simpleCursorAdapter =new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to);
//
//        listView.setAdapter(simpleCursorAdapter);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


//            list = new ArrayList<>();
//            ContentResolver contentResolver = ctx.getContentResolver();
//            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//            if (cursor.getCount() > 0) {
//                while (cursor.moveToNext()) {
//                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                        Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
//                        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(ctx.getContentResolver(),
//                                ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id)));
//
//                        Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id));
//                        Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
//
//                        Bitmap photo = null;
//                        if (inputStream != null) {
//                            photo = BitmapFactory.decodeStream(inputStream);
//                        }
//                        while (cursorInfo.moveToNext()) {
//                            ContactsModel info = new ContactsModel();
//                            info.id = id;
//                            info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                            info.number = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                            info.photo = photo;
//                            info.photoUri= pURI;
//                            list.add(info);
//                        }
//
//                        cursorInfo.close();
//                    }
//                }
//                cursor.close();
//            }
//            return list;




    }






}
