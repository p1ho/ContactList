package com.example.student.contactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.student.contactlist.data.Student;
import com.example.student.contactlist.data.StudentDAOFileImpl;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {
    EditText editText, editText2, editText3;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
    }
    public void clickAdd (View v){
        StudentDAOFileImpl impl = new StudentDAOFileImpl(AddStudent.this);
        ArrayList<Student> mylist = (ArrayList<Student>) impl.getList();
        int MaxID = 0;
        for (Student s: mylist){
            if (s.ID > MaxID){
                MaxID = s.ID;
            }
        }
        MaxID++;
        impl.add(new Student(MaxID, editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString()));
        finish();
    }
}
