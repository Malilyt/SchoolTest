package mesh.e.school_test.controllers;

import mesh.e.school_test.helper.CreateMnemoCode;
import mesh.e.school_test.models.SchoolClass;
import mesh.e.school_test.models.Student;
import mesh.e.school_test.models.Teacher;
import mesh.e.school_test.repo.SchoolClassRepository;

import mesh.e.school_test.repo.StudentRepository;
import mesh.e.school_test.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.UIManager.get;


@Controller
public class MainController {


    CreateMnemoCode createMnemoCode = new CreateMnemoCode();


    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepo;

    @Autowired
    private StudentRepository studentRepository;



    @GetMapping("/")
    public String home(Map<String, Object> model) {

        return "home";
    }

    @GetMapping("add_cs")
    public String addClassSchool(Map<String, Object> model) {

        List<SchoolClass> searchSC = schoolClassRepo.findAll();

        Collections.reverse(searchSC);

        for(int i = searchSC.size()-1; i >= 5 ; i--){
            searchSC.remove(i);
        }

        model.put("searchSC", searchSC);
        return "add_cs";
    }

    @PostMapping("add_сs_post")
    public String postAddClassSchool(
            @RequestParam int yearEducation,
            Map<String, Object> model) {

        List<SchoolClass> searchSC = schoolClassRepo.findAll();

        Collections.reverse(searchSC);

        for(int i = searchSC.size()-1; i >= 5 ; i--){
            searchSC.remove(i);
        }


        String mnemoCode = createMnemoCode.mnemoCode(yearEducation);
        SchoolClass schoolClass = new SchoolClass(yearEducation, mnemoCode);


        schoolClassRepo.save(schoolClass);

        model.put("searchSC", searchSC);
        return "redirect:add_cs";
    }

    @GetMapping("add_teacher")
    public String addTeacher(Map<String, Object> model) {

        return "add_teacher";
    }

    @PostMapping("add_teacher")
    public String postAddTeacher(
            @RequestParam String lastName,
            @RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String mainSubject,
            @RequestParam String genderIdentity,
            @RequestParam int birthYear,
            Map<String, Object> model) {

        Teacher teacher = new Teacher(lastName, firstName, middleName, mainSubject, genderIdentity, birthYear);
        teacherRepository.save(teacher);


        return "add_teacher";
    }

    @GetMapping("add_student")
    public String addStudent(Map<String, Object> model) {

        return "add_student";
    }

    @PostMapping("add_student")
    public String postAddStudent(
            @RequestParam String lastName,
            @RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String genderIdentity,
            @RequestParam int birthYear,
            Map<String, Object> model) {

        Student student = new Student(lastName, firstName, middleName, genderIdentity, birthYear);
        studentRepository.save(student);

        return "add_student";
    }

    @GetMapping("sc/{id}")
    public String changeMT (@PathVariable(value = "id") long scId,
             Map<String, Object> model){


        Optional<SchoolClass> sc = schoolClassRepo.findById(scId);

        ArrayList<SchoolClass> result = new ArrayList<>();
        sc.ifPresent(result::add);

        Optional<SchoolClass> searchSC = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> searchSCList = new ArrayList<>();

        searchSC.ifPresent(searchSCList::add);


        model.put("searchSC", searchSCList);
        model.put("sc", result);
        return "change_mt";
    }


    @PostMapping(value = "/sc/{id}")
    public String postChangeMT (@PathVariable(value = "id") long scId,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String middleName,
                                @RequestParam String mainSubject,
                                @RequestParam String genderIdentity,
                                @RequestParam String birthYearSt,
                                Map<String, Object> model){

        List<Teacher> teachers;


        Optional<SchoolClass> sc = schoolClassRepo.findById(scId);

        ArrayList<SchoolClass> result = new ArrayList<>();
        sc.ifPresent(result::add);

        Optional<SchoolClass> searchSC = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> searchSCList = new ArrayList<>();

        searchSC.ifPresent(searchSCList::add);



        if(birthYearSt != "") {
            int birthYear = Integer.parseInt(birthYearSt);
            teachers = teacherRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndMainSubjectIsContainingAndGenderIdentityIsContainingAndBirthYear
                            (firstName, lastName ,middleName,  mainSubject, genderIdentity, birthYear);
            model.put("teachers", teachers);
        }
        if(birthYearSt == "") {
            teachers = teacherRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndMainSubjectIsContainingAndGenderIdentityIsContaining
                            (firstName, lastName ,middleName,  mainSubject, genderIdentity);
            model.put("teachers", teachers);
        }
        if(firstName == "" && lastName == "" && middleName == "" && mainSubject == "" &&  genderIdentity == "" && birthYearSt == ""){
            teachers = null;
            model.put("teachers", teachers);
        }



        model.put("searchSC", searchSCList);
        model.put("sc", result);
        return "change_mt";
    }


