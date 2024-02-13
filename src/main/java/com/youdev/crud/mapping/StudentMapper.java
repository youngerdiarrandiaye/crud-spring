package com.youdev.crud.mapping;

import com.youdev.crud.dtos.Studentdto;
import com.youdev.crud.entities.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Studentdto fromStudent(Student student){
        Studentdto studentdto= new Studentdto();
        BeanUtils.copyProperties(student,studentdto);
        return studentdto;
    }
    public Student fromStudentDTO(Studentdto studentdto){
        Student student= new Student();
        BeanUtils.copyProperties(studentdto,student);
        return student;
    }


}
