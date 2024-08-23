package com.example.test;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;
import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {

    private ArrayList<Contact> contactList;
    private Context context;

    public MyCustomAdapter(ArrayList<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }


    public void addContact(Contact contact) {
        contactList.add(contact); // names is the arrayList<> object name
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contactList = contacts;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = (Contact) getItem(position);
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null, false);
        }
        TextView tv = v.findViewById(android.R.id.text1);
        String contact_name = contact.getContact_name();
        tv.setText(contact_name);
        return v;
    }

}
