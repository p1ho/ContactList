package com.example.student.contactlist.data;

import java.util.List;

/**
 * Created by student on 2016/10/13.
 */

public interface StudentDAO {
    public List getList();
    public void add(Student s);
    public void update(Student s);
    public void delete(Student s);
    Student getItem(int ID);
}
