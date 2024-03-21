package gr.aueb.cf.springauthsession1.service;

import gr.aueb.cf.springauthsession1.dto.RegisterTeacherDTO;
import gr.aueb.cf.springauthsession1.mapper.Mapper;
import gr.aueb.cf.springauthsession1.model.Teacher;
import gr.aueb.cf.springauthsession1.model.User;
import gr.aueb.cf.springauthsession1.repository.TeacherRepository;
import gr.aueb.cf.springauthsession1.repository.UserRepository;
import gr.aueb.cf.springauthsession1.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.springauthsession1.service.exceptions.TeacherAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;


    @Override
    public List<Teacher> findAllTeachers() throws Exception {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public Teacher registerTeacher(RegisterTeacherDTO dto) throws TeacherAlreadyExistsException {
        Teacher teacher;
        User user;

        try {
            teacher = Mapper.extractTeacherFromRegisterTeacherDTO(dto);
            user = Mapper.extractUserFromRegisterTeacherDTO(dto);


            Optional<User> returnedUser = userRepository.findByUsername(dto.getUsername());
                  //  .orElseThrow(() -> new TeacherAlreadyExistsException(Teacher.class, dto.getUsername()));

            // insert is not idempotent, so we have to check
            if (returnedUser.isPresent()) throw new TeacherAlreadyExistsException(Teacher.class, dto.getUsername());

            teacher.addUser(user);                      // Convenient method
            teacherRepository.save(teacher);            // CRUD Repository method
            log.info("Teacher added");
        } catch (TeacherAlreadyExistsException e) {
            log.error(e.getMessage());
            throw e;
        }

        return teacher;
    }
}
