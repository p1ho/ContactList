package com.example.student.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.student.contactlist.data.Student;
import com.example.student.contactlist.data.StudentDAOFileImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Student> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
//        StudentDAOFileImpl impl = new StudentDAOFileImpl(MainActivity.this);
//        impl.add(new Student("AAA", "111", "AA11"));
//        ArrayList<Student> mylist = (ArrayList<Student>) impl.getList();
//        for (Student s : mylist)
//        {
//            Log.d("DATA", s.toString());
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("ADD");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(MainActivity.this, AddStudent.class);
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StudentDAOFileImpl impl = new StudentDAOFileImpl(this);
        mylist = (ArrayList<Student>) impl.getList();
        String names[] = new String[mylist.size()];
        for (int i = 0; i < mylist.size(); i++){
            names[i] = mylist.get(i).Name;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, UpdateStudent.class);
                int ID = mylist.get(position).ID;
                it.putExtra("ID", ID);
                startActivity(it);
            }
        });
    }

}
