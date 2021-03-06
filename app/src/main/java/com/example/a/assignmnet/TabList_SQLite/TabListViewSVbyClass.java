package com.example.a.assignmnet.TabList_SQLite;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.assignmnet.Adapter.AdapterSinhVien;
import com.example.a.assignmnet.Class.Lop;
import com.example.a.assignmnet.Class.SinhVien;
import com.example.a.assignmnet.Main_Screen.MainActivity;
import com.example.a.assignmnet.R;
import com.example.a.assignmnet.SQL.SQLite;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;
import static com.example.a.assignmnet.R.id.imageView;

public class TabListViewSVbyClass extends AppCompatActivity {
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
    ImageButton imageButton;
    ImageView imageV;
    Bitmap imagebipmap;
    TextView txtSBL;


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_studentbyclass);
        //AX
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        database = new SQLite(TabListViewSVbyClass.this, "Student10.sqlite", null, 1);
        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listviewbook);
        btnadd = (Button) findViewById(R.id.btnAdd);
        edtsearch = (EditText) findViewById(R.id.edtSearch);
        edtdialogID = (EditText) findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) findViewById(R.id.dialogTILTE);
        edtdialogPrice = (EditText) findViewById(R.id.dialogPRICE);
        btndialogup = (Button) findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) findViewById(R.id.dialogbtnDEL);
        btnsearch = (Button) findViewById(R.id.btnSearch);
        txtdialogname = (TextView) dialog.findViewById(R.id.dialogName);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_character);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButton_female);
        spinner = (Spinner) findViewById(R.id.spinner);
        txtSBL = (TextView) findViewById(R.id.txtbystudentbyclass);
        arrayList.clear();
        Intent mIntent = new Intent(this, MainActivity.class);
        mIntent.putExtra("studentbyclass", value);
        String value = getIntent().getExtras().getString("studentbyclass");
        txtSBL.setText("Student in Class: PT" + value);
        arrayList = database.getSearchStudentByClass(value);
        adapterSinhVien = new AdapterSinhVien(TabListViewSVbyClass.this, R.layout.info_sv, arrayList);
        listView.setAdapter(adapterSinhVien);
        Log.d("value", value);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = i;
                SinhVien a = new SinhVien();
                final String indextext = arrayList.get(index).getId_table() + "";
                Log.d("indextxt", index + "  " + indextext);
                txtdialogname = (TextView) dialog.findViewById(R.id.dialogName);
