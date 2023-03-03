package mesh.e.school_test.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "school_class")
public class SchoolClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String mCode;

    private int yearEducation;



    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany (fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Collection<Student> student;




    public SchoolClass(int yearEducation, String Code) {
        this.yearEducation = yearEducation;
        this.mCode = Code;



    }

    public SchoolClass() {

    }

    public SchoolClass(SchoolClass dubClass){
        this.yearEducation = dubClass.yearEducation;
        this.mCode = dubClass.mCode;

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

    public Collection<Student> getStudent() {
        return student;
    }

    public void setStudent(Collection<Student> student) {
        this.student = student;
    }

    // Год обучения, мнемокод, Классный руководитель (ссылка на учителя), список учеников класса (ссылки на учеников).
}
