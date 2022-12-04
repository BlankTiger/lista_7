package university.app;

import java.util.Map;

public class UniversityApp {
    private Lecturer[] lecturers;
    private Student[] students;
    private Group[] groups;

    public UniversityApp() {
        this.lecturers = new Lecturer[0];
        this.students = new Student[0];
        this.groups = new Group[0];
    }

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */
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

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */
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

    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */
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

    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */
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

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param studentIndex - numer indeksu studenta
     * @param groupCode    - kod grupy
     * @param grade        - ocena
     */
    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group selectedGroup = Group.getGroupByCode(groups, groupCode);
        if (selectedGroup == null) return;

        selectedGroup.addGradeForStudent(studentIndex, grade);
    }

    /**
     * Wyświetla wszystkie oceny studenta.
     * Przykładowy wydruk:
     * Podstawy programowania: 5.0
     * Programowanie obiektowe: 5.5
     *
     * @param index - numer indesku studenta dla którego wyświetlić oceny
     */
    public void printGradesForStudent(int index) {
        System.out.println("Oceny studenta o indeksie: " + index);

        for (Group group : groups) {
            if (!group.doesStudentHaveAGrade(index)) continue;

            double grade = group.getGrades().get(String.valueOf(index));
            System.out.printf("%s: %.1f%n", group.getName(), grade);
        }
    }

    /**
     * Wyświetla oceny studentów dla wskazanej grupy.
     * Przykładowy wydruk:
     * 179128 Marcin Abacki: 5.0
     * 179234 Dawid Donald: 4.5
     * 189521 Anna Kowalska: 5.5
     *
     * @param groupCode - kod grupy, dla której wyświetlić oceny
     */
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

    /**
     * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
     * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
     * Przykładowy wydruk:
     * 179128 Marcin Abacki
     * 179234 Dawid Donald
     * 189521 Anna Kowalska
     */
    public void printAllStudents() {
        for (Student student : students) {
            student.printInfo();
        }
    }
}
