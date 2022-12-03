package university.app;

import java.util.Map;

public class UniversityApp {
    protected Lecturer[] lecturers;
    protected Student[] students;
    protected Group[] groups;

    public UniversityApp() {
        this.lecturers = new Lecturer[0];
        this.students = new Student[0];
        this.groups = new Group[0];
    }

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (Lecturer.checkIfLecturerExists(lecturers, id)) {
            System.out.printf("Prowadzacy o id %d juz istnieje%n", id);
            return;
        }

        int newLength = lecturers.length + 1;
        Lecturer[] newLecturers = new Lecturer[newLength];
        System.arraycopy(lecturers, 0, newLecturers, 0, newLength - 1);
        newLecturers[newLength - 1] = new Lecturer(id, degree, firstName, lastName);
        lecturers = newLecturers;
    }

    public void createGroup(String code, String name, int lecturerId) {
        if (Group.checkIfGroupExists(groups, code)) {
            System.out.printf("Grupa o kodzie %s juz istnieje%n", code);
            return;
        }

        if (!Lecturer.checkIfLecturerExists(lecturers, lecturerId)) {
            System.out.printf("Prowadzacy o id %d nie istnieje%n", lecturerId);
            return;
        }

        int newLength = groups.length + 1;
        Group[] newGroups = new Group[newLength];
        System.arraycopy(groups, 0, newGroups, 0, newLength - 1);
        newGroups[newLength - 1] = new Group(code, name, lecturerId);
        groups = newGroups;
    }

    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group group = Group.getGroupByCode(groups, groupCode);
        if (group == null) return;

        group.addStudentToGroup(index, firstName, lastName);
        addStudentToStudents(index, firstName, lastName);
    }

    private void addStudentToStudents(int index, String firstName, String lastName) {
        if (isStudentInStudents(index)) return;

        int newLength = students.length + 1;
        Student[] newStudents = new Student[newLength];
        System.arraycopy(students, 0, newStudents, 0, newLength - 1);
        newStudents[newLength - 1] = new Student(index, firstName, lastName);
        students = newStudents;
    }

    private boolean isStudentInStudents(int index) {
        for (Student student : students) {
            if (student.getIndex() == index) return true;
        }
        return false;
    }

    public void printGroupInfo(String groupCode) {
        Group group = Group.getGroupByCode(groups, groupCode);
        if (group == null) return;

        Lecturer lecturer = Lecturer.getLecturerById(lecturers, group.getLecturerId());
        Student[] studentsInGroup = group.getStudents();

        String output = "Kod: " + groupCode + "\n" + "Nazwa: " + group.getName() + "\n" + "Prowadzacy: " + lecturer.getDegree() + " " + lecturer.getFirstName() + " " + lecturer.getLastName() + "\n" + "Uczestnicy:\n";
        for (Student student : studentsInGroup) {
            output += student.getIndex() + " " + student.getFirstName() + " " + student.getLastName() + "\n";
        }
        System.out.println(output);
    }

    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group selectedGroup = Group.getGroupByCode(groups, groupCode);
        if (selectedGroup == null) return;

        selectedGroup.addGradeForStudent(studentIndex, grade);
    }

    public void printGradesForStudent(int index) {
        System.out.println("Oceny studenta o indeksie: " + index);

        for (Group group : groups) {
            if (!group.doesStudentHaveAGrade(index)) continue;

            double grade = group.getGrades().get(String.valueOf(index));
            System.out.printf("%s: %.1f%n", group.getName(), grade);
        }
    }

    public void printGradesForGroup(String groupCode) {
        Group group = Group.getGroupByCode(groups, groupCode);
        if (group == null) return;

        for (Map.Entry<String, Double> indexGradeEntry : group.getGrades().entrySet()) {
            int index = Integer.parseInt(indexGradeEntry.getKey());
            double grade = indexGradeEntry.getValue();
            Student student = Student.getStudentByIndex(students, index);
            if (student == null) continue;

            String output = "" + index + " " + student.getFirstName() + " " + student.getLastName() + ": " + grade;
            System.out.println(output);
        }
    }

    public void printAllStudents() {
        for (Student student : students) {
            student.printInfo();
        }
    }
}
