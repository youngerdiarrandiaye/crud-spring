package com.youdev.crud.services;

import com.youdev.crud.dtos.Studentdto;
import com.youdev.crud.entities.Student;
import com.youdev.crud.mapping.StudentMapper;
import com.youdev.crud.repository.StudentRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "students")
public class ServicesImpl implements Studentservices{
    private final StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public ServicesImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Studentdto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::fromStudent)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public Studentdto getStudentsById(Long id) {
        Optional<Student> StudentOptional = studentRepository.findById(id);
        return StudentOptional.map(studentMapper::fromStudent).orElse(null);
    }

    @Override
    @Transactional
    public Studentdto createStudents(Studentdto studentdto) {
        Student student = studentMapper.fromStudentDTO(studentdto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.fromStudent(savedStudent);
    }

    @Override
    @CachePut(key = "#id")
    @Transactional
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
    @CacheEvict(key = "#id")
    @Transactional
    public void deleteStudents(Long id) {
        studentRepository.deleteById(id);
    }
}