//                final String namedialog=indextext.substring(indextext.indexOf("\n")+1,indextext.indexOf("\n"));
                DialogBook();
                btndialogup.setText("Update");
                btndialogdel.setVisibility(View.VISIBLE);
                dialog.setTitle("Update and Delete");
                txtdialogname.setText(arrayList.get(index).getName());
                btndialogdel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String sql = database.delete(indextext);
                        AlertDialog.Builder builder = new AlertDialog.Builder(TabListViewSVbyClass.this);
                        builder.setTitle("Delete");
                        builder.setMessage("Can You Detele " + arrayList.get(index).getName() + " Student?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.QueryData(sql);
                                Toast.makeText(TabListViewSVbyClass.this, "Delete OK", Toast.LENGTH_SHORT).show();
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
                        dialog.dismiss();
                    }
                });
                spinner = (Spinner) dialog.findViewById(R.id.spinner);
                edtdialogID.setText(arrayList.get(index).getId());
                edtdialogPrice.setText(arrayList.get(index).getBirthday());
                edtdialogTilte.setText(arrayList.get(index).getName());
                List<Lop> list = new ArrayList<>();
                databaseclass = new SQLite(TabListViewSVbyClass.this, "Class3.sqlite", null, 1);
                list = databaseclass.getDataClassSpinner("");
                ArrayAdapter<Lop> adapter = new ArrayAdapter(TabListViewSVbyClass.this, android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(TabListViewSVbyClass.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btndialogup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String id = edtdialogID.getText().toString();
                        final String name = edtdialogTilte.getText().toString();
                        final String lop = spinner.getSelectedItem().toString().substring(0, spinner.getSelectedItem().toString().indexOf("-"));
                        final String bir = edtdialogPrice.getText().toString();
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                                if (null != rb && checkedId > -1) {
                                    Toast.makeText(TabListViewSVbyClass.this, rb.getText(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                        final String gender = Gender = rb.getText().toString();
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageV.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        final byte[] Image = outputStream.toByteArray();
//                            final String sql = database.update(id, name, lop, gender, bir, indextext);
                        if (edtdialogID.getText().toString().isEmpty() || edtdialogTilte.getText().toString().isEmpty() || edtdialogPrice.getText().toString().isEmpty()) {
                            edtdialogID.setHint("Input ID ");
                            edtdialogPrice.setHint("Input Birthday Year");
                            edtdialogTilte.setHint("Input Name");
                            edtdialogID.setHintTextColor(Color.RED);
                            edtdialogTilte.setHintTextColor(Color.RED);
                            edtdialogPrice.setHintTextColor(Color.RED);

                        } else {
                            try {
                                AlertDialog.Builder builder = new AlertDialog.Builder(TabListViewSVbyClass.this);
                                builder.setTitle("Update");
                                builder.setMessage("Can You Update " + arrayList.get(index).getName() + " to " + name + "  Student?");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                            database.QueryData(sql);
                                        database.updateStu(id, Image, name, lop, gender, bir, indextext);
                                        Toast.makeText(TabListViewSVbyClass.this, "Update OK", Toast.LENGTH_SHORT).show();
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

                            dialog.dismiss();
                        }


//              database.QueryData(database.addBook(new ClassBook(0,tilte,author,price)));
                    }
                });
                return false;
//
            }
        });
    }


    public void DialogBook() {
        dialog.setTitle("Add Student");
        edtdialogID = (EditText) dialog.findViewById(R.id.dialogID);
        edtdialogTilte = (EditText) dialog.findViewById(R.id.dialogTILTE);
        edtdialogPrice = (EditText) dialog.findViewById(R.id.dialogPRICE);
        btndialogup = (Button) dialog.findViewById(R.id.dialogbtnUP);
        btndialogdel = (Button) dialog.findViewById(R.id.dialogbtnDEL);
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup_character);
        radioButtonMale = (RadioButton) dialog.findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) dialog.findViewById(R.id.radioButton_female);
        spinner = (Spinner) dialog.findViewById(R.id.spinner);
        imageButton = (ImageButton) dialog.findViewById(R.id.btnImage);
        imageV = (ImageView) dialog.findViewById(imageView);
        edtdialogID.setText("");
        edtdialogPrice.setText("");
        edtdialogTilte.setText("");
        List<Lop> list = new ArrayList<>();
        databaseclass = new SQLite(TabListViewSVbyClass.this, "Class3.sqlite", null, 1);
        list = databaseclass.getDataClassSpinner("");
        final ArrayAdapter<Lop> adapter = new ArrayAdapter(TabListViewSVbyClass.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TabListViewSVbyClass.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
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
                String lop = spinner.getSelectedItem().toString().substring(0, spinner.getSelectedItem().toString().indexOf("-"));
                String bir = edtdialogPrice.getText().toString();
                imageButton = (ImageButton) dialog.findViewById(R.id.btnImage);

                imageV = (ImageView) dialog.findViewById(imageView);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) group.findViewById(checkedId);
                        if (null != rb && checkedId > -1) {
                            Toast.makeText(TabListViewSVbyClass.this, rb.getText(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String gender = Gender = rb.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageV.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[]Image = outputStream.toByteArray();
//                final String sql = database.addStu(id,Image, name, lop, gender, bir);

                if (edtdialogID.getText().toString().isEmpty() || edtdialogTilte.getText().toString().isEmpty() || edtdialogPrice.getText().toString().isEmpty()) {
                    edtdialogID.setHint("Input ID ");
                    edtdialogPrice.setHint("Input Birthday Year");
                    edtdialogTilte.setHint("Input Name");
                    edtdialogID.setHintTextColor(Color.RED);
                    edtdialogTilte.setHintTextColor(Color.RED);
                    edtdialogPrice.setHintTextColor(Color.RED);

                } else {
                    try {
                        database.addStu(id,Image, name, lop, gender, bir);
//                        database.QueryData(sql);
                        Toast.makeText(TabListViewSVbyClass.this, "Add OK", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Log.d("error",ex.toString());
                        AlertErrorDialog();
                    }

//                arrayList.add(new ClassBook(id,tilte,author,price));
                    database.getData("");
                    arrayList.clear();
                    adapterSinhVien.notifyDataSetChanged();

                    dialog.dismiss();
                }

            }

//              database.QueryData(database.addBook(new ClassBook(0,tilte,author,price)));

        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To Gallery
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 999);
            }

        });
        dialog.show();
    }
//
//
//
//
    public void AlertErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TabListViewSVbyClass.this);
        builder.setTitle("Error");
        builder.setMessage("ID Exist, Please input other Id.Tks");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


//
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == Activity.RESULT_OK) {
            try {
                InputStream in = TabListViewSVbyClass.this.getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                imagebipmap = bitmap;
                imageV.setImageBitmap(imagebipmap);
                in.close();

            } catch (Exception e) {
            }
        }
    }

//    public static byte[] imageViewToByte(ImageView image) {
//        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        return byteArray;
//    }
//
}















