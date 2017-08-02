package com.example.a.assignmnet;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class TabListViewSV extends Fragment {
    private static final String TAG = "Tab1Fragment";
    ListView lvSinhVien;
    SQLite database;
    EditText user;
    EditText mssv;
    Button btnadd;
    String userdb;
    String mssvdb;
    String userdialog;
    String mssvdialog;
    private Button btnTEST;
    Button btn;
    ListView LvSinhVien;
    private ArrayList<SinhVien> arrayList;
    private AdapterSinhVien adapterSinhVien;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view_student, container, false);
        LvSinhVien = (ListView) view.findViewById(R.id.listViewSinhVien);
        user = (EditText) view.findViewById(R.id.txtUserDialog);
        mssv = (EditText) view.findViewById(R.id.txtPassDialog);
        lvSinhVien = (ListView) view.findViewById(R.id.listViewSinhVien);
        arrayList = new ArrayList<>();
        arrayList.clear();
        database = new SQLite(getContext(), "Student3.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Sinhvien(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten WARCHAR,MSSV WARCHAR)");
        GetDataDB();
        adapterSinhVien = new AdapterSinhVien(getActivity(), R.layout.info_sv, arrayList);

        LvSinhVien.setAdapter(adapterSinhVien);

        btn = (Button) view.findViewById(R.id.btnAddStu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.activity_dialog);
                dialog.setTitle("Add Student");
                user = (EditText) dialog.findViewById(R.id.txtUserDialog);
                mssv = (EditText) dialog.findViewById(R.id.txtPassDialog);
                btnadd = (Button) dialog.findViewById(R.id.btnAdd1);
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userdialog = user.getText().toString();
                        mssvdialog = user.getText().toString();
                        database.QueryData("INSERT INTO Sinhvien VALUES(null,'" + userdialog + "','" + mssvdialog + "')");
                        arrayList.add(new SinhVien(userdb, mssvdb, 0));
                        Toast.makeText(getContext(), "Add OK", Toast.LENGTH_SHORT).show();
                        adapterSinhVien.notifyDataSetChanged();
                        dialog.dismiss();

                        GetDataDB();

                    }
                });

                dialog.show();
            }
        });
        return view;
    }

    final public void GetDataDB() {

        Cursor cursor = database.GetData("SELECT * FROM Sinhvien");
        while (cursor.moveToNext()) {
            userdb = cursor.getString(1);
            mssvdb = cursor.getString(2);
            arrayList.add(new SinhVien(userdb, mssvdb, 0));
        }

    }


}

