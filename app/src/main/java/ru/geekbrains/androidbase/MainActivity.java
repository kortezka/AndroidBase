package ru.geekbrains.androidbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Constants {
    TextView result;
    TextView numbers;
    TextView history;
    CharSequence value1;
    CharSequence value2;
    Operation operat;
    Calculator calculator = new Calculator();


    public static final CharSequence ZERO = "ZERO";
    public static final String HISTORY = "HISTORY";
    public static final String NUMBERS = "NUMBERS";
    public static final String RESULT = "RESULT";
    public static final int THEME_DARK = 0;
    public static final int REQUEST_CODE = 0;
    public static final int THEME_LIGHT = 1;
    public static final String PREFS_KEY = "KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(intentsToStyle(prefsGet()));
        setContentView(R.layout.activity_main);
        numbers = findViewById(R.id.entering_numbers);
        result = findViewById(R.id.text_equation);
        history = findViewById(R.id.text_history);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);

        Button buttonAddition = findViewById(R.id.button_addition);
        Button buttonMultiplication = findViewById(R.id.button_multiplication);
        Button buttonSubstraction = findViewById(R.id.button_subtraction);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonDot = findViewById(R.id.button_dot);
        Button buttonClear = findViewById(R.id.button_clear);
        Button buttonEquation = findViewById(R.id.button_equation);

        button1.setOnClickListener(v -> init(button1));
        button2.setOnClickListener(v -> init(button2));
        button3.setOnClickListener(v -> init(button3));
        button4.setOnClickListener(v -> init(button2));
        button5.setOnClickListener(v -> init(button5));
        button6.setOnClickListener(v -> init(button6));
        button7.setOnClickListener(v -> init(button7));
        button8.setOnClickListener(v -> init(button8));
        button9.setOnClickListener(v -> init(button9));
        button0.setOnClickListener(v -> init(button0));


        buttonDot.setOnClickListener(v -> {
            if (!(numbers.getText().charAt(numbers.length() - 1) == '.')) {
                numbers.setText(numbers.getText() + ".");
            }
        });

        buttonMultiplication.setOnClickListener(v -> operationButtonInit(buttonMultiplication, Operation.MULTIPLICATION));
        buttonSubstraction.setOnClickListener(v -> operationButtonInit(buttonSubstraction, Operation.SUBTRACTION));
        buttonAddition.setOnClickListener(v -> operationButtonInit(buttonAddition, Operation.ADDITION));
        buttonDivide.setOnClickListener(v -> operationButtonInit(buttonDivide, Operation.DIVIDE));
        buttonClear.setOnClickListener(v -> {
            numbers.setText("");
            history.setText("");
            result.setText("");
        });
        buttonEquation.setOnClickListener(v -> {

            value2 = numbers.getText();
            if (value1 != "" && value2 != "") {

                numbers.setText("");
                if ((calculator.equation(value1, value2, operat) == ZERO)) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "неть, на ноль нельзя", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    result.setText(calculator.equation(value1, value2, operat));
                    history.setText(value1 + operat.getTitle() + value2 + "=" + result.getText());
                }
            }
        });

        Button buttonTheme = findViewById(R.id.button_theme);
        buttonTheme.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, second_activity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

    }


    private void prefsPut(int code) {
        SharedPreferences preferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        preferences.edit().putInt(PREFS_KEY, code).apply();

    }

    private int prefsGet() {
        SharedPreferences preferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return preferences.getInt(PREFS_KEY, THEME_LIGHT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            prefsPut(data.getIntExtra(THEME_SETTINGS, 0));
            recreate();
        }
    }

    private int intentsToStyle(int intent) {
        switch (intent) {
            case THEME_DARK:
                return R.style.Theme_Hw1;
            default:
                return R.style.designMan1;
        }
    }

    private void operationButtonInit(Button button, Operation operation) {
        if (!(numbers.getText() == "")) {
            value1 = numbers.getText();
            history.setText(value1.toString() + button.getText());
            operat = operation;
            numbers.setText("");
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(HISTORY, history.getText());
        outState.putCharSequence(RESULT, result.getText());
        outState.putCharSequence(NUMBERS, numbers.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        history.setText(savedInstanceState.getCharSequence(HISTORY));
        result.setText(savedInstanceState.getCharSequence(RESULT));
        numbers.setText(savedInstanceState.getCharSequence(NUMBERS));
    }


    public void init(Button button) {

        numbers.setText(numbers.getText() + "" + button.getText());
    }

}




