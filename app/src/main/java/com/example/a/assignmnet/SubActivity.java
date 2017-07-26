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


public class SubActivity extends AppCompatActivity {

    public EditText txt1;
    public EditText txt2;
    public EditText txt3;
    public Button btn1;
    public TextView txts1;
    public TextView txts2;
    public TextView txts3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        OnClick();
    }

    public void OnClick() {
        txt1 = (EditText) findViewById(R.id.txtUser);
        txt2 = (EditText) findViewById(R.id.txtPass);
        txt3 = (EditText) findViewById(R.id.txtRePass);
        txts1 = (TextView) findViewById(R.id.txtTest1);
        txts2 = (TextView) findViewById(R.id.txtTest2);
        txts3=(TextView) findViewById(R.id.txtTest3);

        btn1 = (Button) findViewById(R.id.btnLogin);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txt1.getText().toString();
                String pass = txt2.getText().toString();
                String repass=txt3.getText().toString();
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
                }else if (!(text.length()<16&&text.length()>1)) {
                    txts1.setText("User must have at 1-16 characters");
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

                if (pass.equals("")||pass.length()<6) {
                    txts2.setText("Password must have at 6 characters");
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
                if (repass.equals("")||repass.length()<6) {
                    txts3.setText("Password must have at 6 characters");
                    txts3.setTextColor(Color.RED);
                    txt2.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            txts2.setText("");
                            return false;
                        }
                    });
                } else if (!pass.equalsIgnoreCase(repass)) {
                    txts3.setText("Does not match password.");
                    txts3.setTextColor(Color.RED);
                    txt3.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            txts3.setText("");
                            return false;
                        }
                    });
                }else {
                    txts3.setText("Password Ok");
                    txts3.setTextColor(Color.GREEN);
                }
                if ((text.length()<16&&text.length()>1) && (pass.equalsIgnoreCase(repass) &&!(repass.equals("")&&pass.equals("")))){
                    Toast.makeText(SubActivity.this, "Register Ok", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(SubActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });


    }
}
