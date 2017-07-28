package com.example.a.assignmnet;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public EditText txt1;
    public EditText txt2;
    public Button btn1;
    public TextView txts1;
    public TextView txts2;
    public TextView txts3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClick();
    }

    public void OnClick() {
        txt1 = (EditText) findViewById(R.id.txtUser);
        txt2 = (EditText) findViewById(R.id.txtPass);
        txts1 = (TextView) findViewById(R.id.txtTest1);
        txts2 = (TextView) findViewById(R.id.txtTest2);
        txts3 = (Button) findViewById(R.id.txtTest3);
        btn1 = (Button) findViewById(R.id.btnLogin);
        txts3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txt1.getText().toString();
                String pass = txt2.getText().toString();
                if (text.equals("")) {
                    txts1.setText("Enter User. Please!!!");
                    txts1.setTextColor(Color.RED);
                    txt1.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            txts1.setText("");
                            return false;
                        }
                    });
                } else if (!text.equalsIgnoreCase("admin")) {

                    txts1.setText("User is invalid.");
                    txts1.setTextColor(Color.RED);
                    txt1.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            txts1.setText("");
                            return false;
                        }
                    });

                } else {
                    txts1.setText("User Ok");
                    txts1.setTextColor(Color.GREEN);
                }
                if (pass.equals("")) {
                    txts2.setText("Enter Password. Please!!!");
                    txts2.setTextColor(Color.RED);
                    txt2.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            txts2.setText("");
                            return false;
                        }
                    });
                } else if (!pass.equalsIgnoreCase("123456")) {

                    txts2.setText("Wrong Password.");
                    txts2.setTextColor(Color.RED);
                    txt2.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            txts2.setText("");
                            return false;
                        }
                    });

                } else {
                    txts2.setText("Password Ok");
                    txts2.setTextColor(Color.GREEN);
                }
                if (text.equalsIgnoreCase("admin") && (pass.equalsIgnoreCase("123456"))) {
                    Toast.makeText(MainActivity.this, "Login Ok", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(MainActivity.this,NavigationActivity.class);
                    startActivity(intent);
                }

            }

        });
        /**/

    }
}
