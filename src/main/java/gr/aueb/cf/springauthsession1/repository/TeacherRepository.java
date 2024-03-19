package gr.aueb.cf.springauthsession1.repository;

import gr.aueb.cf.springauthsession1.model.Status;
import gr.aueb.cf.springauthsession1.model.Teacher;
import gr.aueb.cf.springauthsession1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.management.relation.Role;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
