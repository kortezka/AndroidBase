package ru.geekbrains.androidbase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    TextView equation;
    TextView numbers;
    TextView history;
    CharSequence value1;
    CharSequence value2;
    char operation;
    Calculator calculator = new Calculator();
    public static final CharSequence ZERO = "ZERO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbers = findViewById(R.id.entering_numbers);
        equation = findViewById(R.id.text_equation);
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
        buttonAddition.setOnClickListener(v -> {
            value1 = numbers.getText();
            history.setText(value1 + "+");
            operation = '+';
            numbers.setText("");
        });
        buttonClear.setOnClickListener(v -> {
            numbers.setText("");
            history.setText("");
            equation.setText("");
        });

        buttonDivide.setOnClickListener(v -> {
            value1 = numbers.getText();
            history.setText(value1 + "/");
            operation = '/';
            numbers.setText("");
        });

        buttonEquation.setOnClickListener(v -> {

            value2 = numbers.getText();
            if (value1 != "" && value2 != "") {

                numbers.setText("");
                if ((calculator.equation(value1, value2, operation) == ZERO)) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "неть, на ноль нельзя", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    equation.setText(calculator.equation(value1, value2, operation));
                    history.setText(value1 + Character.toString(operation) + value2 + "=" + equation.getText());
                    equation.setText("");
                }
            }
        });


    }

    public void init(Button button) {

        numbers.setText(numbers.getText() + "" + button.getText());
    }
}
