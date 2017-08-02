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
    Button btn1,btn2;
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

        database = new SQLite(getContext(), "Student3.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Sinhvien(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten WARCHAR,MSSV WARCHAR)");
//        database.QueryData("DELETE FROM Sinhvien "); (DELETE All)
        GetDataDB();
        adapterSinhVien = new AdapterSinhVien(getActivity(), R.layout.info_sv, arrayList);

        LvSinhVien.setAdapter(adapterSinhVien);

        btn = (Button) view.findViewById(R.id.btnAddStu);
        btn1 = (Button) view.findViewById(R.id.btnDelStu);
        btn2 = (Button) view.findViewById(R.id.btnUpStu);
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
                        mssvdialog = mssv.getText().toString();
                        database.QueryData("INSERT INTO Sinhvien VALUES(null,'" + userdialog + "','" + mssvdialog + "')");
                        Toast.makeText(getContext(), "Add OK", Toast.LENGTH_SHORT).show();
                        adapterSinhVien.notifyDataSetChanged();
                        dialog.dismiss();
                        GetDataDB();

                    }
                });

                dialog.show();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getActivity());
                dialog1.setContentView(R.layout.activity_dialog);
                dialog1.setTitle("Delete Student");
                user = (EditText) dialog1.findViewById(R.id.txtUserDialog);
                user.setHint("Input Id Detete");
                mssv = (EditText) dialog1.findViewById(R.id.txtPassDialog);
                mssv.setVisibility(View.INVISIBLE);
                btnadd = (Button) dialog1.findViewById(R.id.btnAdd1);
                btnadd.setText("Delete");
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userdialog = user.getText().toString();
                        database.QueryData("DELETE FROM Sinhvien WHERE Ten='"+userdialog+"'");//Detete row name & id
//                        database.QueryData("DELETE FROM Sinhvien");
                        Toast.makeText(getContext(), "Delete OK", Toast.LENGTH_SHORT).show();
                        adapterSinhVien.notifyDataSetChanged();
                        dialog1.dismiss();
                        GetDataDB();
                    }
                });
                dialog1.show();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getActivity());
                dialog1.setContentView(R.layout.activity_dialog);
                dialog1.setTitle("Update Student");
                user = (EditText) dialog1.findViewById(R.id.txtUserDialog);
                user.setHint("Input Id Update");
                mssv = (EditText) dialog1.findViewById(R.id.txtPassDialog);
                mssv.setHint("Update Here by Id");
                btnadd = (Button) dialog1.findViewById(R.id.btnAdd1);
                btnadd.setText("Update");
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userdialog = user.getText().toString();
                        mssvdialog = mssv.getText().toString();
                        database.QueryData("UPDATE Sinhvien SET Ten='"+mssvdialog+"' WHERE Ten='"+userdialog+"'");//Uodate
//                        database.QueryData("DELETE FROM Sinhvien");
                        Toast.makeText(getContext(), "Update OK", Toast.LENGTH_SHORT).show();
                        adapterSinhVien.notifyDataSetChanged();
                        dialog1.dismiss();
                        GetDataDB();
                    }
                });
                dialog1.show();

            }
        });
        return view;
    }

    final public void GetDataDB() {
        arrayList.clear();
//Get Database and display
        Cursor cursor = database.GetData("SELECT * FROM Sinhvien");
        while (cursor.moveToNext()) {
            userdb = cursor.getString(1);
            mssvdb = cursor.getString(2);
            arrayList.add(new SinhVien(userdb, mssvdb, 0));
        }

    }



}

