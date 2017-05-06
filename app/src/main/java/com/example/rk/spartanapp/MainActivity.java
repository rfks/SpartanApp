package com.example.rk.spartanapp;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;



public class MainActivity extends AppCompatActivity {
    /*store the applicants detials in an ArrayList*/
    ArrayList<Applicant> Applicants = new ArrayList<Applicant>();
    private RadioGroup radioNewGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*starting the animation here*/
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable frameAnimation = (AnimationDrawable) (findViewById(R.id.spartan_image)).getBackground();
        if(hasFocus) { frameAnimation.start(); } else { frameAnimation.stop(); }
    }

    /*confirm availability*/
    public void confirm(View view) {
        setContentView(R.layout.activity_survey);
        radioNewGroup = (RadioGroup) findViewById(R.id.radiogroup1);
    }

    /*submit details*/
    public void submit(View view) {
        /*check if name is not empty*/
        if (((EditText) findViewById(R.id.name)).getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter your name!", Toast.LENGTH_SHORT).show();
            return;
        }
        /*check if email is not empty*/
        if (((EditText) findViewById(R.id.email)).getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        /*check if availability was chosen or not */
        if (
                !(((CheckBox) findViewById(R.id.mon)).isChecked() ||
                        ((CheckBox) findViewById(R.id.tue)).isChecked() ||
                        ((CheckBox) findViewById(R.id.wed)).isChecked() ||
                        ((CheckBox) findViewById(R.id.thu)).isChecked() ||
                        ((CheckBox) findViewById(R.id.fri)).isChecked() ||
                        ((CheckBox) findViewById(R.id.sat)).isChecked() ||
                        ((CheckBox) findViewById(R.id.sun)).isChecked())

                ) {
            Toast.makeText(getApplicationContext(), "Please pick at least one day!", Toast.LENGTH_SHORT).show();
            return;
        }
        /*add the new details to the ArrayList*/
        Applicants.add(new Applicant(
                ((EditText) findViewById(R.id.name)).getText().toString(),
                ((EditText) findViewById(R.id.email)).getText().toString(),
                (((CheckBox) findViewById(R.id.mon)).isChecked() ? 1 : 0) +
                        (((CheckBox) findViewById(R.id.tue)).isChecked() ? 1 : 0) * 2 +
                        (((CheckBox) findViewById(R.id.wed)).isChecked() ? 1 : 0) * 4 +
                        (((CheckBox) findViewById(R.id.thu)).isChecked() ? 1 : 0) * 8 +
                        (((CheckBox) findViewById(R.id.fri)).isChecked() ? 1 : 0) * 16 +
                        (((CheckBox) findViewById(R.id.sat)).isChecked() ? 1 : 0) * 32 +
                        (((CheckBox) findViewById(R.id.sun)).isChecked() ? 1 : 0) * 64,
                ((RadioButton) findViewById(R.id.no)).isChecked()));
        Toast.makeText(getApplicationContext(), "Thanks, you are the " + Applicants.size() + ". who responded.", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);
    }

    /*back to the main screen*/
    public void cancel(View view) {
        setContentView(R.layout.activity_main);
    }

    /*show statistics of the applicants so far*/
    public void stats(View view) {
        int mon = 0, tue = 0, wed = 0, thu = 0, fri = 0, sat = 0, sun = 0, newbie = 0;
        if (Applicants.size() == 0) {
            Toast.makeText(getApplicationContext(), "No applicants yet.", Toast.LENGTH_LONG).show();
        } else {
            for (Iterator it = Applicants.iterator(); it.hasNext(); ) {
                Applicant applicant = (Applicant) it.next();
                if ((applicant.getDays() & 1) == 1) {
                    mon++;
                }
                if ((applicant.getDays() & 2) == 2) {
                    tue++;
                }
                if ((applicant.getDays() & 4) == 4) {
                    wed++;
                }
                if ((applicant.getDays() & 8) == 8) {
                    thu++;
                }
                if ((applicant.getDays() & 16) == 16) {
                    fri++;
                }
                if ((applicant.getDays() & 32) == 32) {
                    sat++;
                }
                if ((applicant.getDays() & 64) == 64) {
                    sun++;
                }
                if (applicant.getNewbie()) {
                    newbie++;
                }
            }
            Toast.makeText(getApplicationContext(), "All applicants: " + Applicants.size() + "\nNewbies: " + newbie + "\nMon: " + mon + "\nTue: " + tue + "\nWed: " + wed + "\nThu: " + thu + "\nFri: " + fri + "\nSat: " + sat + "\nSun: " + sun, Toast.LENGTH_LONG).show();
        }
    }
}
