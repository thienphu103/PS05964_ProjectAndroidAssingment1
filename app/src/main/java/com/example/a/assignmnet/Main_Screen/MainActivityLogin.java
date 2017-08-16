package com.example.a.assignmnet.Main_Screen;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.assignmnet.Class.Resgiter;
import com.example.a.assignmnet.R;
import com.example.a.assignmnet.SQL.SQLite;

import java.util.ArrayList;


public class MainActivityLogin extends AppCompatActivity {

    public EditText txt1;
    public EditText txt2;
    public Button btn1;
    public TextView txts1;
    public TextView txts2;
    public TextView txts3;
    public TextView user;
    public TextView email;
    SQLite database;
    public static int admin=0;
    public ArrayList<Resgiter> arrayList;
    String infouser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        database = new SQLite(getApplicationContext(), "register5.sqlite", null, 1);
        arrayList = new ArrayList<>();
        arrayList = database.getDataRe("");
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
                Log.d("key",arrayList.size()+"");
                String text = txt1.getText().toString();
                String pass = txt2.getText().toString();
                if (text.equals("")) {
                    txt1.setError("Enter User. Please!!!");
                } else {
                    txts1.setText("User Ok");
                    txts1.setTextColor(Color.GREEN);
                }
                if (pass.equals("")) {
                    txt2.setError("Enter Password. Please!!!");

                } else {
                    txts2.setText("Password Ok");
                    txts2.setTextColor(Color.GREEN);
                }
                if (!text.equalsIgnoreCase("") && (!pass.equalsIgnoreCase(""))) {
                    int usertest=0;
                    int passtest=0;
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).getUser().equalsIgnoreCase(text)) {
                            usertest=1;
                            infouser=arrayList.get(i).getUser();
                        }
                        if (arrayList.get(i).getPass().equalsIgnoreCase(pass)) {
                            passtest=1;
                        }

                    }
                    Log.d("Integer",usertest+""+passtest);
                    if(usertest==1&&passtest==1) {
                        admin=1;
                        Toast.makeText(getApplicationContext(), "Login Ok", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                        else if(!(usertest==1&&passtest==1)){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivityLogin.this);

                            alertDialogBuilder.setMessage("Username and Password Not Correct\n" +
                                    "You want to log in as guest");
                            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Intent intent = new Intent(MainActivityLogin.this, MainActivity.class);
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

                } else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivityLogin.this);

                    alertDialogBuilder.setMessage("User and Pass Not Correct\n" +
                            "You want to log in as guest");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            Intent intent = new Intent(MainActivityLogin.this, MainActivity.class);
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
