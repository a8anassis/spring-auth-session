package gr.aueb.cf.springauthsession1.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String username;
    private String password;

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;


    public User(Long id) {
        this.id = id;
        setIsActive(true);
    }

    public static User NEW_USER(Role role, String password) {
        User user = new User();
        user.setIsActive(true);
        user.setRole(role);
        user.setStatus(Status.PENDING);
        user.setPassword(password);
        return user;
    }

    public static User NEW_TEACHER(String username, String password) {
        User user = new User();
        user.setIsActive(true);
        user.setRole(Role.TEACHER);
        user.setStatus(Status.APPROVED);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static User NEW_STUDENT(String username, String password) {
        User user = new User();
        user.setIsActive(true);
        user.setRole(Role.STUDENT);
        user.setStatus(Status.APPROVED);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getIsActive() == null || this.getIsActive();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
