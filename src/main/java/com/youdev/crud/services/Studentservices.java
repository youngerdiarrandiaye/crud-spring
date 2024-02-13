package com.youdev.crud.services;

import com.youdev.crud.dtos.Studentdto;

import java.util.List;

public interface Studentservices {
    List<Studentdto> getAllStudents();

    Studentdto getStudentsById(Long id);

    Studentdto createStudents(Studentdto studentdto);

    Studentdto updateStudents(Long id, Studentdto studentdto);

    void deleteStudents(Long id);
}