    @GetMapping(value = "/system_change_mt/{teach}/{sc}")
    public String postChangeMT (@PathVariable(value = "teach") long idT,
                                @PathVariable(value = "sc") long scId,
                                Map<String, Object> model){

        Teacher teacher = teacherRepository.findById(idT)
                .orElseThrow(() -> new ClassCastException());

                    SchoolClass schoolClass = schoolClassRepo.findById(scId)
                    .orElseThrow(() -> new ClassCastException());

            schoolClass.setMainTeacher(teacher);
            schoolClassRepo.save(schoolClass);


        return "redirect:/sc/"+scId;
    }

    @GetMapping("add_sc_student/{id}")
    public String addSCStudent (@PathVariable(value = "id") long scId,
                            Map<String, Object> model){

        SchoolClass schoolClass = schoolClassRepo.findById(scId)
                .orElseThrow(() -> new ClassCastException());



        List<Student> studentsInClass = schoolClass.getStudentClass();


        Optional<SchoolClass> sc = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> result = new ArrayList<>();

        sc.ifPresent(result::add);

        Optional<SchoolClass> searchSC = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> searchSCList = new ArrayList<>();

        searchSC.ifPresent(searchSCList::add);

        model.put("studentsInClass", studentsInClass);
        model.put("searchSC", searchSCList);
        model.put("sc", result);
        return "add_sc_student";
    }

    @PostMapping(value = "/add_sc_student/{id}")
    public String postAddSCStudent (@PathVariable(value = "id") long scId,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String middleName,
                                @RequestParam String genderIdentity,
                                @RequestParam String birthYearSt,
                                Map<String, Object> model){

        List<Student> students;


        SchoolClass schoolClass = schoolClassRepo.findById(scId)
                .orElseThrow(() -> new ClassCastException());



        List<Student> studentsInClass = (List<Student>) schoolClass.getStudentClass();


        Optional<SchoolClass> sc = schoolClassRepo.findById(scId);

        ArrayList<SchoolClass> result = new ArrayList<>();
        sc.ifPresent(result::add);

        Optional<SchoolClass> searchSC = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> searchSCList = new ArrayList<>();

        searchSC.ifPresent(searchSCList::add);



        if(birthYearSt != "") {
            int birthYear = Integer.parseInt(birthYearSt);
            students = studentRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndGenderIdentityIsContainingAndBirthYear
                            (firstName, lastName ,middleName, genderIdentity, birthYear);
            model.put("students", students);
        }
        if(birthYearSt == "") {
            students = studentRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndGenderIdentityIsContaining
                            (firstName, lastName ,middleName, genderIdentity);
            model.put("students", students);
        }
        if(firstName == "" && lastName == "" && middleName == "" && genderIdentity == "" && birthYearSt == ""){
            students = null;
            model.put("students", students);
        }


        model.put("studentsInClass", studentsInClass);
        model.put("searchSC", searchSCList);
        model.put("sc", result);
        return "add_sc_student";
    }

    @GetMapping(value = "/system_add_sc_student/{stud}/{sc}")
    public String postAddSCStud (@PathVariable(value = "stud") long idS,
                                @PathVariable(value = "sc") long scId,
                                Map<String, Object> model){

        Student student = studentRepository.findById(idS)
                .orElseThrow(() -> new ClassCastException());

        SchoolClass schoolClass = schoolClassRepo.findById(scId)
                .orElseThrow(() -> new ClassCastException());


        schoolClass.getStudentClass().add(student);

        schoolClassRepo.save(schoolClass);


        return "redirect:/add_sc_student/"+scId;
    }

