package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    MyDatabase myDatabase;
    ArrayList<Contact> contacts;
    MyCustomAdapter myCustomAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatAction);
        listView = findViewById(R.id.listView);
        myDatabase = new MyDatabase(this);
        contacts = myDatabase.getAllContacts();
        myCustomAdapter = new MyCustomAdapter(contacts, this);
        contacts.sort((c1, c2) -> c1.getContact_name().compareToIgnoreCase(c2.getContact_name()));
        listView.setAdapter(myCustomAdapter);

//        for (Contact c : contacts){
//            myDatabase.deleteContact(c);
//        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = (Contact) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
            intent.putExtra("name", contact.getContact_name());
            intent.putExtra("phone", contact.getContact_phone());
            intent.putExtra("id", contact.getId());
            startActivity(intent);
        });

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
            finish();
        });

    }


    private void readContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                myDatabase.addContact(new Contact(name, phoneNumber));
            }
            cursor.close();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts(); // Read contacts if permission is granted
            } else {
                Toast.makeText(this, "Permission denied to read your contacts", Toast.LENGTH_SHORT).show();
            }
        }
    }


}