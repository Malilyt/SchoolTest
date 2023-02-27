package mesh.e.school_test.models;


import javax.persistence.*;



@Entity
public class Teacher{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String lastName, firstName, middleName, mainSubject, genderIdentity;
    private int birthYear;



    public Teacher(){

    }


    public Teacher(String lastName, String firstName, String middleName, String mainSubject, String genderIdentity, int birthYear) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.mainSubject = mainSubject;
        this.genderIdentity = genderIdentity;
        this.birthYear = birthYear;
    }

    public Teacher(Teacher teacher) {
        this.lastName = teacher.lastName;
        this.firstName = teacher.firstName;
        this.middleName = teacher.middleName;
        this.mainSubject = teacher.mainSubject;
        this.genderIdentity = teacher.genderIdentity;
        this.birthYear = teacher.birthYear;
    }


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

    public String getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(String mainSubject) {
        this.mainSubject = mainSubject;
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



//Фамилия, Имя, Отчество, год рождения, гендерная принадлежность, основной предмет.