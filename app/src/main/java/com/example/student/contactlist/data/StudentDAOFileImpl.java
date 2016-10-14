package com.example.student.contactlist.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2016/10/13.
 */

public class StudentDAOFileImpl implements StudentDAO {
    ArrayList<Student> mylist;
    Context context;
    public StudentDAOFileImpl(Context context)
    {
        this.context = context;
        mylist = new ArrayList<>();

        File f1 = this.context.getFilesDir();
        File readFile = new File(f1, "mydata.txt");
        try {
            FileReader fr = new FileReader(readFile.getAbsoluteFile());
            char[] buffer = new char[1];
            StringBuilder sb = new StringBuilder();
            while (fr.read(buffer) != -1)
            {
                sb.append(new String(buffer));
            }
            Gson gson = new GsonBuilder().create();
            mylist = gson.fromJson(sb.toString(),  new TypeToken<ArrayList<Student>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List getList() {
        return mylist;
    }

    @Override
    public void add(Student s) {
        mylist.add(s);
        saveFile();

    }

    @Override
    public void update(Student s) {
        for (Student item: mylist){
            if (s.ID == item.ID){
                item.Name = s.Name;
                item.Tel = s.Tel;
                item.Addr = s.Addr;
                break;
            }
        }
        saveFile();
    }

    @Override
    public void delete(Student s) {
        for (int i = mylist.size()-1; i>=0; i--){
            if (s.ID == mylist.get(i).ID){
                mylist.remove(i);
                break;
            }
        }
        saveFile();
    }

    @Override
    public Student getItem(int ID) {
        for (Student s: mylist){
            if (s.ID == ID)
            {
                return s;
            }
        }
        return null;
    }
    public void saveFile () {
        Gson gson = new GsonBuilder().create();
        String jsonStr = gson.toJson(mylist,  new TypeToken<ArrayList<Student>>(){}.getType());
        File f1 = this.context.getFilesDir();
        File writeFile = new File(f1, "mydata.txt");
        try {
            FileWriter fw = new FileWriter(writeFile.getAbsoluteFile());
            fw.write(jsonStr);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}