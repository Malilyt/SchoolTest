package mesh.e.school_test.repo;

import mesh.e.school_test.models.Student;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {


    List<Student> findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndGenderIdentityIsContainingAndBirthYear(String firstName, String lastName , String middleName,  String genderIdentity, int birthYear);

    List<Student> findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndGenderIdentityIsContaining(String firstName, String lastName ,String middleName, String genderIdentity);
}
