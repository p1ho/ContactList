package com.example.student.contactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.student.contactlist.data.Student;
import com.example.student.contactlist.data.StudentDAOFileImpl;

import java.util.ArrayList;

public class UpdateStudent extends AppCompatActivity {
    Student s;
    ArrayList<Student> mylist;
    EditText editText4, editText5, editText6;
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        StudentDAOFileImpl impl = new StudentDAOFileImpl(this);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        mylist = (ArrayList<Student>) impl.getList();
        ID = getIntent().getIntExtra("ID", 0);
        s = impl.getItem(ID);
        editText4.setText(s.Name);
        editText5.setText(s.Tel);
        editText6.setText(s.Addr);
    }
    public void clickSave(View v){
        Student t = new Student(ID, editText4.getText().toString(), editText5.getText().toString(), editText6.getText().toString());
        StudentDAOFileImpl impl = new StudentDAOFileImpl(this);
        impl.update(t);
        finish();
    }
    public void clickCancel(View v){
        finish();
    }
    public void clickDelete(View v){
        Student t = new Student(ID, editText4.getText().toString(), editText5.getText().toString(), editText6.getText().toString());
        StudentDAOFileImpl impl = new StudentDAOFileImpl(this);
        impl.delete(t);
        finish();
    }
}
