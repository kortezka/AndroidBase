package ru.geekbrains.androidbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;

public class second_activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, Constants {
    SwitchCompat themeswitch;

    public static final String PREFS_KEY = "KEY";
    public static final int THEME_DARK = 0;
    public static final int THEME_LIGHT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(prefsToStyle(prefsGet()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        themeswitch = findViewById(R.id.themeSwitch);
        if (!(themeswitch.isChecked())) {
            themeswitch.setOnCheckedChangeListener(this);
            themeswitch.setOnClickListener(v -> recreate());
        }
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            Intent themeIntent = new Intent();
            themeIntent.putExtra(THEME_SETTINGS, prefsGet());
            setResult(RESULT_OK, themeIntent);
            finish();
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            prefsPut(THEME_DARK);
        } else {
            prefsPut(THEME_LIGHT);
        }

    }

    private void prefsPut(int code) {
        SharedPreferences preferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        preferences.edit().putInt(PREFS_KEY, code).apply();

    }

    private int prefsGet() {
        SharedPreferences preferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return preferences.getInt(PREFS_KEY, THEME_LIGHT);

    }

    private int prefsToStyle(int pref) {
        switch (pref) {
            case THEME_DARK:

                return R.style.Theme_Hw1;
            default:
                return R.style.designMan1;
        }

    }
}