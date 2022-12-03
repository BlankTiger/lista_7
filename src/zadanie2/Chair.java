package zadanie2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Chair {
    private int amountOfLegs;
    private String type;
    private boolean adjustable;
    private Cushion cushion;

    protected Chair(int amountOfLegs, String type, boolean adjustable, Cushion cushion) {
        this.amountOfLegs = amountOfLegs;
        this.type = type;
        this.adjustable = adjustable;
        this.cushion = cushion;
    }

    protected static Chair[] chairArrayCreator(int amountOfChairs) {
        Chair[] chairs = new Chair[amountOfChairs];
        Set<Chair> setOfChairs = new HashSet<Chair>();

        for (int i = 0; i < chairs.length; i++) {
            boolean isUnique = false;
            Chair chair = null;
            while (!isUnique) {
                chair = createChairFromUserInput();
                // kiedy dodajemy do struktury Set, to zwraca boolean mowiacy o
                // tym, czy juz istnieje taki element, czy nie
                // sposob ten unika podwojnej petli prowadzacej do O(n^2)
                // i pozwala na (w najlepszym przypadku) O(n)
                isUnique = setOfChairs.add(chair);
                System.out.println();
                if (!isUnique) {
                    System.out.println("Sprobuj ponownie, takie krzesla zostalo juz utworzone");
                }
            }
            chairs[i] = chair;
        }

        return chairs;
    }

    private static Chair createChairFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj liczbe nog: ");
        int amountOfLegs = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Podaj typ krzesla (obrotowe/nieobrotowe): ");
        String type = scanner.nextLine();

        System.out.print("Podaj czy mozna dostosowac oparcie (true/false): ");
        boolean adjustable = scanner.nextBoolean();
        scanner.nextLine();

        Cushion cushion = Cushion.createCushionFromUserInput();

        return new Chair(amountOfLegs, type, adjustable, cushion);
    }

    @Override
    public String toString() {
        return "Chair{" +
                "amountOfLegs=" + amountOfLegs +
                ", type='" + type + '\'' +
                ", adjustable=" + adjustable +
                ", cushion=" + cushion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chair chair = (Chair) o;
        return amountOfLegs == chair.amountOfLegs && adjustable == chair.adjustable && Objects.equals(type, chair.type) && Objects.equals(cushion, chair.cushion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfLegs, type, adjustable, cushion);
    }
}
