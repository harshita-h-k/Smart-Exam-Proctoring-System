package com.smartexam.backend.service;

import com.smartexam.backend.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {

    AttendanceDto saveAttendance(AttendanceDto attendanceDto);

    List<AttendanceDto> getAllAttendance();

    AttendanceDto getAttendanceById(Long id);

    AttendanceDto updateAttendance(Long id, AttendanceDto attendanceDto);

    void deleteAttendance(Long id);
}