    @GetMapping(value = "/system_delete_sc_student/{stud}/{sc}")
    public String postDeleteSCStud (@PathVariable(value = "stud") long idS,
                                 @PathVariable(value = "sc") long scId,
                                 Map<String, Object> model){

        Student student = studentRepository.findById(idS)
                .orElseThrow(() -> new ClassCastException());

        SchoolClass schoolClass = schoolClassRepo.findById(scId)
                .orElseThrow(() -> new ClassCastException());


        schoolClass.getStudentClass().remove(student);

        schoolClassRepo.save(schoolClass);


        return "redirect:/add_sc_student/"+scId;
    }

    @GetMapping(value = "search_class_school")
    public String searchSC(Map<String, Object> model){

        return "search_class_school";
    }

    @PostMapping(value = "search_class_school")
    public String postSearchSC(@RequestParam String firstNameMainTeacher,
                           @RequestParam String lastNameMainTeacher,
                           @RequestParam String middleNameMainTeacher,
                           @RequestParam String mnomoCode,
                           @RequestParam String yearEducation,
                           Map<String, Object> model){

        List<SchoolClass> res;


        List<Teacher> teachers =teacherRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContaining
        (firstNameMainTeacher,  lastNameMainTeacher , middleNameMainTeacher);



        if(teachers != null){

            for (Iterator<Teacher> iter = teachers.iterator(); iter.hasNext(); ) {
                Teacher element = iter.next();

                List<SchoolClass> searchCT =schoolClassRepo.findByMainTeacher(element);
                model.put("searchC",  searchCT);
            }
        }

        if(yearEducation != "") {
            if(teachers != null){

                for (Iterator<Teacher> iter = teachers.iterator(); iter.hasNext(); ) {
                    Teacher element = iter.next();

                    List<SchoolClass> searchCT =schoolClassRepo.findByMainTeacher(element);

                    int yearEducationInt = Integer.parseInt(yearEducation);

                    res = schoolClassRepo.findBymCodeIsContainingAndYearEducation(mnomoCode, yearEducationInt);

                    List<SchoolClass> combineList = Stream.concat(res.stream(), searchCT.stream())
                            .collect(Collectors.toList());

                    List<SchoolClass> finalRes = combineList.stream().distinct().collect(Collectors.toList());


                    model.put("searchC",  finalRes);
                }
            }else{
                int yearEducationInt = Integer.parseInt(yearEducation);

                res = schoolClassRepo.findBymCodeIsContainingAndYearEducation(mnomoCode, yearEducationInt);

                model.put("searchC",  res);
            }


        }if(yearEducation == "") {
            if(teachers != null){
                for (Iterator<Teacher> iter = teachers.iterator(); iter.hasNext(); ) {
                    Teacher element = iter.next();

                    List<SchoolClass> searchCT =schoolClassRepo.findByMainTeacher(element);

                    res = schoolClassRepo.findBymCode(mnomoCode);

                    List<SchoolClass> combineList = Stream.concat(res.stream(), searchCT.stream())
                            .collect(Collectors.toList());

                    List<SchoolClass> finalRes = combineList.stream().distinct().collect(Collectors.toList());


                    model.put("searchC",  finalRes);
                }
            }else{
                res = schoolClassRepo.findBymCode(mnomoCode);
                model.put("searchC",  res);
            }

        }

        if(firstNameMainTeacher == "" && lastNameMainTeacher == "" && middleNameMainTeacher == "" && mnomoCode == "" && yearEducation == "" ){
            res = null;
            model.put("searchC",  res);
        }


        return "search_class_school";
    }

    @GetMapping(value = "/editor_class/{id}")
    public String editrClass(@PathVariable(value = "id") long scId,
                             Map<String, Object> model){

        Optional<SchoolClass> sc = schoolClassRepo.findById(scId);

        ArrayList<SchoolClass> result = new ArrayList<>();
        sc.ifPresent(result::add);

        Optional<SchoolClass> searchSC = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> searchSCList = new ArrayList<>();

        searchSC.ifPresent(searchSCList::add);


        model.put("searchSC", searchSCList);
        model.put("sc", result);
        return "editor_class";
    }

