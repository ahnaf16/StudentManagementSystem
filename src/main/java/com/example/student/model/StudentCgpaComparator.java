package com.example.student.model;

import java.util.Comparator;

public class StudentCgpaComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getCgpa() == null || s2.getCgpa() == null) {
            return 0;
        }
        // Descending order
        return s2.getCgpa().compareTo(s1.getCgpa());
    }
}
