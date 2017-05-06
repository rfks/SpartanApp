package com.example.rk.spartanapp;

/**
 * Created by rk on 03/04/2017.
 */

public class Applicant {
    private String mName;
    private String mEmail;
    private int mDays;
    private boolean mNewbie;

    public Applicant (String name,String email,int days,boolean newbie){
        mName = name;
        mEmail = email;
        mDays = days;
        mNewbie = newbie;
    }

    public String getName() {return mName;}
    public String getEmail() {return mEmail;}
    public int getDays() {return mDays;}
    public boolean getNewbie() {return mNewbie;}
}
