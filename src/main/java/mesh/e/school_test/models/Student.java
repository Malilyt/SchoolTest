package mesh.e.school_test.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

    @Id
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    private String lastName, firstName, middleName, genderIdentity;
    private int birthYear;




    public Student(Student student){
        this.lastName = student.lastName;
        this.firstName = student.firstName;
        this.middleName = student.middleName;
        this.genderIdentity = student.genderIdentity;
        this.birthYear = student.birthYear;

    }

    public Student(String lastName, String firstName, String middleName, String genderIdentity, int birthYear) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.genderIdentity = genderIdentity;
        this.birthYear = birthYear;
    }

    public Student(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGenderIdentity() {
        return genderIdentity;
    }

    public void setGenderIdentity(String genderIdentity) {
        this.genderIdentity = genderIdentity;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

}
