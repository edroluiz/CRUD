package teacher.api.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teacher.api.personapi.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}