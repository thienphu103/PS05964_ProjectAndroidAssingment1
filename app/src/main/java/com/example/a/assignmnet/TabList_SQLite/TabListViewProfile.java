package com.example.a.assignmnet.TabList_SQLite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a.assignmnet.Class.Resgiter;
import com.example.a.assignmnet.Main_Screen.MainActivityLogin;
import com.example.a.assignmnet.R;
import com.example.a.assignmnet.SQL.SQLite;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class TabListViewProfile extends Fragment {
    private static final String TAG = "Tab3Fragment";
    public ArrayList<Resgiter> arrayList;
    SQLite database;
    private Button btnTEST;
    String info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_profile, container, false);
        database = new SQLite(getContext(), "register5.sqlite", null, 1);
        arrayList = new ArrayList<>();
        arrayList = database.getDataRe("");
        btnTEST = (Button) view.findViewById(R.id.btnLogin);
        for (int i = 0; i < arrayList.size(); i++) {
            info += arrayList.get(i).toString();

        }
        MainActivityLogin login = new MainActivityLogin();
        Log.d("key", login.admin + "");
        if (login.admin == 0) {
            btnTEST.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setMessage("You Need Account Admin or User not Guest\n Tks:V");
                    alertDialogBuilder.setTitle("");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()

                    {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {


                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        } else {
            btnTEST.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setMessage(info);
                    alertDialogBuilder.setTitle("Information Account");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {


                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }
        return view;
    }
}
