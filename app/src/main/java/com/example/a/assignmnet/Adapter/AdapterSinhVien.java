package com.example.a.assignmnet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.assignmnet.R;
import com.example.a.assignmnet.Class.SinhVien;

import java.util.List;

/**
 * Created by namtn on 25-Jul-17.
 */

public class AdapterSinhVien extends BaseAdapter {

    Context context;
    int layout;
    List<SinhVien> sinhVienList;

    public AdapterSinhVien(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<SinhVien> getSinhVienList() {
        return sinhVienList;
    }

    public void setSinhVienList(List<SinhVien> sinhVienList) {
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtName, txtId,txtClass,txtBir,txtGen;
        ImageView ivImg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout, null);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.txtId = (TextView) convertView.findViewById(R.id.textViewId);
            viewHolder.txtClass = (TextView) convertView.findViewById(R.id.textViewClass);
            viewHolder.txtGen = (TextView) convertView.findViewById(R.id.textViewGender);
            viewHolder.txtBir = (TextView) convertView.findViewById(R.id.textViewBir);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.imageViewImage);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SinhVien sinhVien = sinhVienList.get(position);

        viewHolder.txtName.setText(sinhVien.getName());
        viewHolder.txtId.setText(sinhVien.getId());
        viewHolder.txtClass.setText(sinhVien.getLop());
        viewHolder.txtBir.setText(sinhVien.getBirthday());
        viewHolder.txtGen.setText(sinhVien.getGender());
        viewHolder.ivImg.setImageResource(sinhVien.getUrlHinh());
        return convertView;
    }
}
