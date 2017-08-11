package com.example.a.assignmnet.TabList_SQLite;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.a.assignmnet.Adapter.AdapterLop;
import com.example.a.assignmnet.Class.SinhVien;
import com.example.a.assignmnet.R;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class TabListViewClass extends Fragment {
    private static final String TAG = "Tab2Fragment";
    Button btn;
    ListView lvLop;
    private ArrayList<SinhVien> arrayList;
    private AdapterLop adapterLop;

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view_class,container,false);

        lvLop = (ListView) view.findViewById(R.id.ListViewClass);

        arrayList = new ArrayList<>();
        arrayList.add(new SinhVien("Lập Trình Mobile", "PT12318", R.drawable.classicon));
        arrayList.add(new SinhVien("Thiết Kế Đồ Họa", "PT12317", R.drawable.classicon));
        arrayList.add(new SinhVien("Thiết Kế Web", "PT12317", R.drawable.classicon));
        arrayList.add(new SinhVien("Ứng Dụng Phần Mềm", "PT12317", R.drawable.classicon));



        adapterLop = new AdapterLop(getActivity(), R.layout.info_sv, arrayList);

        lvLop.setAdapter(adapterLop);

        btn = (Button) view.findViewById(R.id.btnAddClass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Add Class");
                dialog.setContentView(R.layout.activity_dialog);
                dialog.show();
            }
        });
        return view;
    }
}
