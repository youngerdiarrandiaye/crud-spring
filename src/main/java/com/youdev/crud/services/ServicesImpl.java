package com.youdev.crud.services;

import com.youdev.crud.dtos.Studentdto;
import com.youdev.crud.entities.Student;
import com.youdev.crud.mapping.StudentMapper;
import com.youdev.crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicesImpl implements Studentservices{
    private final StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public ServicesImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Studentdto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::fromStudent)
                .collect(Collectors.toList());
    }

    @Override
    public Studentdto getStudentsById(Long id) {
        Optional<Student> StudentOptional = studentRepository.findById(id);
        return StudentOptional.map(studentMapper::fromStudent).orElse(null);
    }

    @Override
    public Studentdto createStudents(Studentdto studentdto) {
        Student student = studentMapper.fromStudentDTO(studentdto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.fromStudent(savedStudent);
    }

    @Override
    public Studentdto updateStudents(Long id, Studentdto studentdto) {
        Optional<Student> StudentOptional = studentRepository.findById(id);
        if (StudentOptional.isPresent()) {
            Student existingStudent = StudentOptional.get();
            // Update fields from DTO
            existingStudent.setNom(studentdto.getNom());
            existingStudent.setPrenom(studentdto.getPrenom());
            // Save and return updated Student
            Student updatedStudent = studentRepository.save(existingStudent);
            return studentMapper.fromStudent(updatedStudent);
        } else {
            return null; // Student not found
        }
    }

    @Override
    public void deleteStudents(Long id) {
        studentRepository.deleteById(id);
    }
}
