package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    EditText add_name;
    EditText add_phone;
    ImageButton back_button;
    Button btn_save;
    String contact_name;
    String contact_phone;
    ArrayList<Contact> contacts;
    MyDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        add_name = findViewById(R.id.add_name);
        add_phone = findViewById(R.id.add_phone);
        btn_save = findViewById(R.id.button_save);
        back_button = findViewById(R.id.back_button_add);
        myDatabase = new MyDatabase(this);
        contacts = myDatabase.getAllContacts();

        add_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        back_button.setOnClickListener(v -> {
            Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn_save.setOnClickListener(v -> {
            contact_name = add_name.getText().toString();
            contact_phone = add_phone.getText().toString();

            if (contact_phone.length() != 11) {
                add_phone.setError("Phone number must be exactly 11 digits");
            } else if (!contact_phone.matches("\\d{11}")) {
                add_phone.setError("Phone number must contain only digits");
            } else {
                if (contact_name.length() > 0) {
                    boolean isExist = false;
                    for (Contact c : contacts) {
                        if (c.getContact_name().equals(contact_name) && c.getContact_phone().equals(contact_phone)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        Toast.makeText(this, "already exist!", Toast.LENGTH_SHORT).show();
                    } else {
                        long id = myDatabase.addContact(new Contact(contact_name, contact_phone));
                        Toast.makeText(this, contact_name + " saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddContactActivity.this, ContactDetailsActivity.class);
                        intent.putExtra("name", contact_name);
                        intent.putExtra("phone", contact_phone);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    add_name.setError("empty field!");
                }
            }
        });
    }
}