package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.StudentDto;
import com.smartexam.backend.entity.Student;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.StudentRepository;
import com.smartexam.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {

        Student student = modelMapper.map(studentDto, Student.class);

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with ID: " + id));

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with ID: " + id));

        existingStudent.setName(studentDto.getName());
        existingStudent.setRegisterNumber(studentDto.getRegisterNumber());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setDepartment(studentDto.getDepartment());
        existingStudent.setYear(studentDto.getYear());
        existingStudent.setPhoneNumber(studentDto.getPhoneNumber());

        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with ID: " + id));

        studentRepository.delete(existingStudent);
    }
}