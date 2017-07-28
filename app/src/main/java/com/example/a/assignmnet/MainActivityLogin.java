package com.example.a.assignmnet;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivityLogin extends AppCompatActivity {

    public EditText txt1;
    public EditText txt2;
    public Button btn1;
    public TextView txts1;
    public TextView txts2;
    public TextView txts3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
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
                Intent intent = new Intent(MainActivityLogin.this, SubActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txt1.getText().toString();
                String pass = txt2.getText().toString();
                if (text.equals("")) {
                    txt1.setError("Enter User. Please!!!");

                } else if (!text.equalsIgnoreCase("admin")) {

                    txt1.setError("User is invalid.");


                } else {
                    txts1.setText("User Ok");
                    txts1.setTextColor(Color.GREEN);
                }
                if (pass.equals("")) {
                    txt2.setError("Enter Password. Please!!!");

                } else if (!pass.equalsIgnoreCase("123456")) {

                    txt2.setError("Wrong Password.");


                } else {
                    txts2.setText("Password Ok");
                    txts2.setTextColor(Color.GREEN);
                }
                if (text.equalsIgnoreCase("admin") && (pass.equalsIgnoreCase("123456"))) {
                    Toast.makeText(MainActivityLogin.this, "Login Ok", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(MainActivityLogin.this,MainActivity.class);
                    startActivity(intent);
                }else{

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivityLogin.this);

                    alertDialogBuilder.setMessage("You want to log in as guest");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent =new Intent(MainActivityLogin.this,MainActivity.class);
                            startActivity(intent);

                        }
                    });

                    alertDialogBuilder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            }

        });
        /**/

    }
}
