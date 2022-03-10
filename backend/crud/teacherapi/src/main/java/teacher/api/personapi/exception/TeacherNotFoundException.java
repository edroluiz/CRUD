package teacher.api.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends Exception {
    public TeacherNotFoundException(Long id) {
        super("Teacher not found with ID " + id);
    }
}
