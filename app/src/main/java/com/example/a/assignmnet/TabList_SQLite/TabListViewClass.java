package com.example.a.assignmnet.TabList_SQLite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.assignmnet.Adapter.AdapterLop;
import com.example.a.assignmnet.Class.Lop;
import com.example.a.assignmnet.Main_Screen.MainActivityLogin;
import com.example.a.assignmnet.R;
import com.example.a.assignmnet.SQL.SQLite;

import java.util.ArrayList;

public class TabListViewClass extends Fragment {
    private static final String TAG = "Tab2Fragment";
    ListView listView;
    public ArrayList<Lop> arrayList;
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
    RadioGroup radioGroup;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    String Gender;
    Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_view_class, container, false);
        //AX
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_dialog);
        TabListViewSV sv = new TabListViewSV();
        database = new SQLite(getContext(), "Class3.sqlite", null, 1);
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
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_character);
        radioButtonMale = (RadioButton) view.findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) view.findViewById(R.id.radioButton_female);
        txtdialogname = (TextView) dialog.findViewById(R.id.dialogName);
//        database.getDataClassBook("");
        arrayList.clear();
        showlist("");
        MainActivityLogin login =new MainActivityLogin();
        if(login.admin==0){
            btnadd.setVisibility(View.INVISIBLE);
        }
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//Toast.makeText(getContext(),"aaa",Toast.LENGTH_LONG).show();

                arrayList.clear();
                adapterLop.notifyDataSetChanged();
//                database.getSearchBook("");
                showlist(edtsearch.getText().toString());
//                Toast.makeText(getContext(), "Search OK", Toast.LENGTH_SHORT).show();

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
                adapterLop.notifyDataSetChanged();
//                database.getSearchBook("");
                showlist(edtsearch.getText().toString());
                Toast.makeText(getContext(), "Search OK", Toast.LENGTH_SHORT).show();
            }
        });
        if(login.admin==0){
           listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   return false;
               }
           });
        }else {
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final int index = i;
                    Lop a = new Lop();
                    final String indextext = arrayList.get(index).getName() + "";
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
                            final String sql = database.deleteClass(indextext);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Delete");
                            builder.setMessage("Can You Detele " + arrayList.get(index).getName() + " class?");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    database.QueryData(sql);
                                    Toast.makeText(getContext(), "Delete OK", Toast.LENGTH_SHORT).show();
                                    database.getDataClass("");
                                    adapterLop.notifyDataSetChanged();
                                }

                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                            database.getDataClass("");
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
                            final String sql = database.updateClass(id, tilte, indextext);
                            if (edtdialogID.getText().toString().isEmpty() || edtdialogTilte.getText().toString().isEmpty() ) {
                                edtdialogID.setHint("Input ID ");
                                edtdialogTilte.setHint("Input Name");
                                edtdialogID.setHintTextColor(Color.RED);
                                edtdialogTilte.setHintTextColor(Color.RED);
                            } else {
                                try {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Update");
                                    builder.setMessage("Can You Update " + arrayList.get(index).getName() + " to " + tilte + "  class?");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            database.QueryData(sql);
                                            Toast.makeText(getContext(), "Update OK", Toast.LENGTH_SHORT).show();
                                            database.getDataClass("");
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
//                arrayList.add(new ClassBook(idid),tilte,author,price));
                                database.getDataClass("");
                                arrayList.clear();
                                adapterLop.notifyDataSetChanged();
                                showlist("");
                                dialog.dismiss();
                            }


//              database.QueryData(database.addBook(new ClassBook(0,tilte,author,price)));
                        }
                    });

                    return false;

                }
            });
        }
        adapterLop = new AdapterLop(getActivity(), R.layout.info_sv, arrayList);
        listView.setAdapter(adapterLop);
        return view;
    }


    public void DialogBook() {
        dialog.setTitle("Add Class");
        edtdialogID = (EditText) dialog.findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) dialog.findViewById(R.id.dialogTILTE);
        edtdialogPrice = (EditText) dialog.findViewById(R.id.dialogPRICE);
        btndialogup = (Button) dialog.findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) dialog.findViewById(R.id.dialogbtnDEL);
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup_character);
        radioButtonMale = (RadioButton) dialog.findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) dialog.findViewById(R.id.radioButton_female);
        edtdialogID.setText("");
        edtdialogPrice.setText("");
        radioGroup.setVisibility(View.INVISIBLE);
        radioButtonMale.setVisibility(View.INVISIBLE);
        radioButtonFemale.setVisibility(View.INVISIBLE);
        edtdialogPrice.setVisibility(View.INVISIBLE);
        spinner = (Spinner) dialog.findViewById(R.id.spinner);
        spinner.setVisibility(View.INVISIBLE);
        btndialogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtdialogID.getText().toString();
                String tilte = edtdialogTilte.getText().toString();
//
                final String sql = database.addClass(id, tilte);
                if (edtdialogID.getText().toString().isEmpty() || edtdialogTilte.getText().toString().isEmpty() ) {
                    edtdialogID.setHint("Input ID Class ");
                    edtdialogTilte.setHint("Input Name Class");
                    edtdialogID.setHintTextColor(Color.RED);
                    edtdialogTilte.setHintTextColor(Color.RED);

                } else {
                    try {
                        database.QueryData(sql);
                        Toast.makeText(getContext(), "Add OK", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        AlertErrorDialog();
                    }
//                arrayList.add(new ClassBook(idid),tilte,author,price));
                    database.getDataClass("");
                    arrayList.clear();
                    adapterLop.notifyDataSetChanged();
                    showlist("");
                    dialog.dismiss();
                }

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
            arrayList = database.getDataClass("");
        } else {
            arrayList = database.getSearchClass(edtsearch.getText().toString());
        }


    }


}