    @PostMapping(value = "/editor_class/{id}")
    public String postEditrClass(@PathVariable(value = "id") long scId,
                                 @RequestParam String mCode,
                                 @RequestParam String yearEducation,

                             Map<String, Object> model){

        Optional<SchoolClass> sc = schoolClassRepo.findById(scId);

        ArrayList<SchoolClass> result = new ArrayList<>();
        sc.ifPresent(result::add);

        Optional<SchoolClass> searchSC = schoolClassRepo.findById(scId);
        ArrayList<SchoolClass> searchSCList = new ArrayList<>();

        searchSC.ifPresent(searchSCList::add);


        SchoolClass schoolClassEdit = schoolClassRepo.findById(scId).
                orElseThrow(() -> new ClassCastException());

        int yearEducationInt = Integer.parseInt(yearEducation);
        schoolClassEdit.setmCode(mCode);
        schoolClassEdit.setYearEducation(yearEducationInt);

        schoolClassRepo.save(schoolClassEdit);

        model.put("searchSC", searchSCList);
        model.put("sc", result);
        return "editor_class";
    }

    @GetMapping(value = "/duble_sc/{id}")
    public String dubleSC(@PathVariable(value = "id") long scId,
                          Map<String, Object> model){

        SchoolClass schoolClassOld = schoolClassRepo.findById(scId).
                orElseThrow(() -> new ClassCastException());


        SchoolClass schoolClassNew = new SchoolClass(schoolClassOld);

            schoolClassRepo.save(schoolClassNew);


        return "redirect:/search_class_school";
    }


    @GetMapping(value = "/delete_sc/{id}")
    public String deleteSC(@PathVariable(value = "id") long scId,
                          Map<String, Object> model){

        SchoolClass schoolClass = schoolClassRepo.findById(scId).
                orElseThrow(() -> new ClassCastException());

        schoolClassRepo.delete(schoolClass);

        return "redirect:/search_class_school";
    }

    @GetMapping(value = "search_teacher")
    public String searchTeacher(Map<String, Object> model){

        return "search_teacher";
    }

    @PostMapping(value = "search_teacher")
    public String postSearchTeacher(@RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam String middleName,
                                    @RequestParam String mainSubject,
                                    @RequestParam String genderIdentity,
                                    @RequestParam String birthYearSt,
                                    Map<String, Object> model){

        List<Teacher> teachers;


        if(birthYearSt != "") {
            int birthYear = Integer.parseInt(birthYearSt);
            teachers = teacherRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndMainSubjectIsContainingAndGenderIdentityIsContainingAndBirthYear
                            (firstName, lastName ,middleName,  mainSubject, genderIdentity, birthYear);
            model.put("teachers", teachers);
        }
        if(birthYearSt == "") {
            teachers = teacherRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndMainSubjectIsContainingAndGenderIdentityIsContaining
                            (firstName, lastName ,middleName,  mainSubject, genderIdentity);
            model.put("teachers", teachers);
        }
        if(firstName == "" && lastName == "" && middleName == "" && mainSubject == "" &&  genderIdentity == "" && birthYearSt == ""){
            teachers = null;
            model.put("teachers", teachers);
        }

        return "search_teacher";

    }



    @GetMapping(value = "/editor_teacher/{id}")
    public String editorTeacher( @PathVariable(value = "id") long tId,
                                 Map<String, Object> model){

        Teacher teachers = teacherRepository.findById(tId)
                .orElseThrow(() -> new ClassCastException());



           model.put("teachers", teachers);
        return "editor_teacher";
    }

