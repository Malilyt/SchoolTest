package mesh.e.school_test.repo;

import mesh.e.school_test.models.SchoolClass;
import mesh.e.school_test.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.*;


public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {

    List<SchoolClass> findAll();

    Collection<SchoolClass> findByStudent(Student student);

    List<SchoolClass> findBymCodeIsContainingAndYearEducation(String mC, int yE);

    List<SchoolClass> findBymCode(String mC);
}
