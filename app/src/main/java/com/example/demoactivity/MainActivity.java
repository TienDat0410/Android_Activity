package com.example.demoactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
//    private String[] listData;
    private ArrayList<String> listData;
    private ArrayAdapter<String> adapter;
    private Context context;
    private Button button, btncapnhat;
    private EditText editText;
    private int vitri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        listView = (ListView) findViewById(R.id.lv);
        button = (Button)findViewById(R.id.btn);
        editText =(EditText)findViewById(R.id.edit);
        btncapnhat = (Button)findViewById(R.id.btncapnhat);
//
//        listData = context.getResources().getStringArray(R.array.listVideo);
//        listData = new String[] {
//                "Bài 1: Giới thiệu Android",
//                "Bài 2: Cài đặt môi trường lập trình"
//        };
        listData = new ArrayList<>();
        listData.add("Bài 1: Giới thiệu Android");
        listData.add("Bài 2: Cài đặt môi trường lập trình");
        listData.add("Bài 3:Tạo project HelloWorld");
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString().trim();
                if(TextUtils.isEmpty(item))
                {
                    Toast.makeText(context, "Bạn chưa nhập dữ liệu",Toast.LENGTH_SHORT).show();
                    return;
                }
                listData.add(item);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(context, listData.get(position), Toast.LENGTH_SHORT).show();
                editText.setText(listData.get(position));
                vitri = position;
            }
        });

        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData.set(vitri, editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("xác nhận");
                dialog.setMessage("Bạn có đồng ý xoá không");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listData.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });

                dialog.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                return false;
            }
        });
    }

}