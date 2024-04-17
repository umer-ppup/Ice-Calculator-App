package com.atrule.calculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.atrule.calculator.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //region variable declarations
    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    private Button btnAc, btnPlusMinus, btnDivide, btnMultiply, btnPlus, btnMinus, btnEqual, btnPercent, btnDot, btnC;
    private HorizontalScrollView hScrollView;

    private TextView tvResult, tvDisplay;

    private double firstNumber, secondNumber, result;
    private boolean isDotPressed = false;
    private boolean op = false;

    private ConstraintLayout constraintLayout;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //region content view setting
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //make translucent statusBar on kitkat devices
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //endregion

        //region initializations
        firstNumber = 0;
        secondNumber = 0;
        result = 0;
        //endregion

        //region finding view by ids
        constraintLayout = findViewById(R.id.constraintLayout);
        tvResult = findViewById(R.id.tvResult);
        tvResult.setText("");

        tvDisplay = findViewById(R.id.tvDisplay);
        tvDisplay.setText("");
        hScrollView = findViewById(R.id.hScrollView);

        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);

        btnAc = findViewById(R.id.btnAc);
        btnC = findViewById(R.id.btnC);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnEqual = findViewById(R.id.btnEqual);
        btnPercent = findViewById(R.id.btnPercent);
        btnDot = findViewById(R.id.btnDot);

        ToggleButton btnToggle = findViewById(R.id.btnToggle);
        //endregion

        //region setting onClickListener
        btnZero.setOnClickListener(MainActivity.this);
        btnOne.setOnClickListener(MainActivity.this);
        btnTwo.setOnClickListener(MainActivity.this);
        btnThree.setOnClickListener(MainActivity.this);
        btnFour.setOnClickListener(MainActivity.this);
        btnFive.setOnClickListener(MainActivity.this);
        btnSix.setOnClickListener(MainActivity.this);
        btnSeven.setOnClickListener(MainActivity.this);
        btnEight.setOnClickListener(MainActivity.this);
        btnNine.setOnClickListener(MainActivity.this);

        btnAc.setOnClickListener(MainActivity.this);
        btnC.setOnClickListener(MainActivity.this);
        btnPlusMinus.setOnClickListener(MainActivity.this);
        btnDivide.setOnClickListener(MainActivity.this);
        btnMultiply.setOnClickListener(MainActivity.this);
        btnPlus.setOnClickListener(MainActivity.this);
        btnMinus.setOnClickListener(MainActivity.this);
        btnEqual.setOnClickListener(MainActivity.this);
        btnPercent.setOnClickListener(MainActivity.this);
        btnDot.setOnClickListener(MainActivity.this);
        //endregion

        //region Check Change Listener for theme
        btnToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                blueTheme();
            } else {
                orangeTheme();
            }
        });
        //endregion

        //region Text Change Listener
        tvDisplay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hScrollView.post(() -> hScrollView.fullScroll(View.FOCUS_RIGHT));
            }
        });
        //endregion
    }

    //region onClickListener for all buttons
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btnZero:
                //region code when zero is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("0");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "0");
                    } else {
                        tvResult.setText("0");
                        op = false;
                    }
                } else {
                    if (!tvResult.getText().toString().equals("0")) {
                        tvResult.setText(tvResult.getText().toString() + "0");
                    }
                }
                break;
                //endregion
            case R.id.btnOne:
                //region code when one is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("1");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "1");
                    } else {
                        tvResult.setText("1");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "1");
                }
                break;
                //endregion
            case R.id.btnTwo:
                //region code when two is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("2");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "2");
                    } else {
                        tvResult.setText("2");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "2");
                }
                break;
                //endregion
            case R.id.btnThree:
                //region code when three is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("3");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "3");
                    } else {
                        tvResult.setText("3");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "3");
                }
                break;
                //endregion
            case R.id.btnFour:
                //region code when four is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("4");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "4");
                    } else {
                        tvResult.setText("4");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "4");
                }
                break;
                //endregion
            case R.id.btnFive:
                //region code when five is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("5");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "5");
                    } else {
                        tvResult.setText("5");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "5");
                }
                break;
                //endregion
            case R.id.btnSix:
                //region code when six is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("6");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "6");
                    } else {
                        tvResult.setText("6");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "6");
                }
                break;
                //endregion
            case R.id.btnSeven:
                //region code when seven is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("7");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "7");
                    } else {
                        tvResult.setText("7");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "7");
                }
                break;
                //endregion
            case R.id.btnEight:
                //region code when eight is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("8");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "8");
                    } else {
                        tvResult.setText("8");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "8");
                }
                break;
                //endregion
            case R.id.btnNine:
                //region code when nine is pressed
                if (op) {
                    if (tvDisplay.getText().toString().endsWith("=")) {
                        tvDisplay.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        op = false;
                        tvResult.setText("9");
                    } else if (isDotPressed) {
                        tvResult.setText(tvResult.getText().toString() + "9");
                    } else {
                        tvResult.setText("9");
                        op = false;
                    }
                } else {
                    tvResult.setText(tvResult.getText().toString() + "9");
                }
                break;
                //endregion
            case R.id.btnDot:
                //region code when dot is pressed
                if (!isDotPressed) {
                    if (op) {
                        if (!tvDisplay.getText().toString().endsWith("=")) {
                            tvResult.setText("");
                            if (tvResult.getText().toString().equals("")) {
                                tvResult.setText("0.");
                            } else {
                                tvResult.setText(tvResult.getText().toString() + ".");
                            }
                            isDotPressed = true;
                        } else {
                            tvResult.setText("0.");
                            tvDisplay.setText("");
                            firstNumber = 0;
                            secondNumber = 0;
                            result = 0;
                        }
                        op = false;
                    } else {
                        if (tvResult.getText().toString().equals("")) {
                            tvResult.setText("0.");
                        } else {
                            tvResult.setText(tvResult.getText().toString() + ".");
                        }
                        isDotPressed = true;
                    }
                }
                break;
                //endregion
            case R.id.btnAc:
                //region code when all clear button is pressed
                tvResult.setText("");
                tvDisplay.setText("");
                firstNumber = 0;
                secondNumber = 0;
                result = 0;
                op = false;
                isDotPressed = false;
                break;
                //endregion
            case R.id.btnC:
                //region code when clear button is pressed
                if(op || tvDisplay.getText().toString().endsWith("=")){
                    if(tvDisplay.getText().toString().endsWith("=")){
                        tvDisplay.setText("");
                    }
                    tvResult.setText("");
                    op = true;
                }
                else{
                    if(tvResult.getText().toString().endsWith(".")){
                        isDotPressed = false;
                    }
                    String s = getStringMethod(tvResult.getText().toString());
                    if(s.equals("")){
                        op = true;
                    }
                    tvResult.setText(s);
                }
                break;
                //endregion
            case R.id.btnPlusMinus:
                //region code when plus_minus button is pressed
                if (!tvResult.getText().toString().equals("")) {
                    if (!tvDisplay.getText().toString().endsWith("=")) {
                        if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                            if (op) {
                                tvResult.setText("");
                                op = false;
                            } else {
                                double d = Double.parseDouble(tvResult.getText().toString().trim());
                                d = d * -1;

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(d);
                                tvResult.setText(f);
                            }
                        }
                    }
                }
                break;
                //endregion
            case R.id.btnDivide:
                //region code when divide button is pressed
                if (tvDisplay.getText().toString().equals("")) {
                    if (!tvResult.getText().toString().equals("")) {
                        firstNumber = Double.parseDouble(tvResult.getText().toString());
                        op = true;
                        isDotPressed = false;
                        tvResult.setText("");

                        NumberFormat formatter = new DecimalFormat("###.#####");
                        String f = formatter.format(firstNumber);
                        tvDisplay.setText(f + " /");
                    }
                } else {
                    if (tvDisplay.getText().toString().endsWith("/")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber / Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " /");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("-")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber - Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " /");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "/";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("+")) {
                        if (!op) {
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber + Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " /");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "/";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("X")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber * Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " /");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "/";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("%")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber % Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " /");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot remainder by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "/";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("=")) {
                        if (!tvResult.getText().toString().equals("")) {
                            firstNumber = Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvResult.setText("");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvDisplay.setText(f + " /");
                        }
                    } else {
                        tvDisplay.setText(tvDisplay.getText().toString() + " /");
                        tvResult.setText("");
                    }

                }
                break;
                //endregion
            case R.id.btnMultiply:
                //region code when multiply button is pressed
                if (tvDisplay.getText().toString().equals("")) {
                    if (!tvResult.getText().toString().equals("")) {
                        firstNumber = Double.parseDouble(tvResult.getText().toString());
                        op = true;
                        isDotPressed = false;
                        tvResult.setText("");

                        NumberFormat formatter = new DecimalFormat("###.#####");
                        String f = formatter.format(firstNumber);
                        tvDisplay.setText(f + " X");
                    }
                } else {
                    if (tvDisplay.getText().toString().endsWith("X")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                            firstNumber = firstNumber * Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " X");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvResult.setText(f);
                        }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("-")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber - Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " X");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "X";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("+")) {
                        if (!op) {
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber + Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " X");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "X";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("/")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber / Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " X");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "X";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("%")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber % Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " X");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot remainder by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "X";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("=")) {
                        if (!tvResult.getText().toString().equals("")) {
                            firstNumber = Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvResult.setText("");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvDisplay.setText(f + " X");
                        }
                    } else {
                        tvDisplay.setText(tvDisplay.getText().toString() + " X");
                        tvResult.setText("");
                    }

                }
                break;
                //endregion
            case R.id.btnPlus:
                //region code when plus button is pressed
                if (tvDisplay.getText().toString().equals("")) {
                    if (!tvResult.getText().toString().equals("")) {
                        firstNumber = Double.parseDouble(tvResult.getText().toString());
                        op = true;
                        isDotPressed = false;
                        tvResult.setText("");

                        NumberFormat formatter = new DecimalFormat("###.#####");
                        String f = formatter.format(firstNumber);
                        tvDisplay.setText(f + " +");
                    }
                } else {
                    if (tvDisplay.getText().toString().endsWith("+")) {
                        if (!op) {
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber + Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " +");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("-")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber - Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " +");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "+";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("X")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber * Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " +");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "+";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("/")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber / Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " +");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "+";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("%")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber % Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " +");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot remainder by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "+";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("=")) {
                        if (!tvResult.getText().toString().equals("")) {
                            firstNumber = Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvResult.setText("");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvDisplay.setText(f + " +");
                        }
                    } else {
                        tvDisplay.setText(tvDisplay.getText().toString() + " +");
                        tvResult.setText("");
                    }

                }
                break;
                //endregion
            case R.id.btnMinus:
                //region code when minus button is pressed
                if (tvDisplay.getText().toString().equals("")) {
                    if (!tvResult.getText().toString().equals("")) {
                        firstNumber = Double.parseDouble(tvResult.getText().toString());
                        op = true;
                        isDotPressed = false;
                        tvResult.setText("");

                        NumberFormat formatter = new DecimalFormat("###.#####");
                        String f = formatter.format(firstNumber);

                        tvDisplay.setText(f + " -");
                    }
                } else {
                    if (tvDisplay.getText().toString().endsWith("-")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber - Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " -");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("+")) {
                        if (!op) {
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber + Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " -");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "-";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("X")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber * Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " -");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "-";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("/")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber / Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " -");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "-";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("%")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber % Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " -");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot remainder by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "-";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("=")) {
                        if (!tvResult.getText().toString().equals("")) {
                            firstNumber = Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvResult.setText("");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvDisplay.setText(f + " -");
                        }
                    } else {
                        tvDisplay.setText(tvDisplay.getText().toString() + " -");
                        tvResult.setText("");
                    }

                }
                break;
                //endregion
            case R.id.btnPercent:
                //region code when percent/remainder button is pressed
                if (tvDisplay.getText().toString().equals("")) {
                    if (!tvResult.getText().toString().equals("")) {
                        firstNumber = Double.parseDouble(tvResult.getText().toString());
                        op = true;
                        isDotPressed = false;
                        tvResult.setText("");

                        NumberFormat formatter = new DecimalFormat("###.#####");
                        String f = formatter.format(firstNumber);
                        tvDisplay.setText(f + " %");
                    }
                } else {
                    if (tvDisplay.getText().toString().endsWith("%")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber % Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " %");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot remainder by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("-")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber - Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " %");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "%";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("+")) {
                        if (!op) {
                            if (!tvResult.getText().toString().equals("")) {
                                firstNumber = firstNumber + Double.parseDouble(tvResult.getText().toString());
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " %");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(firstNumber);
                                tvResult.setText(f);
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "%";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("X")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                            firstNumber = firstNumber * Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " %");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvResult.setText(f);
                        }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "%";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("/")) {
                        if(!op){
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    firstNumber = firstNumber / Double.parseDouble(tvResult.getText().toString());
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " %");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(firstNumber);
                                    tvResult.setText(f);
                                } else {
                                    op = true;
                                    isDotPressed = false;
                                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }
                            }
                        }
                        else{
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "%";
                            tvDisplay.setText(str);
                            tvResult.setText("");
                        }
                    } else if (tvDisplay.getText().toString().endsWith("=")) {
                        if (!tvResult.getText().toString().equals("")) {
                            firstNumber = Double.parseDouble(tvResult.getText().toString());
                            op = true;
                            isDotPressed = false;
                            tvResult.setText("");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(firstNumber);
                            tvDisplay.setText(f + " %");
                        }
                    } else {
                        tvDisplay.setText(tvDisplay.getText().toString() + " %");
                        tvResult.setText("");
                    }

                }
                break;
                //endregion
            case R.id.btnEqual:
                //region code when equal button is pressed
                if (!tvDisplay.getText().toString().equals("")) {
                    if (tvDisplay.getText().toString().endsWith("+") && !tvResult.getText().toString().equals("")) {
                        if (op) {
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "=";
                            tvDisplay.setText(str);
                            op = true;
                            isDotPressed = false;
                        } else {
                            if (!tvResult.getText().toString().equals("")) {
                                secondNumber = Double.parseDouble(tvResult.getText().toString());
                                result = firstNumber + secondNumber;
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " =");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(result);
                                tvResult.setText(f);
                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("-") && !tvResult.getText().toString().equals("")) {
                        if (op) {
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "=";
                            tvDisplay.setText(str);
                            op = true;
                            isDotPressed = false;
                        } else {
                            if (!tvResult.getText().toString().equals("")) {
                                secondNumber = Double.parseDouble(tvResult.getText().toString());
                                result = firstNumber - secondNumber;
                                op = true;
                                isDotPressed = false;
                                tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " =");

                                NumberFormat formatter = new DecimalFormat("###.#####");
                                String f = formatter.format(result);
                                tvResult.setText(f);
                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("X") && !tvResult.getText().toString().equals("")) {
                        if (!tvResult.getText().toString().equals("")) {
                            secondNumber = Double.parseDouble(tvResult.getText().toString());
                            result = firstNumber * secondNumber;
                            op = true;
                            isDotPressed = false;
                            tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " =");

                            NumberFormat formatter = new DecimalFormat("###.#####");
                            String f = formatter.format(result);
                            tvResult.setText(f);
                        }
                    } else if (tvDisplay.getText().toString().endsWith("/") && !tvResult.getText().toString().equals("")) {
                        if (op) {
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "=";
                            tvDisplay.setText(str);
                            op = true;
                            isDotPressed = false;
                        } else {
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    secondNumber = Double.parseDouble(tvResult.getText().toString());
                                    result = firstNumber / secondNumber;
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " =");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(result);
                                    tvResult.setText(f);
                                } else {
                                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }

                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("%") && !tvResult.getText().toString().equals("")) {
                        if (op) {
                            String string = tvDisplay.getText().toString();
                            String str = string.substring(0, string.length() - 1) + "=";
                            tvDisplay.setText(str);
                            op = true;
                            isDotPressed = false;
                        } else {
                            if (!tvResult.getText().toString().equals("")) {
                                if (Double.parseDouble(tvResult.getText().toString()) != 0) {
                                    secondNumber = Double.parseDouble(tvResult.getText().toString());
                                    result = firstNumber % secondNumber;
                                    op = true;
                                    isDotPressed = false;
                                    tvDisplay.setText(tvDisplay.getText().toString() + " " + tvResult.getText().toString() + " =");

                                    NumberFormat formatter = new DecimalFormat("###.#####");
                                    String f = formatter.format(result);
                                    tvResult.setText(f);
                                } else {
                                    Toast.makeText(this, "Cannot remainder by 0", Toast.LENGTH_LONG).show();
                                    tvResult.setText("");
                                }

                            }
                        }
                    } else if (tvDisplay.getText().toString().endsWith("=")) {

                    } else {
                        if(!tvResult.getText().toString().equals("")){
                            if (op) {
                                tvDisplay.setText(tvDisplay.getText().toString() + " =");
                            }
                        }
                    }
                }
                break;
                //endregion
        }
    }
    //endregion

    //region function to set orange theme
    public void orangeTheme() {
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorTextBrown));
        tvResult.setBackground(getDrawable(R.drawable.back_view_off));

        btnZero.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnOne.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnTwo.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnThree.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnFour.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnFive.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnSix.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnSeven.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnEight.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnNine.setTextColor(getResources().getColor(R.color.colorTextBrown));

        btnAc.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnC.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnPlusMinus.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnDivide.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnMultiply.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnPlus.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnMinus.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnEqual.setBackground(getDrawable(R.drawable.back_view_off));
        btnPercent.setTextColor(getResources().getColor(R.color.colorTextBrown));
        btnDot.setTextColor(getResources().getColor(R.color.colorTextBrown));

        btnZero.setBackground(getDrawable(R.drawable.btn_light));
        btnOne.setBackground(getDrawable(R.drawable.btn_light));
        btnTwo.setBackground(getDrawable(R.drawable.btn_light));
        btnThree.setBackground(getDrawable(R.drawable.btn_light));
        btnFour.setBackground(getDrawable(R.drawable.btn_light));
        btnFive.setBackground(getDrawable(R.drawable.btn_light));
        btnSix.setBackground(getDrawable(R.drawable.btn_light));
        btnSeven.setBackground(getDrawable(R.drawable.btn_light));
        btnEight.setBackground(getDrawable(R.drawable.btn_light));
        btnNine.setBackground(getDrawable(R.drawable.btn_light));

        btnAc.setBackground(getDrawable(R.drawable.btn_light));
        btnC.setBackground(getDrawable(R.drawable.btn_light));
        btnPlusMinus.setBackground(getDrawable(R.drawable.btn_light));
        btnDivide.setBackground(getDrawable(R.drawable.btn_light));
        btnMultiply.setBackground(getDrawable(R.drawable.btn_light));
        btnPlus.setBackground(getDrawable(R.drawable.btn_light));
        btnMinus.setBackground(getDrawable(R.drawable.btn_light));
        btnEqual.setBackground(getDrawable(R.drawable.back_view_off));
        btnPercent.setBackground(getDrawable(R.drawable.btn_light));
        btnDot.setBackground(getDrawable(R.drawable.btn_light));
    }
    //endregion

    //region function to set blue theme
    public void blueTheme() {
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.btnColorDivider));
        tvResult.setBackground(getDrawable(R.drawable.back_view));

        btnZero.setTextColor(getResources().getColor(R.color.colorWhite));
        btnOne.setTextColor(getResources().getColor(R.color.colorWhite));
        btnTwo.setTextColor(getResources().getColor(R.color.colorWhite));
        btnThree.setTextColor(getResources().getColor(R.color.colorWhite));
        btnFour.setTextColor(getResources().getColor(R.color.colorWhite));
        btnFive.setTextColor(getResources().getColor(R.color.colorWhite));
        btnSix.setTextColor(getResources().getColor(R.color.colorWhite));
        btnSeven.setTextColor(getResources().getColor(R.color.colorWhite));
        btnEight.setTextColor(getResources().getColor(R.color.colorWhite));
        btnNine.setTextColor(getResources().getColor(R.color.colorWhite));

        btnAc.setTextColor(getResources().getColor(R.color.colorWhite));
        btnC.setTextColor(getResources().getColor(R.color.colorWhite));
        btnPlusMinus.setTextColor(getResources().getColor(R.color.colorWhite));
        btnDivide.setTextColor(getResources().getColor(R.color.colorWhite));
        btnMultiply.setTextColor(getResources().getColor(R.color.colorWhite));
        btnPlus.setTextColor(getResources().getColor(R.color.colorWhite));
        btnMinus.setTextColor(getResources().getColor(R.color.colorWhite));
        btnEqual.setBackground(getDrawable(R.drawable.back_view));
        btnPercent.setTextColor(getResources().getColor(R.color.colorWhite));
        btnDot.setTextColor(getResources().getColor(R.color.colorWhite));

        btnZero.setBackground(getDrawable(R.drawable.btn_dark));
        btnOne.setBackground(getDrawable(R.drawable.btn_dark));
        btnTwo.setBackground(getDrawable(R.drawable.btn_dark));
        btnThree.setBackground(getDrawable(R.drawable.btn_dark));
        btnFour.setBackground(getDrawable(R.drawable.btn_dark));
        btnFive.setBackground(getDrawable(R.drawable.btn_dark));
        btnSix.setBackground(getDrawable(R.drawable.btn_dark));
        btnSeven.setBackground(getDrawable(R.drawable.btn_dark));
        btnEight.setBackground(getDrawable(R.drawable.btn_dark));
        btnNine.setBackground(getDrawable(R.drawable.btn_dark));

        btnAc.setBackground(getDrawable(R.drawable.btn_dark));
        btnC.setBackground(getDrawable(R.drawable.btn_dark));
        btnPlusMinus.setBackground(getDrawable(R.drawable.btn_dark));
        btnDivide.setBackground(getDrawable(R.drawable.btn_dark));
        btnMultiply.setBackground(getDrawable(R.drawable.btn_dark));
        btnPlus.setBackground(getDrawable(R.drawable.btn_dark));
        btnMinus.setBackground(getDrawable(R.drawable.btn_dark));
        btnEqual.setBackground(getDrawable(R.drawable.back_view));
        btnPercent.setBackground(getDrawable(R.drawable.btn_dark));
        btnDot.setBackground(getDrawable(R.drawable.btn_dark));
    }
    //endregion

    //region function used in transparent notification bar
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    //endregion

    //region function used to remove one digit
    public String getStringMethod(String str) {
        if (!str.equals("") && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    //endregion

    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}