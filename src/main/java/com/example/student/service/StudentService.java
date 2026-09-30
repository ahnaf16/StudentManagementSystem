package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.model.StudentCgpaComparator;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private Stack<Student> recentStudents = new Stack<>();
    private static final String FILE_NAME = "students.ser";

    @PostConstruct
    public void init() {
        loadData();
    }

    public Student addStudent(Student student) {
        students.add(student);
        
        // Push to stack for last three added students
        recentStudents.push(student);
        if (recentStudents.size() > 3) {
            recentStudents.remove(0); // Remove the oldest to keep only the last 3
        }
        
        saveData();
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> resultList = new ArrayList<>();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        return resultList;
    }

    public List<Student> sortById() {
        List<Student> sortedList = new ArrayList<>(students);
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<Student> sortByCgpa() {
        List<Student> sortedList = new ArrayList<>(students);
        sortedList.sort(new StudentCgpaComparator());
        return sortedList;
    }

    public Set<String> getUniqueDepartments() {
        Set<String> uniqueDepartments = new HashSet<>();
        for (Student student : students) {
            if (student.getDepartment() != null && !student.getDepartment().trim().isEmpty()) {
                uniqueDepartments.add(student.getDepartment());
            }
        }
        return uniqueDepartments;
    }

    public Student peekRecentStudent() {
        if (recentStudents.isEmpty()) {
            return null;
        }
        return recentStudents.peek();
    }

    public Student popRecentStudent() {
        if (recentStudents.isEmpty()) {
            return null;
        }
        return recentStudents.pop();
    }
    
    public List<Student> getRecentStudents() {
        return new ArrayList<>(recentStudents);
    }

    private void saveData() {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                students = (List<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
