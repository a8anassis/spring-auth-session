package gr.aueb.cf.springauthsession1.repository;

import gr.aueb.cf.springauthsession1.model.Status;
import gr.aueb.cf.springauthsession1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.management.relation.Role;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
    Long countByRole(Role role);
    Long countByRoleAndStatus(Role role, Status status);

}
