package mesh.e.school_test.repo;

import mesh.e.school_test.models.SchoolClass;
import mesh.e.school_test.models.Student;
import mesh.e.school_test.models.Teacher;
import org.springframework.data.repository.CrudRepository;
import java.util.List;



public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
    List<SchoolClass> findAll ();

    List<SchoolClass> findByStudentClass(Student student);

    List<SchoolClass> findByMainTeacher(Teacher teachrt);

    List<SchoolClass> findBymCodeIsContainingAndYearEducation(String mC, int yE);

    List<SchoolClass> findBymCode(String mC);
}
