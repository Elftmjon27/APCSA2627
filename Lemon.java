import java.util.Scanner;

public class Lemon {
	public static void main(String[] args) {
		// Call Lemonade Stand constructor, instance is l1
		LemonadeStand l1 = new LemonadeStand(); // put starting money in parameter
		// Call the method inside the Lemonade Stand class
		Scanner sc = new Scanner(System.in);
		
		l1.instructions(sc);
		l1.navigation(sc);
	}
}


