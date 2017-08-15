package com.example.a.assignmnet.TabList_SQLite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.assignmnet.Adapter.AdapterSinhVien;
import com.example.a.assignmnet.Class.Lop;
import com.example.a.assignmnet.Class.SinhVien;
import com.example.a.assignmnet.R;
import com.example.a.assignmnet.SQL.SQLite;

import java.util.ArrayList;
import java.util.List;

public class TabListViewSV extends Fragment {
    private static final String TAG = "Tab1Fragment";
    ListView listView;
    public ArrayList<SinhVien> arrayList;
    private AdapterSinhVien adapterSinhVien;
    Dialog dialog;
    public SQLite database;
    public SQLite databaseclass;
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
    RadioGroup radioGroup;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    String Gender = "";
    Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view_student, container, false);
        //AX
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_dialog);
        database = new SQLite(getContext(), "Student2.sqlite", null, 1);
        arrayList = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listviewbook);
        btnadd = (Button) view.findViewById(R.id.btnAdd);
        edtsearch = (EditText) view.findViewById(R.id.edtSearch);
        edtdialogID = (EditText) view.findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) view.findViewById(R.id.dialogTILTE);
        edtdialogPrice = (EditText) view.findViewById(R.id.dialogPRICE);
        btndialogup = (Button) view.findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) view.findViewById(R.id.dialogbtnDEL);
        btnsearch = (Button) view.findViewById(R.id.btnSearch);
        txtdialogname = (TextView) dialog.findViewById(R.id.dialogName);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_character);
        radioButtonMale = (RadioButton) view.findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) view.findViewById(R.id.radioButton_female);
        spinner = (Spinner) view.findViewById(R.id.spinner);
//        database.getDataBook("");
        arrayList.clear();
        showlist("");
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//Toast.makeText(getContext(),"aaa",Toast.LENGTH_LONG).show();

                Log.d("text", edtsearch.getText().toString());
                arrayList.clear();
                adapterSinhVien.notifyDataSetChanged();
//                database.getSearchBook("");
                showlist(edtsearch.getText().toString());
                Toast.makeText(getContext(), "Search OK", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                adapterSinhVien.notifyDataSetChanged();
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
                        builder.setMessage("Can You Detele " + arrayList.get(index).getName() + " Student?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.QueryData(sql);
                                Toast.makeText(getContext(), "Delete OK", Toast.LENGTH_SHORT).show();
                                database.getData("");
                                adapterSinhVien.notifyDataSetChanged();
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
                        adapterSinhVien.notifyDataSetChanged();
                        showlist("");
                        dialog.dismiss();
                    }
                });
                spinner = (Spinner) dialog.findViewById(R.id.spinner);
                edtdialogID.setText("");
                edtdialogPrice.setText("");
                edtdialogTilte.setText("");
                List<Lop> list = new ArrayList<>();
                databaseclass = new SQLite(getContext(), "Class3.sqlite", null, 1);
                list = databaseclass.getDataClassSpinner("");
                ArrayAdapter<Lop> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btndialogup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = edtdialogID.getText().toString();
                        String name = edtdialogTilte.getText().toString();
                        String lop =spinner.getSelectedItem().toString().substring(0,spinner.getSelectedItem().toString().indexOf("-"));
                        String bir = edtdialogPrice.getText().toString();
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                                if (null != rb && checkedId > -1) {
                                    Toast.makeText(getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                        String gender = Gender = rb.getText().toString();
                        final String sql = database.update(id, name, lop, gender, bir, indextext);
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Update");
                            builder.setMessage("Can You Update " + arrayList.get(index).getName() + " to " + name + "  Student?");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    database.QueryData(sql);
                                    Toast.makeText(getContext(), "Update OK", Toast.LENGTH_SHORT).show();
                                    database.getData("");
                                    adapterSinhVien.notifyDataSetChanged();
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
//                arrayList.add(new ClassBook(id,tilte,author,price));
                        database.getData("");
                        arrayList.clear();
                        adapterSinhVien.notifyDataSetChanged();
                        showlist("");
                        dialog.dismiss();

//              database.QueryData(database.addBook(new ClassBook(0,tilte,author,price)));
                    }
                });

                return false;

            }
        });
        adapterSinhVien = new AdapterSinhVien(getActivity(), R.layout.info_sv, arrayList);
        listView.setAdapter(adapterSinhVien);
        return view;
    }


    public void DialogBook() {
        dialog.setTitle("Add Book");
        edtdialogID = (EditText) dialog.findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) dialog.findViewById(R.id.dialogTILTE);
        edtdialogPrice = (EditText) dialog.findViewById(R.id.dialogPRICE);
        btndialogup = (Button) dialog.findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) dialog.findViewById(R.id.dialogbtnDEL);
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup_character);
        radioButtonMale = (RadioButton) dialog.findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) dialog.findViewById(R.id.radioButton_female);
        spinner = (Spinner) dialog.findViewById(R.id.spinner);
        edtdialogID.setText("");
        edtdialogPrice.setText("");
        edtdialogTilte.setText("");
        List<Lop> list = new ArrayList<>();
        databaseclass = new SQLite(getContext(), "Class3.sqlite", null, 1);
        list = databaseclass.getDataClassSpinner("");
        ArrayAdapter<Lop> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btndialogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtdialogID.getText().toString();
                String name = edtdialogTilte.getText().toString();
                String lop =spinner.getSelectedItem().toString().substring(0,spinner.getSelectedItem().toString().indexOf("-"));
                String bir = edtdialogPrice.getText().toString();
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) group.findViewById(checkedId);
                        if (null != rb && checkedId > -1) {
                            Toast.makeText(getContext(), rb.getText(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

                String gender = Gender = rb.getText().toString();
                final String sql = database.add(id, name, lop, gender, bir);
                try {
                    database.QueryData(sql);
                    Toast.makeText(getContext(), "Add OK", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    AlertErrorDialog();
                }

//                arrayList.add(new ClassBook(id,tilte,author,price));
                database.getData("");
                arrayList.clear();
                adapterSinhVien.notifyDataSetChanged();
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










