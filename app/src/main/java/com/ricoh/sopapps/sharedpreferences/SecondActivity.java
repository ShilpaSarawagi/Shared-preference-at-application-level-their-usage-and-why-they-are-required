package com.ricoh.sopapps.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ssarawagi on 11/8/2016.
 */

public class SecondActivity extends Activity{

    private TextView txvName, txvProfession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txvName = (TextView) findViewById(R.id.txvName);
        txvProfession = (TextView) findViewById(R.id.txvProfession);
    }

    public void loadAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences( getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");
        String profession = sharedPreferences.getString("profession", "N/A");

        txvName.setText(name);
        txvProfession.setText(profession);
    }

    public void clearAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences( getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void removeProfessionKey(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences( getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("profession");
        editor.apply();
    }
}
