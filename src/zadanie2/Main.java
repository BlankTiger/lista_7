package zadanie2;

public class Main {
	public static void main(String[] args) {
		Chair[] chairs = Chair.chairArrayCreator(2);

		for (Chair chair : chairs) {
			System.out.println(chair);
		}
	}
}
