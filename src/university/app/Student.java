package university.app;

public class Student {
    private final int index;
    private final String firstName;
    private final String lastName;

    public int getIndex() {
        return index;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    protected Student(int index, String firstName, String lastName) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected static Student getStudentByIndex(Student[] students, int index) {
        for (Student student : students) {
            if (student.getIndex() == index) return student;
        }
        return null;
    }

    protected void printInfo() {
        String output = "" + index + " " + firstName + " " + lastName;
        System.out.println(output);
    }
}
