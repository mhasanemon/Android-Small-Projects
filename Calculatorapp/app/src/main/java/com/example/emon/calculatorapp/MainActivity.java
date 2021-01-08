package com.example.emon.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText operand1;
    private EditText operand2;

    private Button buttonAddition;
    private Button buttonSubstraction;
    private Button buttonMultiplication;
    private Button buttonDivision;
    private Button clear;

    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operand1 = (EditText) findViewById(R.id.editText2);
        operand2 = (EditText) findViewById(R.id.editText3);

        buttonAddition = (Button) findViewById(R.id.add);
        buttonSubstraction = (Button) findViewById(R.id.sub);
        buttonMultiplication = (Button) findViewById(R.id.mul);
        buttonDivision = (Button) findViewById(R.id.div);
        clear = (Button) findViewById(R.id.clr);

        result = (TextView) findViewById(R.id.textView3);

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operand1.getText().length() > 0 && operand2.getText().length() > 0) {
                    double op1 = Double.parseDouble(operand1.getText().toString());
                    double op2 = Double.parseDouble(operand2.getText().toString());

                    double testResult = op1 + op2;
                    result.setText(Double.toString(testResult));
                }
                else {
                    Toast.makeText(MainActivity.this,"Please Enter Numbers.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operand1.getText().length() > 0 && operand2.getText().length() > 0) {
                    double op1 = Double.parseDouble(operand1.getText().toString());
                    double op2 = Double.parseDouble(operand2.getText().toString());

                    double testResult = op1 - op2;
                    result.setText(Double.toString(testResult));
                }
                else {
                    Toast.makeText(MainActivity.this,"Please Enter Numbers.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operand1.getText().length() > 0 && operand2.getText().length() > 0) {
                    double op1 = Double.parseDouble(operand1.getText().toString());
                    double op2 = Double.parseDouble(operand2.getText().toString());

                    double testResult = op1 * op2;
                    result.setText(Double.toString(testResult));
                }
                else {
                    Toast.makeText(MainActivity.this,"Please Enter Numbers.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operand1.getText().length() > 0 && operand2.getText().length() > 0) {
                    double op1 = Double.parseDouble(operand1.getText().toString());
                    double op2 = Double.parseDouble(operand2.getText().toString());

                    double testResult = op1 / op2;
                    result.setText(Double.toString(testResult));
                }
                else {
                    Toast.makeText(MainActivity.this,"Please Enter Numbers.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand1.setText("");
                operand2.setText("");
                result.setText("0.0");
                operand1.requestFocus();
            }
        });
    }
}
