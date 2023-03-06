package mesh.e.school_test.specifications;


import mesh.e.school_test.models.Student;
import mesh.e.school_test.models.Teacher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StudentSpecification {

    public static Specification<Student> firstName(String firstName){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("firstName"), firstName);
        });
    }

    public static Specification<Student> lastName(String lastName){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("lastName"), lastName);
        });
    }

    public static Specification<Student> middleName(String middleName){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("middleName"), middleName);
        });
    }


    public static Specification<Student> genderIdentity(String genderIdentity){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("genderIdentity"), genderIdentity);
        });
    }

    public static Specification<Student> birthYear(int birthYear){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("birthYear"), birthYear);
        });
    }
}

