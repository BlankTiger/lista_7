package university.app;


public class Lecturer {
    private final int id;
    private final String degree;
    private final String firstName;
    private final String lastName;

    public int getId() {
        return id;
    }

    public String getDegree() {
        return degree;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    protected Lecturer(int id, String degree, String firstName, String lastName) {
        this.id = id;
        this.degree = degree;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected static boolean checkIfLecturerExists(Lecturer[] lecturers, int id) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer != null && lecturer.id == id) return true;
        }
        return false;
    }

    protected static Lecturer getLecturerById(Lecturer[] lecturers, int id) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getId() == id) return lecturer;
        }
        return null;
    }
}
