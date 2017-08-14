package com.example.a.assignmnet.ListView_NotUsed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.example.a.assignmnet.Adapter.AdapterSinhVien;
import com.example.a.assignmnet.Class.SinhVien;
import com.example.a.assignmnet.R;

import java.util.ArrayList;

public class ListViewClass extends AppCompatActivity  {
    Button btn;
    ListView lvSinhVien;
    private ArrayList<SinhVien> arrayList;
    private AdapterSinhVien adapterLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_student);

//        lvSinhVien = (ListView) findViewById(R.id.listViewSinhVien);
//
//        arrayList = new ArrayList<>();
//        arrayList.add(new SinhVien("Lập Trình Mobile", "PT12318", R.drawable.classicon));
//        arrayList.add(new SinhVien("Thiết Kế Đồ Họa", "PT12317", R.drawable.classicon));
//        arrayList.add(new SinhVien("Thiết Kế Web", "PT12317", R.drawable.classicon));
//        arrayList.add(new SinhVien("Ứng Dụng Phần Mềm", "PT12317", R.drawable.classicon));



        adapterLop = new AdapterSinhVien(this, R.layout.info_sv, arrayList);

        lvSinhVien.setAdapter(adapterLop);

//        btn = (Button) findViewById(R.id.btnAddStu);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(ListViewClass.this);
//                dialog.setTitle("Add Student");
//                dialog.setContentView(R.layout.activity_dialog);
//                dialog.show();
//            }
//        });
    }


}





