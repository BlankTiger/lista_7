package university;
import university.app.UniversityApp;

public class Main {
    public static void main(String[] args) {
        UniversityApp university = new UniversityApp();
        university.createLecturer(0, "Dr", "Anita", "Atina");
        university.createLecturer(0, "Mgr", "Anita", "Atina");
        university.createLecturer(1, "Inż.", "Irena", "Aneri");
        university.createGroup("P00-23a", "Programowanie", 0);
        university.createGroup("P00-23a", "Rysunek techniczny", 0);
        university.createGroup("P23-00c", "Rysunek techniczny", 1);
        university.addStudentToGroup(261733, "P00-23a", "Jan", "Kowalski");
        university.addStudentToGroup(261733, "P00-23a", "Jan", "Kowalski");
        university.addStudentToGroup(261733, "P23-00c", "Jan", "Kowalski");
        university.addStudentToGroup(257313, "P00-23a", "Grzegorz", "Slowny");
        university.printGroupInfo("P00-23a");
        university.printGroupInfo("P00-23b");
        university.printAllStudents();
        university.addGrade(261733, "P00-23a", 5.5);
        university.addGrade(261733, "P00-23b", 5.5);
        university.addGrade(261733, "P00-23a", 5.5);
        university.addGrade(261733, "P23-00c", 3.0);
        university.addGrade(257313, "P00-23a", 4.5);
        university.printGradesForGroup("P00-23a");
        university.printGradesForGroup("P00-23b");
        university.printGradesForStudent(261733);
    }
}
