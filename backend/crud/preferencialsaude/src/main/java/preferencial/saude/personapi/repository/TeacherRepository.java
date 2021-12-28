package preferencial.saude.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preferencial.saude.personapi.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}