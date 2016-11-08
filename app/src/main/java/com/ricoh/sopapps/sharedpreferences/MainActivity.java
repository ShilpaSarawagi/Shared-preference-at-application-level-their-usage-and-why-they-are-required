package com.ricoh.sopapps.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText etName, etProfession;
    private TextView txvName, txvProfession;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etProfession = (EditText) findViewById(R.id.etProfession);

        txvName = (TextView) findViewById(R.id.txvName);
        txvProfession = (TextView) findViewById(R.id.txvProfession);
        pageColorSwitch = (Switch) findViewById(R.id.pageColorSwitch);

        pageLayout = (LinearLayout) findViewById(R.id.pageLayout);

        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("green", isChecked);
                editor.apply();

                pageLayout.setBackgroundColor(isChecked ? Color.GREEN : Color.WHITE);
            }
        });

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("green", false);
        pageColorSwitch.setChecked(isChecked);
    }

    public void saveAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + ".my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", etName.getText().toString());
        editor.putString("profession", etProfession.getText().toString());
        editor.apply(); //editor.commit();
    }

    public void loadAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + ".my_pref_file", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");
        String profession = sharedPreferences.getString("profession", "N/A");
        int profId = sharedPreferences.getInt("prof_id", 0);

        txvName.setText(name);
        txvProfession.setText(profession);

    }

    public void openSecondActivity(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
