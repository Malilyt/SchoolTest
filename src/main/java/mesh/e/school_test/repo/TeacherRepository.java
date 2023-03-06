package mesh.e.school_test.repo;

import mesh.e.school_test.models.SchoolClass;
import mesh.e.school_test.models.Teacher;
import org.hibernate.Criteria;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


import java.util.List;



public interface TeacherRepository extends CrudRepository <Teacher, Long>, JpaSpecificationExecutor {


    List<Teacher> findBySchoolClass (SchoolClass schoolClass);
    List<Teacher> findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContaining(String firstName, String lastName ,String middleName);


}
