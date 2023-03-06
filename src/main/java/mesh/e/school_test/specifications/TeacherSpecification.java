package mesh.e.school_test.specifications;


import mesh.e.school_test.models.Teacher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TeacherSpecification {

    public static Specification<Teacher> firstName(String firstName){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("firstName"), firstName);
        });
    }

    public static Specification<Teacher> lastName(String lastName){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("lastName"), lastName);
        });
    }

    public static Specification<Teacher> middleName(String middleName){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("middleName"), middleName);
        });
    }

    public static Specification<Teacher> mainSubject(String mainSubject){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("mainSubject"), mainSubject);
        });
    }

    public static Specification<Teacher> genderIdentity(String genderIdentity){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("genderIdentity"), genderIdentity);
        });
    }

    public static Specification<Teacher> birthYear(int birthYear){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("birthYear"), birthYear);
        });
    }
}

