package mesh.e.school_test.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "school_class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String mCode;

    private int yearEducation;



    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Teacher mainTeacher;

    public List<Student> getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(List<Student> studentClass) {
        this.studentClass = studentClass;
    }

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "student")
    private List<Student> studentClass;


    public SchoolClass(int yearEducation, String Code) {
        this.yearEducation = yearEducation;
        this.mCode = Code;



    }

    public SchoolClass() {

    }

    public SchoolClass(SchoolClass dubClass){
        this.yearEducation = dubClass.yearEducation;
        this.mCode = dubClass.mCode;
        this.mainTeacher = dubClass.mainTeacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYearEducation() {
        return yearEducation;
    }

    public void setYearEducation(int yearEducation) {
        this.yearEducation = yearEducation;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(Teacher mainTeacher) {
        this.mainTeacher = mainTeacher;
    }



// Год обучения, мнемокод, Классный руководитель (ссылка на учителя), список учеников класса (ссылки на учеников).
}
