package com.example.a.assignmnet.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.assignmnet.Class.SinhVien;
import com.example.a.assignmnet.R;

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
        TextView txtName, txtId, txtClass, txtBir, txtGen;
        ImageView ivImage;
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
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.imageViewImage);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SinhVien sv = sinhVienList.get(position);
        viewHolder.txtName.setText(sv.getName());
        viewHolder.txtId.setText("ID: PS0" + sv.getId());
        viewHolder.txtClass.setText("CLASS: PT" + sv.getLop());
        viewHolder.txtBir.setText("BIRTHDAY: "+sv.getBirthday());
        viewHolder.txtGen.setText("GENDER: "+sv.getGender());
//        Bitmap bitmap = BitmapFactory.decodeByteArray(sinhVien.getImage(),0,sinhVien.getImage().length);
//        viewHolder.ivImage.setImageBitmap(bitmap);

//    Log.d("image",bitmap+"");
//        viewHolder.ivImg.setImageResource(sinhVien.getUrlHinh());
//        viewHolder.ivImage.setImageBitmap(bitmap);
        byte[]hinhanh = sv.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
//        Bitmap bitmap=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.student);
        viewHolder.ivImage.setImageBitmap(bitmap);
//        viewHolder.ivImage.setImageResource(R.drawable.studenticon);
        return convertView;
    }
}
