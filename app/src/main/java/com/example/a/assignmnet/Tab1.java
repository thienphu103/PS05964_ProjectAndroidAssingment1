package com.example.a.assignmnet;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Tab1 extends AppCompatActivity {


    public Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        OnClick();
    }

    public void OnClick() {
btn1=(Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tab1.this, "Login Ok", Toast.LENGTH_LONG).show();
            }
        });
    }

}
