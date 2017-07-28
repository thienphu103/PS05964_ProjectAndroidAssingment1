package com.example.a.assignmnet;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class TabListViewSV extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST;
    Button btn;
    ListView LvSinhVien;
    private ArrayList<SinhVien> arrayList;
    private AdapterSinhVien adapterSinhVien;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view_student,container,false);
        LvSinhVien = (ListView) view.findViewById(R.id.listViewSinhVien);

        arrayList = new ArrayList<>();
        arrayList.add(new SinhVien("Lập Trình Mobile", "PT12318", R.drawable.classicon));
        arrayList.add(new SinhVien("Thiết Kế Đồ Họa", "PT12317", R.drawable.classicon));
        arrayList.add(new SinhVien("Thiết Kế Web", "PT12317", R.drawable.classicon));
        arrayList.add(new SinhVien("Ứng Dụng Phần Mềm", "PT12317", R.drawable.classicon));



        adapterSinhVien = new AdapterSinhVien(getActivity(), R.layout.info_sv, arrayList);

        LvSinhVien.setAdapter(adapterSinhVien);

        btn = (Button) view.findViewById(R.id.btnAddStu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Add Student");
                dialog.setContentView(R.layout.activity_dialog);
                dialog.show();
            }
        });


        return view;
    }

}
