package university.app;

import java.util.HashMap;

public class Group {
    private final String code;
    private final String name;
    private final int lecturerId;
    private Student[] students;
    private HashMap<String, Double> grades;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public Student[] getStudents() {
        return students;
    }

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    protected Group(String code, String name, int lecturerId) {
        this.code = code;
        this.name = name;
        this.lecturerId = lecturerId;
        this.students = new Student[0];
        this.grades = new HashMap<String, Double>();
    }

    protected static boolean checkIfGroupExists(Group[] groups, String code) {
        for (Group group : groups) {
            if (group != null && code.equals(group.code)) return true;
        }
        return false;
    }

    protected static Group getGroupByCode(Group[] groups, String code) {
        if (!checkIfGroupExists(groups, code)) {
            System.out.printf("Grupa %s nie istnieje%n", code);
            return null;
        }

        for (Group group : groups) {
            if (code.equals(group.getCode())) return group;
        }
        return null;
    }

    protected void addStudentToGroup(int index, String firstName, String lastName) {
        if (isStudentInGroup(index)) {
            System.out.printf("Student o indeksie %d już jest w grupie o kodzie %s%n", index, code);
            return;
        }

        int newLength = students.length + 1;
        Student[] newStudents = new Student[newLength];
        System.arraycopy(students, 0, newStudents, 0, newLength - 1);
        newStudents[newLength - 1] = new Student(index, firstName, lastName);
        students = newStudents;
    }

    private boolean isStudentInGroup(int index) {
        for (Student student : students) {
            if (student.getIndex() == index) return true;
        }
        return false;
    }

    protected boolean doesStudentHaveAGrade(int index) {
        return grades.containsKey(String.valueOf(index));
    }

    protected void addGradeForStudent(int index, double grade) {
        if (!isStudentInGroup(index)) {
            System.out.printf("Student o indeksie %d nie jest zapisany do grupy o kodzie %s%n", index, code);
            return;
        }

        if (doesStudentHaveAGrade(index)) {
            System.out.printf("Student o indeksie %d juz ma ocene w grupie o kodzie %s%n", index, code);
        }

        grades.put(String.valueOf(index), grade);
    }
}
