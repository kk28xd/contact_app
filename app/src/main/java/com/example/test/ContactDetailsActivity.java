package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactDetailsActivity extends AppCompatActivity {

    EditText contact_name;
    EditText contact_phone;
    ImageButton btn_call;
    ImageButton btn_update;
    ImageButton btn_delete;
    ImageButton btn_back;

    MyDatabase myDatabase;
    Intent intent;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        contact_name = findViewById(R.id.contact_name);
        contact_phone = findViewById(R.id.contact_phone);
        btn_call = findViewById(R.id.call_button);
        btn_update = findViewById(R.id.update_button);
        btn_delete = findViewById(R.id.delete_button);
        btn_back = findViewById(R.id.back_button_details);
        myDatabase = new MyDatabase(this);

        //contact_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        intent = getIntent();
        String contactName = intent.getStringExtra("name");
        String contactPhone = intent.getStringExtra("phone");
        id = intent.getLongExtra("id", 0);

        contact_name.setText(contactName);
        contact_phone.setText(contactPhone);

        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(ContactDetailsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn_update.setOnClickListener(v -> {
            String contactName1 = contact_name.getText().toString();
            String contactPhone1 = contact_phone.getText().toString();

            if (contactPhone1.length() != 11) {
                contact_phone.setError("Phone number must be exactly 11 digits");
            } else if (!contactPhone1.matches("\\d{11}")) {
                contact_phone.setError("Phone number must contain only digits");
            } else {
                if (contactName1.length()>0){
                    int y = myDatabase.updateContact(new Contact(id, contactName1, contactPhone1));
                    Toast.makeText(this, y + " updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    contact_name.setError("empty field!");
                }
            }
        });

        btn_delete.setOnClickListener(v -> {
            int y = myDatabase.deleteContact(new Contact(contactName, contactPhone));
            Toast.makeText(this, y + " deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ContactDetailsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn_call.setOnClickListener(v -> {
            makePhoneCall(contactPhone);
        });
    }
    private void makePhoneCall(String phone_num) {
//        StringBuilder phone= new StringBuilder();
//        for (int c = 0; c< phone_num.length();c++){
//            if (c!='-'){
//                phone.append(c);
//            }
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                startCall(String.valueOf(phone_num));
            }
        } else {
            startCall(String.valueOf(phone_num));
        }
    }
//012-235-005
    private void startCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            startActivity(callIntent);
        } catch (SecurityException e) {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

}