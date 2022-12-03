package zadanie2;

import java.util.Objects;
import java.util.Scanner;

public class Cushion {
    private double height;
    private double width;
    private double depth;

    protected Cushion(double height, double width, double depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    protected static Cushion createCushionFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj wysokosc poduszki: ");
        double height = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Podaj szerokosc poduszki: ");
        double width = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Podaj glebokosc poduszki: ");
        double depth = scanner.nextDouble();
        scanner.nextLine();

        return new Cushion(height, width, depth);
    }

    @Override
    public String toString() {
        return "Cushion{" + "height=" + height + ", width=" + width + ", depth=" + depth + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cushion cushion = (Cushion) o;
        return Double.compare(cushion.height, height) == 0 && Double.compare(cushion.width, width) == 0 && Double.compare(cushion.depth, depth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, depth);
    }
}
