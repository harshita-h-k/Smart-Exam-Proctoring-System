package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.FacultyDto;
import com.smartexam.backend.entity.Faculty;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.FacultyRepository;
import com.smartexam.backend.service.FacultyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapper modelMapper;

    public FacultyServiceImpl(FacultyRepository facultyRepository,
                              ModelMapper modelMapper) {
        this.facultyRepository = facultyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto) {

        Faculty faculty = modelMapper.map(facultyDto, Faculty.class);

        Faculty savedFaculty = facultyRepository.save(faculty);

        return modelMapper.map(savedFaculty, FacultyDto.class);
    }

    @Override
    public FacultyDto getFacultyById(Long id) {

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Faculty not found with id: " + id));

        return modelMapper.map(faculty, FacultyDto.class);
    }

    @Override
    public List<FacultyDto> getAllFaculties() {

        return facultyRepository.findAll()
                .stream()
                .map(faculty -> modelMapper.map(faculty, FacultyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDto updateFaculty(Long id, FacultyDto facultyDto) {

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Faculty not found with id: " + id));

        faculty.setFacultyId(facultyDto.getFacultyId());
        faculty.setFirstName(facultyDto.getFirstName());
        faculty.setLastName(facultyDto.getLastName());
        faculty.setEmail(facultyDto.getEmail());
        faculty.setPhoneNumber(facultyDto.getPhoneNumber());
        faculty.setDepartment(facultyDto.getDepartment());
        faculty.setDesignation(facultyDto.getDesignation());
        faculty.setQualification(facultyDto.getQualification());
        faculty.setExperience(facultyDto.getExperience());
        faculty.setActive(facultyDto.getActive());

        Faculty updatedFaculty = facultyRepository.save(faculty);

        return modelMapper.map(updatedFaculty, FacultyDto.class);
    }

    @Override
    public void deleteFaculty(Long id) {

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Faculty not found with id: " + id));

        facultyRepository.delete(faculty);
    }
}