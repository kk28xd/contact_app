package com.example.test;

public class Contact {
    private long id;
    private String contact_name;
    private String contact_phone;

    public Contact(long id, String fruit_name , String contact_phone) {
        this.id = id;
        this.contact_name = fruit_name;
        this.contact_phone = contact_phone;
    }

    public Contact(String contact_name, String contact_phone) {
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}
