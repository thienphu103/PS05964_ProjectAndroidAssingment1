package com.example.a.assignmnet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ListViewStudent extends AppCompatActivity {
    Button btn;
    ListView lvSinhVien;
    public ArrayList<SinhVien> arrayList;
    public AdapterSinhVien adapterSinhVien;
    SQLite database;
    EditText user;
    EditText mssv;
    Button btnadd;
    String userdb;
    String mssvdb;
    String userdialog;
    String mssvdialog;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        user = (EditText) findViewById(R.id.txtUserDialog);
//        mssv = (EditText) findViewById(R.id.txtPassDialog);
//        setContentView(R.layout.activity_list_view_student);
//        lvSinhVien = (ListView) findViewById(R.id.listViewSinhVien);
//        arrayList = new ArrayList<>();
//        arrayList.clear();
//        database= new SQLite(this, "Student3.sqlite", null, 1);
//        database.QueryData("CREATE TABLE IF NOT EXISTS Sinhvien(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten WARCHAR,MSSV WARCHAR)");
//        GetDataDB();
////        arrayList.add(new SinhVien("Đặng Phạm Nhật Thanh", "PS06077", R.drawable.studenticon));
////        arrayList.add(new SinhVien("Lê Công Danh", "PS06077", R.drawable.studenticon));
////        arrayList.add(new SinhVien("Lê Vương Thiện Phú", "PS06077", R.drawable.studenticon));
////        arrayList.add(new SinhVien("Truong Nhat Nam", "PS06077", R.drawable.studenticon));
//        adapterSinhVien = new AdapterSinhVien(this, R.layout.info_sv, arrayList);
//        lvSinhVien.setAdapter(adapterSinhVien);
//
//        btn = (Button) findViewById(R.id.btnAddStu);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(ListViewStudent.this);
//                dialog.setContentView(R.layout.activity_dialog);
//                dialog.setTitle("Add Student");
//                user = (EditText) dialog.findViewById(R.id.txtUserDialog);
//                mssv = (EditText) dialog.findViewById(R.id.txtPassDialog);
//                btnadd = (Button) dialog.findViewById(R.id.btnAdd1);
//                btnadd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        userdialog = user.getText().toString();
//                        mssvdialog = user.getText().toString();
//                        database.QueryData("INSERT INTO Sinhvien VALUES(null,'" + userdialog + "','" + mssvdialog + "')");
//                        arrayList.add(new SinhVien(userdb, mssvdb, 0));
//                        Toast.makeText(getApplicationContext(),"Add OK",Toast.LENGTH_SHORT).show();
//
//                        Intent intent= new Intent(getApplicationContext(),ListViewStudent.class);
//                        startActivity(intent);
//                        dialog.dismiss();
//
//                    }
//                });
//
//                dialog.show();
//            }
//        });

 }
//    final public void GetDataDB() {
//        Cursor cursor = database.GetData("SELECT * FROM Sinhvien");
//        while (cursor.moveToNext()) {
//            userdb = cursor.getString(1);
//            mssvdb = cursor.getString(2);
//            arrayList.add(new SinhVien(userdb, mssvdb, 0));
//        }
//
//    }


}

