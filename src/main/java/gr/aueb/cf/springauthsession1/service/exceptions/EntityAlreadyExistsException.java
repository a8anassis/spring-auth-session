package gr.aueb.cf.springauthsession1.service.exceptions;

import java.io.Serial;

public class EntityAlreadyExistsException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(Class<?> entityClass, Long id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " already exists.");
    }
}
