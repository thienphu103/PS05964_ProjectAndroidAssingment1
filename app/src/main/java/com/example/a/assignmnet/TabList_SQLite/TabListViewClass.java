package com.example.a.assignmnet.TabList_SQLite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.assignmnet.Adapter.AdapterLop;
import com.example.a.assignmnet.Class.SinhVien;
import com.example.a.assignmnet.R;
import com.example.a.assignmnet.SQL.SQLite;

import java.util.ArrayList;

public class TabListViewClass extends Fragment {
    private static final String TAG = "Tab2Fragment";
    ListView listView;
    public ArrayList<SinhVien> arrayList;
    private AdapterLop adapterLop;
    Dialog dialog;
    SQLite database;
    Button btnadd;
    EditText edtsearch;
    TextView txtdialogname;
    Button btndialogup;
    Button btnsearch;
    Button btndialogdel;
    EditText edtdialogID;
    EditText edtdialogTilte;
    EditText edtdialogAuthor;
    EditText edtdialogPrice;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view_class, container, false);
        //AX
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_dialog);
        database = new SQLite(getContext(), "Class1.sqlite", null, 1);
        arrayList = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listviewbook);
        btnadd = (Button) view.findViewById(R.id.btnAdd);
        edtsearch = (EditText) view.findViewById(R.id.edtSearch);
        edtdialogID = (EditText) view.findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) view.findViewById(R.id.dialogTILTE);
        edtdialogAuthor = (EditText) view.findViewById(R.id.dialogAUTHOR);
        edtdialogPrice = (EditText) view.findViewById(R.id.dialogPRICE);
        btndialogup = (Button) view.findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) view.findViewById(R.id.dialogbtnDEL);
        btnsearch = (Button) view.findViewById(R.id.btnSearch);
        txtdialogname = (TextView) dialog.findViewById(R.id.dialogName);
//        database.getDataBook("");
        arrayList.clear();
        showlist("");

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBook();
                btndialogup.setText("Add");
                txtdialogname.setText("");
                btndialogdel.setVisibility(View.INVISIBLE);
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("text", edtsearch.getText().toString());
                arrayList.clear();
                adapterLop.notifyDataSetChanged();
//                database.getSearchBook("");
                showlist(edtsearch.getText().toString());
                Toast.makeText(getContext(), "Search OK", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = i;
                SinhVien a = new SinhVien();
                final String indextext = arrayList.get(index).getId() + "";
                ;
                Log.d("index", index + "  " + indextext);
                txtdialogname = (TextView) dialog.findViewById(R.id.dialogName);
//                final String namedialog=indextext.substring(indextext.indexOf("\n")+1,indextext.indexOf("\n"));
                DialogBook();
                btndialogdel.setVisibility(View.VISIBLE);
                dialog.setTitle("Update and Delete");
                txtdialogname.setText(arrayList.get(index).getName());
                btndialogdel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String sql = database.delete(indextext);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Delete");
                        builder.setMessage("Can You Detele " + arrayList.get(index).getName() + " class?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.QueryData(sql);
                                Toast.makeText(getContext(), "Delete OK", Toast.LENGTH_SHORT).show();
                                database.getData("");
                                adapterLop.notifyDataSetChanged();
                            }

                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                        database.getData("");
                        arrayList.clear();
                        adapterLop.notifyDataSetChanged();
                        showlist("");
                        dialog.dismiss();
                    }
                });
                btndialogup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = edtdialogID.getText().toString();
                        String tilte = edtdialogTilte.getText().toString();
                        String author = edtdialogAuthor.getText().toString();
                        String price = edtdialogPrice.getText().toString();
                        final String sql = database.update(Integer.parseInt(id), tilte, author,null, price, indextext);
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Update");
                            builder.setMessage("Can You Update " + arrayList.get(index).getName() + " to " + tilte + "  class?");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    database.QueryData(sql);
                                    Toast.makeText(getContext(), "Update OK", Toast.LENGTH_SHORT).show();
                                    database.getData("");
                                    adapterLop.notifyDataSetChanged();
                                }

                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        } catch (Exception ex) {
                            AlertErrorDialog();
                        }
//                arrayList.add(new ClassBook(Integer.parseInt(id),tilte,author,price));
                        database.getData("");
                        arrayList.clear();
                        adapterLop.notifyDataSetChanged();
                        showlist("");
                        dialog.dismiss();

//              database.QueryData(database.addBook(new ClassBook(0,tilte,author,price)));
                    }
                });

                return false;

            }
        });
        adapterLop = new AdapterLop(getActivity(), R.layout.info_sv, arrayList);
        listView.setAdapter(adapterLop);
        return view;
    }


    public void DialogBook() {
        dialog.setTitle("Add Book");
        edtdialogID = (EditText) dialog.findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) dialog.findViewById(R.id.dialogTILTE);
        edtdialogAuthor = (EditText) dialog.findViewById(R.id.dialogAUTHOR);
        edtdialogPrice = (EditText) dialog.findViewById(R.id.dialogPRICE);
        btndialogup = (Button) dialog.findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) dialog.findViewById(R.id.dialogbtnDEL);
        edtdialogID.setText("");
        edtdialogPrice.setText("");
        edtdialogTilte.setText("");
        edtdialogAuthor.setText("");
        btndialogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtdialogID.getText().toString();
                String tilte = edtdialogTilte.getText().toString();
                String author = edtdialogAuthor.getText().toString();
                String price = edtdialogPrice.getText().toString();
                final String sql = database.add(Integer.parseInt(id), tilte,null, author, price);
                try {
                    database.QueryData(sql);
                    Toast.makeText(getContext(), "Add OK", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    AlertErrorDialog();
                }

//                arrayList.add(new ClassBook(Integer.parseInt(id),tilte,author,price));
                database.getData("");
                arrayList.clear();
                adapterLop.notifyDataSetChanged();
                showlist("");
                dialog.dismiss();
            }

//              database.QueryData(database.addBook(new ClassBook(0,tilte,author,price)));

        });
        dialog.show();
    }

    public void AlertErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Error");
        builder.setMessage("ID Exist, Please input other Id.Tks");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void showlist(String name) {
        if (name.equals("")) {
            arrayList = database.getData("");
        } else {
            arrayList = database.getSearch(edtsearch.getText().toString());
        }


    }


}