    @PostMapping(value = "editor_teacher/{id}")
    public String postEditorTeacher( @PathVariable(value = "id") long tId,
                                     @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String middleName,
                                     @RequestParam String mainSubject,
                                     @RequestParam String genderIdentity,
                                     @RequestParam String birthYearSt,
                                     Map<String, Object> model){




        Teacher teacherEdit = teacherRepository.findById(tId).
                orElseThrow(() -> new ClassCastException());

        int birthYearStInt = Integer.parseInt(birthYearSt);
        teacherEdit.setFirstName(firstName);
        teacherEdit.setLastName(lastName);
        teacherEdit.setMiddleName(middleName);
        teacherEdit.setMainSubject(mainSubject);
        teacherEdit.setGenderIdentity(genderIdentity);
        teacherEdit.setBirthYear(birthYearStInt);


        teacherRepository.save(teacherEdit);

        model.put("teachers", teacherEdit);
        return "editor_teacher";
    }

    @GetMapping(value = "/duble_teacher/{id}")
    public String dubleT(@PathVariable(value = "id") long tId,
                          Map<String, Object> model){

        Teacher teacher = teacherRepository.findById(tId).
                orElseThrow(() -> new ClassCastException());


        Teacher teacherNew = new Teacher(teacher);

        teacherRepository.save(teacherNew);


        return "redirect:/search_teacher";
    }

    @GetMapping(value = "/delete_teacher/{id}")
    public String deleteT(@PathVariable(value = "id") long tId,
                           Map<String, Object> model){

        Teacher teacher = teacherRepository.findById(tId).
                orElseThrow(() -> new ClassCastException());

       teacherRepository.delete(teacher);

        return "redirect:/search_teacher";
    }

    @GetMapping(value = "search_student")
    public String searchStudent(Map<String, Object> model){

        return "search_student";
    }

    @PostMapping(value = "search_student")
    public String postSearchStudent(@RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam String middleName,
                                    @RequestParam String genderIdentity,
                                    @RequestParam String birthYearSt,
                                    Map<String, Object> model){

        List<Student> student;


        if(birthYearSt != "") {
            int birthYear = Integer.parseInt(birthYearSt);
            student = studentRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndGenderIdentityIsContainingAndBirthYear
                            (firstName, lastName ,middleName, genderIdentity, birthYear);
            model.put("student", student);
        }
        if(birthYearSt == "") {
            student = studentRepository.
                    findByFirstNameIsContainingAndLastNameIsContainingAndMiddleNameIsContainingAndGenderIdentityIsContaining
                            (firstName, lastName ,middleName, genderIdentity);
            model.put("student", student);
        }
        if(firstName == "" && lastName == "" && middleName == "" &&  genderIdentity == "" && birthYearSt == ""){
            student = null;
            model.put("student", student);
        }

        return "search_student";

    }




    @GetMapping(value = "/editor_student/{id}")
    public String editorStudent( @PathVariable(value = "id") long sId,
                                 Map<String, Object> model){

        Student student = studentRepository.findById(sId)
                .orElseThrow(() -> new ClassCastException());



        model.put("students", student);
        return "editor_student";
    }

    @PostMapping(value = "editor_student/{id}")
    public String postEditorStudent( @PathVariable(value = "id") long sId,
                                     @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String middleName,
                                     @RequestParam String genderIdentity,
                                     @RequestParam String birthYearSt,
                                     Map<String, Object> model){




        Student studentEdit = studentRepository.findById(sId).
                orElseThrow(() -> new ClassCastException());

        int birthYearStInt = Integer.parseInt(birthYearSt);
        studentEdit.setFirstName(firstName);
        studentEdit.setLastName(lastName);
        studentEdit.setMiddleName(middleName);
        studentEdit.setGenderIdentity(genderIdentity);
        studentEdit.setBirthYear(birthYearStInt);


        studentRepository.save(studentEdit);

        model.put("students", studentEdit);
        return "editor_student";
    }

    @GetMapping(value = "/duble_student/{id}")
    public String dubleS(@PathVariable(value = "id") long sId,
                         Map<String, Object> model){

        Student student = studentRepository.findById(sId).
                orElseThrow(() -> new ClassCastException());


        Student studentNew = new Student(student);

        studentRepository.save(studentNew);


        return "redirect:/search_student";
    }

    @GetMapping(value = "/delete_student/{id}")
    public String deleteS(@PathVariable(value = "id") long sId,
                          Map<String, Object> model){

//        Student student = studentRepository.findById(sId).
//                orElseThrow(() -> new ClassCastException());




        return "redirect:/search_student";
    }

}
