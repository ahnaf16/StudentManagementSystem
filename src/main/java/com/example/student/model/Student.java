package com.example.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable, Comparable<Student> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String department;
    private Double cgpa;

    @Override
    public int compareTo(Student other) {
        if (this.id == null || other.id == null) {
            return 0;
        }
        return this.id.compareTo(other.id);
    }
}
