package mesh.e.school_test.repo;

import mesh.e.school_test.models.Teacher;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface TeacherRepository extends CrudRepository <Teacher, Long> {

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndMainSubjectIsContainingAndGenderIdentityIsContainingAndBirthYear(String firstName, String lastName ,String middleName, String mainSubject, String genderIdentity, int birthYear);

    List<Teacher> findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndMainSubjectIsContainingAndGenderIdentityIsContaining(String firstName, String lastName ,String middleName, String mainSubject, String genderIdentity);

    List<Teacher> findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContaining(String firstName, String lastName ,String middleName);


}
