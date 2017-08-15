package com.example.a.assignmnet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.assignmnet.Class.Lop;
import com.example.a.assignmnet.R;

import java.util.List;

/**
 * Created by namtn on 25-Jul-17.
 */

public class AdapterLop extends BaseAdapter {

    Context context;
    int layout;
    List<Lop> LopList;

    public AdapterLop(Context context, int layout, List<Lop> LopsvList) {
        this.context = context;
        this.layout = layout;
        this.LopList =LopsvList ;
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

    public List<Lop> getLopsvList() {
        return LopList;
    }

    public void setLopsvList(List<Lop> LopsvList) {
        this.LopList = LopsvList;
    }

    @Override
    public int getCount() {
        return LopList.size();
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

        Lop Lop = LopList.get(position);

        viewHolder.txtName.setText(Lop.getId());
        viewHolder.txtId.setText(Lop.getName());
        viewHolder.txtClass.setVisibility(View.INVISIBLE);
        viewHolder.txtGen.setVisibility(View.INVISIBLE);
        viewHolder.txtBir.setVisibility(View.INVISIBLE);

//        viewHolder.ivImg.setImageResource(Lop.getUrlHinh());
        viewHolder.ivImg.setImageResource(R.drawable.classicon);
        return convertView;
    }

}
