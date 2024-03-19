package gr.aueb.cf.springauthsession1.service;

import gr.aueb.cf.springauthsession1.dto.RegisterTeacherDTO;
import gr.aueb.cf.springauthsession1.model.Teacher;
import gr.aueb.cf.springauthsession1.model.User;
import gr.aueb.cf.springauthsession1.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.springauthsession1.service.exceptions.TeacherAlreadyExistsException;

import java.util.List;


public interface ITeacherService {
    List<Teacher> findAllTeachers () throws Exception;
    Teacher registerTeacher(RegisterTeacherDTO dto) throws TeacherAlreadyExistsException;
}
