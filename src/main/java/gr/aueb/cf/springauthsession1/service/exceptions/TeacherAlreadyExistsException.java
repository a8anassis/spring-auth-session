package gr.aueb.cf.springauthsession1.service.exceptions;

import java.io.Serial;

public class TeacherAlreadyExistsException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherAlreadyExistsException(Class<?> entityClass, String username) {
        super("Entity " + entityClass.getSimpleName() + " with username " + username + " already exists.");
    }
}
