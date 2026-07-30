package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.AttendanceDto;
import com.smartexam.backend.entity.Attendance;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.AttendanceRepository;
import com.smartexam.backend.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public AttendanceDto saveAttendance(AttendanceDto attendanceDto) {

        Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return modelMapper.map(savedAttendance, AttendanceDto.class);
    }

    @Override
    public List<AttendanceDto> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found with ID: " + id));

        return modelMapper.map(attendance, AttendanceDto.class);
    }

    @Override
    public AttendanceDto updateAttendance(Long id, AttendanceDto attendanceDto) {

        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found with ID: " + id));

        existingAttendance.setStudentId(attendanceDto.getStudentId());
        existingAttendance.setExamId(attendanceDto.getExamId());
        existingAttendance.setPresent(attendanceDto.getPresent());
        existingAttendance.setLoginTime(attendanceDto.getLoginTime());
        existingAttendance.setLogoutTime(attendanceDto.getLogoutTime());
        existingAttendance.setDuration(attendanceDto.getDuration());

        Attendance updatedAttendance = attendanceRepository.save(existingAttendance);

        return modelMapper.map(updatedAttendance, AttendanceDto.class);
    }

    @Override
    public void deleteAttendance(Long id) {

        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found with ID: " + id));

        attendanceRepository.delete(existingAttendance);
    }
}