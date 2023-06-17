package com.example.easycalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();

        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring((cursorPos));

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }

    public void zeroBTNPush(View view) {
//        updateText("0");
        updateText(getResources().getString(R.string.zeroText));
    }

    public void oneBTNPush(View view) {
//        updateText("1");
        updateText(getResources().getString(R.string.oneText));
    }

    public void twoBTNPush(View view) {
//        updateText("2");
        updateText(getResources().getString(R.string.twoText));
    }

    public void threeBTNPush(View view) {
//        updateText("3");
        updateText(getResources().getString(R.string.threeText));
    }

    public void fourBTNPush(View view) {
//        updateText("4");
        updateText(getResources().getString(R.string.fourText));
    }

    public void fiveBTNPush(View view) {
//        updateText("5");
        updateText(getResources().getString(R.string.fiveText));
    }

    public void sixBTNPush(View view) {
        updateText("6");
//        updateText(getResources().getString(R.string.sixText));
    }

    public void sevenBTNPush(View view) {
        updateText("7");
//        updateText(getResources().getString(R.string.sevenText));
    }

    public void eightBTNPush(View view) {
//        updateText("8");
        updateText(getResources().getString(R.string.eightText));
    }

    public void nineBTNPush(View view) {
        updateText("9");
//        updateText((getResources().getString(R.string.nineText)));
    }

    public void multiplyBTNPush(View view) {
        updateText(getResources().getString(R.string.multiplyText));
    }

    public void divideBTNPush(View view) {
        updateText(getResources().getString(R.string.divideText));
    }

    public void subtractBTNPush(View view) {
        updateText(getResources().getString(R.string.subtractText));
    }

    public void addBTNPush(View view) {
        updateText(getResources().getString(R.string.addText));
    }

    public void clearBTNPush(View view) {
        display.setText("");
        previousCalculation.setText("");
    }

    public void parOpenBTNPush(View view) {
        updateText(getResources().getString(R.string.parenthesesOpenText));
    }

    public void parCloseBTNPush(View view) {
        updateText(getResources().getString(R.string.parenthesesCloseText));
    }

    public void decimalBTNPush(View view) {
        updateText(getResources().getString(R.string.decimalText));
    }

    public void equalBTNPush(View view) {
        String userExp = display.getText().toString();

        previousCalculation.setText(userExp);

        userExp =userExp.replaceAll(getResources().getString(R.string.divideText), "/");
        userExp =userExp.replaceAll(getResources().getString(R.string.multiplyText), "*");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTNPush(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
}