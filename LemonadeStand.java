import java.util.Scanner;

public class LemonadeStand {
	// declare variables
	private double moneyMine;
	private String[] nameSuppliesPlural = {"Lemons", "Cups of Sugar", "Ice Cubes", "Cups"};
	private String[] nameSuppliesSingular = {"Lemon", "Cup of Sugar", "Ice Cube", "Cup"};
	private double[] cost = {1.50, 2.00, 0.75, 0.25}; // {Lemons, Sugar, Ice, Cups}
	private int[] qty; // {Lemons, Sugar, Ice, Cups}
	private double pricePerCup;
	private int[] ratio; // {lemons per pitcher, sugar per pitcher, ice per cup}
	private int cupsAvail;
	private int pitchCount; // Total pitches of lemonade remaining
	private int cupPerPitch = 12;
	
	private int totalCustomers;
	private int dayCount;
	
	public LemonadeStand() {
		moneyMine = 20.00;
		pricePerCup = 0.25;
		qty = new int[]{0, 0, 0, 0};
		ratio = new int[]{0, 0, 0};
		dayCount = 0;
		totalCustomers = 0;
	}
	
	public LemonadeStand(double money) {
		moneyMine = money;
		pricePerCup = 0.25;
		qty = new int[]{0, 0, 0, 0};
		ratio = new int[]{0, 0, 0};
		dayCount = 0;
		totalCustomers = 0;
	}
	
	public void instructions(Scanner sc) { //prints instructions
		System.out.println("\nWelcome to the game Lemonade Stand!");
		System.out.println("\nYou will running a lemonade stand,");
		System.out.println("making lemonade, and selling lemonade");
		//System.out.println("\nYou will be able to choose the recipe");
		//System.out.println("of the lemonade and how much it costs");
		System.out.println("\nLet's hope your stand is profitable!");
	}
	
	public void navigation(Scanner sc) { // REMEMBER TO ADD METHODS
		System.out.println("\n\nWhat do you want to do?\nType the option you want to do");
		
		System.out.println("\nAction Options:");
		String[] options = new String[]{"Check Inventory", "Go Shopping", "Prepare Lemonade Stand", "Start Day\n"};
		
		for (int i = 0; i < 4; i++) {
			System.out.println(options[i]);
		}
		
		String input = sc.nextLine().toLowerCase();
		
		if (input.equals("check inventory")) {
			getInventory();
		}
		else if (input.equals("go shopping")) {
			shopping(sc);
		}
		else if (input.equals("prepare lemonade stand")) {
			
		}
		else if (input.equals("start day")) {
			
		}
		else {
			navigation(sc);
			return;
		}
		
		navigation(sc);
	}
	
	public void setPrice(double p) {
		pricePerCup = p;
		System.out.println("Price per cup = " + pricePerCup);
	}
	
	public void getDayCount() {
		System.out.println("Number of days = " + dayCount);
	}
	
	public void getMoney() {
		System.out.println("You have $" + moneyMine);
	}
	
	public void getInventory() {
		for (int i = 0; i < 4; i++) {
			System.out.println(qty[i] + " " + nameSuppliesPlural[i]);
		}
		getMoney();
	}

	// Code made for cost having 3 elements, not 4
	public void shopping(Scanner sc) { // prepare buying
		getInventory();
		
		System.out.println("\nDo you want to go shopping for supplies? (y/n)");
		String shop = sc.nextLine();
		shop = shop.toLowerCase();
		if (shop.equals("y")) {
			buying(sc);
		}
		else if (shop.equals("n") == false) {
			System.out.println("Enter y or n");
			shopping(sc);
		}
		return;
	}
	
	public void buying(Scanner sc) { // buy what
		System.out.println("\nWhat do you want to buy: Lemons, Sugar, or Ice?");
		String buy = sc.nextLine();
		buy = buy.toLowerCase();
		while (buy.equals("lemons") == false && buy.equals("sugar") == false && buy.equals("ice") == false) {
			System.out.println("\nEnter lemons, sugar, or ice");
			buy = sc.nextLine();
		}

		int l = buy.length();
		int index = (l % 5)/3 + (l % 2); // Converts string to corresponding number index for arrays
		
		System.out.println("\nYou have " + qty[index] + " " + nameSuppliesPlural[index]);
		System.out.println("1 " + nameSuppliesSingular[index] + " costs $ " + cost[index]);
		System.out.println("How many " + (nameSuppliesPlural[index]).toLowerCase() + " do you want to buy? (int)"); // If user does not enter int, Exception Error
		int amount = sc.nextInt();
		String buffer = sc.nextLine(); 
		
		while (moneyMine - cost[index]*amount < 0) { // Banks don't give loans for lemonade stands
			System.out.println("\nYou cannot afford " + amount + " " + nameSuppliesPlural[index]);
			System.out.println("How many " + (nameSuppliesPlural[index]).toLowerCase() + " do you want to buy? (int)");
			amount = sc.nextInt();
			String buffer1 = sc.nextLine();
		}
		
		qty[index] += amount;
		moneyMine -= cost[index]*amount;
		
		System.out.println("\nYou now have " + qty[index] + " " + nameSuppliesPlural[index] + " & $" + moneyMine + " remaining");
		System.out.println("Do you want to buy more? (y/n)");
		String response = sc.nextLine();
		response = response.toLowerCase();
		if (response.equals("y")) {
			buying(sc);
		}
		return;
	}
	
	public void recipe(Scanner sc) {
		String[] intro = {"Price per Cup: ", "Lemons per Pitcher: ", "Sugar per Pitcher: ", "Ice per Cup: "};
		String[] end = {" Cents", " Lemons", " Cups", " Cubes"};
		String input;
		
		System.out.println("Current Price & Recipe:\n");
		for (int i = 0; i < 4; i++) {
			System.out.println(intro[i] + ratio[i] + end[i]);
		}
		
		System.out.println("Do you want to edit anything? (y/n)");
		String response = sc.nextLine();
		response = response.toLowerCase();
		
		if (response.equals("y")) {
			System.out.println("Enter desired integer values after the colons");
			int value;
			for (int j = 0; j < 4; j++) {
				System.out.print("\n" + intro[j]);
				value = sc.nextInt();
				String buffer2 = sc.nextLine();
				ratio[j] = value;
			}
			recipe(sc);
		}
		return;
	}
}
