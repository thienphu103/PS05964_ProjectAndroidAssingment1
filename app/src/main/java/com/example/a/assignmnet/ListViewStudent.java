package com.example.a.assignmnet;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewStudent extends AppCompatActivity {
Button btn;
    ListView lvSinhVien;
    private ArrayList<SinhVien> arrayList;
    private AdapterSinhVien adapterSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_student);

        lvSinhVien = (ListView) findViewById(R.id.listViewSinhVien);

        arrayList = new ArrayList<>();
        arrayList.add(new SinhVien("Nguyễn Trường An", "PS06077", R.drawable.person));
        arrayList.add(new SinhVien("Đặng Phạm Nhật Thanh", "PS06077", R.drawable.person));
        arrayList.add(new SinhVien("Lê Công Danh", "PS06077", R.drawable.person));
        arrayList.add(new SinhVien("Lê Vương Thiện Phú", "PS06077", R.drawable.person));
        arrayList.add(new SinhVien("Truong Nhat Nam", "PS06077", R.drawable.person));



        adapterSinhVien = new AdapterSinhVien(this, R.layout.info_sv, arrayList);

        lvSinhVien.setAdapter(adapterSinhVien);

            btn = (Button) findViewById(R.id.btnAddStu);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(ListViewStudent.this);
                    dialog.setTitle("Add Student");
                    dialog.setContentView(R.layout.activity_dialog);
                    dialog.show();
                }
            });
        }

    }

