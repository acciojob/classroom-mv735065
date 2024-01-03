package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class StudentRepository {
    List<Student> studentList;
    List<Teacher> teacherList;

    Map<String,List<String>> pair;

    public StudentRepository(){
        this.studentList=new ArrayList<>();
        this.teacherList=new ArrayList<>();
        this.pair=new HashMap<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);

    }


    public void addteacher(Teacher teacher) {

        teacherList.add(teacher);
    }


    public void addStudentTeacherPair(String student, String teacher) {
       if(pair.containsKey(teacher)){
           pair.get(teacher).add(student);
       }
        else
       {
           List<String> list=new ArrayList<>();
           list.add(student);
           pair.put(teacher,list);
       }
    }


    public Student getStudentByName(String name) {
        for(Student student : studentList){
            if(student.getName().equals(name)) return student;
        }
        return null;
    }

    public Teacher getTeacherByName(String name) {

        for(Teacher teacher: teacherList){
            if(teacher.getName().equals(name)) return teacher;
        }
        return null;
    }


    public List<String> getStudentsByTeacherName(String teacher) {

        return pair.get(teacher);

    }


    public List<String> getAllStudents() {
  List<String> list=new ArrayList<>();
        for(Student student : studentList) {
            list.add(student.getName());
        }
return list;
        }

    public void deleteTeacherByName(String teacher) {
        List<String> list=pair.get(teacher);

              for(int i=0;i<studentList.size();i++){
                  String name=(studentList.get(i).getName());
                  if(list.contains(name)){
                      studentList.remove(i);
                      i--;
                  }
              }

      pair.remove(teacher);

        for(int i=0;i< teacherList.size();i++){
            Teacher teacherr=teacherList.get(i);
            if(teacherr.getName().equals(teacher))  teacherList.remove(i);
        }


    }


    public void deleteAllTeachers() {
         while(!teacherList.isEmpty()){
             Teacher teacher=teacherList.get(0);
            deleteTeacherByName(teacher.getName());
        }

    }
}
