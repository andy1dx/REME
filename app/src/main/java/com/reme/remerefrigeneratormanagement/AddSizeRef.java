package com.reme.remerefrigeneratormanagement;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSizeRef extends AppCompatActivity {
    EditText Name;

    ListView sizeList;
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    remeDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_size_ref);
        Name= (EditText) findViewById(R.id.text_name);
        sizeList= (ListView) findViewById(R.id.list_view_1);

        helper = new remeDbAdapter(this);

        listitem = new ArrayList<>();
        viewSizeList();
        sizeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = sizeList.getItemAtPosition(position).toString();
                Toast.makeText(AddSizeRef.this, ""+ text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addsize(View view) {
        String name_size = Name.getText().toString();
        if(name_size.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else
        {
            long id = helper.insertSize(name_size,"1");
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                Name.setText("");
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
                Name.setText("");
                listitem.clear();
                viewSizeList();
            }
        }
    }
    public void viewSizeData(View view)
    {
        String data = helper.getSizeData();
        Message.message(this,data);
    }

    public void viewSizeList() {
        Cursor cursor = helper.viewSizeData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listitem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listitem);
            sizeList.setAdapter(adapter);
        }
    }
}
