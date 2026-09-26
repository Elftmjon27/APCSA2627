import java.util.Scanner;

public class LemonadeStand {
	// declare variables
	private double moneyMine;

	final String[] nameSuppliesPlural = {" Lemons", " Sugar Cubes", " Ice Cubes", " Cups"};
	final String[] nameSuppliesSingular = {"Lemon", "Sugar Cube", "Ice Cube", "Cup"};

	final double[] cost = {0.25, 0.20, 0.0025, 0.20}; // {Lemons, Sugar, Ice, Cups}
	private int[] qty; // {Lemons, Sugar, Ice, Cups}
	final double[] spoilRate = {0.1, 0.1, 1, 0}; // percentage that spoils at end of day

	private double pricePerCup;
	private int[] ratio; // {lemons per pitcher, sugar per pitcher, ice per cup, cups per pitcher}

	private int availCups;
	private int pitchCount; // Total pitches of lemonade remaining

	final String[] weatherOptions = {"Sunny / Hot & Dry", "Sunny & Warm / Clear", "Cloud / Overcast", "Rainy / Thunderstorms"};
	private String weather;
	private int temperature;
	private double popularity;
	
	private int totalCustomers;
	private int dayCount;
	
	public LemonadeStand() {
		moneyMine = 20.00;
		setBasics();
	}
	
	public LemonadeStand(double money) {
		moneyMine = money;
		setBasics();
	}
	
	public void instructions(Scanner sc) { // prints instructions
		System.out.println("\nWelcome to the game Lemonade Stand!");
		System.out.println("\nYou will running a lemonade stand,");
		System.out.println("making lemonade, and selling lemonade");
		//System.out.println("\nYou will be able to choose the recipe");
		//System.out.println("of the lemonade and how much it costs");
		System.out.println("\nLet's hope your stand is profitable!");
	}
	
	public void navigation(Scanner sc) {
		System.out.println("\n\nWhat do you want to do?\nType the option you want to do");
		
		System.out.println("\nAction Options:");
		String[] options = new String[]{"Check Inventory", "Check Weather", "Go Shopping", "Adjust Recipe", "Start Day\n"};
		
		for (int i = 0; i < 5; i++) {
			System.out.println(options[i]);
		}
		
		String input = sc.nextLine().toLowerCase();
		
		if (input.contains("inv")) {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			getInventory();
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		else if (input.contains("weather")) {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			getForecast();
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		else if (input.contains("shop")) {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			shopping(sc);
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		else if (input.contains("adjust") || input.contains("recipe")) {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			recipe(sc);
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		else if (input.contains("start") || input.contains("day")) {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			sellLemonade(sc);
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		
		navigation(sc);
	}
	
	public void setBasics() {
		pricePerCup = 0.25;
		qty = new int[]{0, 0, 0, 0};
		ratio = new int[]{4, 4, 4, 12};
		dayCount = 0;
		pitchCount = 0;
		availCups = 0;
		setWeather();
		setTemperature();
		setTotalCustomers();
	}

	public void setWeather() {
		weather = weatherOptions[(int) (4*Math.random())];
	}

	public void setTemperature() { 
		// unit is fahrenheit
		if (weather.equals("Sunny / Hot & Dry")) { // 90 to 99 inclusively
			temperature = (int) (10*Math.random() + 90);
		}
		else if (weather.equals("Sunny & Warm / Clear")) { // 75 to 89 inclusively
			temperature = (int) (15*Math.random() + 75);
		}
		else if (weather.equals("Cloud / Overcast")) { // 60 to 74 inclusively
			temperature = (int) (15*Math.random() + 60);
		}
		else { // 50 to 59 inclusively
			temperature = (int) (10*Math.random() + 50);
		}
	}

	public void setTotalCustomers() {
		// always int (random)
		// Increases with day count
		// Initial baseline dependent on weather
		// temperated associate with weather, so ignore in calculation

		int adjustment = (int) (Math.floor(temperature / 60) + Math.floor(temperature / 75) + Math.floor(temperature / 90))*20 + 10;
		/* The equation above does this better (keeping to know range given weather)
		if (weather.equals("Sunny / Hot & Dry")) { // 70 to 89 inclusively
			adjustment = 70;
		}
		else if (weather.equals("Sunny & Warm / Clear")) { // 50 to 69 inclusively
			adjustment = 50;
		}
		else if (weather.equals("Cloud / Overcast")) { // 30 to 49 inclusively
			adjustment = 30;
		}
		else { // 10 to 29 inclusively
			adjustment = 10;
		}
		*/

		totalCustomers = (int) (20*Math.random() + adjustment) + 2*dayCount;
	}

	public void setSellQty() {
		int temp = qty[0] / ratio[0];
		for (int i = 1; i < 4; i++) {
			if (temp > qty[i] / ratio[i] && i != 2) {
				temp = qty[i] / ratio[i];
			}
		}

		if(qty[2] / (ratio[3]*ratio[2]) < temp) { // special if statement b/c ice is by per cup instead of per pitcher
			temp = qty[2] / (ratio[3]*ratio[2]);
		}

		pitchCount = temp;
		availCups = pitchCount * ratio[3];
	}	

	public void getInventory() {
		for (int i = 0; i < 4; i++) {
			System.out.println(qty[i] + nameSuppliesPlural[i]);
		}
		System.out.println("You have $" + moneyMine);
	}

	public void getRecipe(String title) {
		String[] intro = {"Lemons per Pitcher: ", "Sugar per Pitcher: ", "Ice per Cup: ", "Cups per Pitcher "};
		String[] end = {" Lemons", " Cubes", " Cubes", " Cups"};
		
		System.out.println(title + ":\n");
		for (int i = 0; i < 4; i++) {
			System.out.println(intro[i] + ratio[i] + end[i]);
		}
	}

	public void getPricePerCup() {
		System.out.println("Price per Cup: $" + pricePerCup);
	}

	public void getForecast() {
		System.out.println("Weather: " + weather);
		System.out.println("Temperature: " + temperature + "\u00B0F");
	}

	public void transaction(Scanner sc, int index) {
		System.out.println("\nYou have " + qty[index] +  nameSuppliesPlural[index]);
		System.out.println("1 " + nameSuppliesSingular[index] + " costs $ " + cost[index]);
		System.out.println("\nHow many" + (nameSuppliesPlural[index]).toLowerCase() + " do you want to buy? (int)"); // If user does not enter int, Exception Error
		int amount = sc.nextInt();
		String buffer = sc.nextLine(); 

		System.out.println("\n" + amount + nameSuppliesPlural[index] + " will cost $" + cost[index]*amount);
		System.out.println("Are you sure about the cost? (y/n)");

		String response = sc.nextLine();

		while (!(response.equals("y") || response.equals("n"))) {
			System.out.println("Enter y or n");
			response = sc.nextLine();
		}

		if (moneyMine < cost[index] * amount) { // Banks don't give loans for lemonade stands
			System.out.println("\nYou cannot afford " + amount + nameSuppliesPlural[index]);
			transaction(sc, index);
			return;
		}

		qty[index] += amount;
		moneyMine -= cost[index]*amount;

		System.out.println("\nYou now have " + qty[index] + nameSuppliesPlural[index] + " & $" + moneyMine + " remaining");
	}

	public void shopping(Scanner sc) { // buy what
		System.out.println("\nWhat do you want to buy: Lemons, Sugar, Ice, or Cups?");
		String buy = sc.nextLine().toLowerCase();
		while (!(buy.contains("lemon") || buy.contains("sugar") || buy.contains("ice") || buy.contains("cup"))) {
			System.out.println("\nEnter lemons, sugar, or ice");
			buy = sc.nextLine().toLowerCase();
		}

		int l = buy.length();
		int index = (int) ((Math.floor(l / 5))+((l - 1) % 4)) * ((l % 6) / l); // Converts string to corresponding number index for arrays
		
		transaction(sc, index);
		System.out.println("Do you want to continue shopping? (y/n)");

		String response = sc.nextLine().toLowerCase();
		
		if (response.equals("y")) { // assumes no y means no
			shopping(sc);
		} 
		return;
	}
	
	public int updateRecipe(Scanner sc) {
		String response = sc.nextLine().toLowerCase();
		if (response.contains("nothing")) {
			return 5;
		}
		else if (response.contains("lemon")) {
			return 0;
		}
		else if (response.contains("sugar")) {
			return 1;
		}
		else if (response.contains("ice")) {
			return 2;
		}
		else if (response.contains("cup")) {
			return 3;
		}
		else if (response.contains("price")) {
			return 4;
		}
		else {
			System.out.println("Enter what you want to edit or \"nothing\" if you don't want to edit anything");
			return updateRecipe(sc);
		}
	}

	public void recipe(Scanner sc) {
		getRecipe("Current Recipe");
		getPricePerCup();
		System.out.println("\nWith the following recipe, you can make " + pitchCount + " pitchers of lemonade");
		
		System.out.println("\nDo you want to edit anything?");
		System.out.println("If so, type what do you want to edit (one at a time) or type \"nothing\"");

		int index = updateRecipe(sc);

		if (index == 5) {
			return;
		}
		else if (index == 4) {
			System.out.print("\nEnter desired price per cup: ");
			pricePerCup = sc.nextInt();
			String buffer1 = sc.nextLine();
		}
		else {
			System.out.print("\nEnter desired value: ");
			ratio[index] = sc.nextInt();
			String buffer2 = sc.nextLine();
		}

		setSellQty();
		
		getRecipe("Updated Recipe");
		getPricePerCup();
		System.out.println("\nWith the following recipe, you can make " + pitchCount + " pitchers of lemonade");
	}

	public double percentBuy() {
		// return percentage as double
		// quarter chance baseline
		// higher with higher temperature (less than half 2 decimals back added)
		// lower with higher price (twice the price removed)

		double percent = 0.25 + (temperature / 225.0) - (2*pricePerCup);

		if (percent < 0) {
			percent = 0;
		}

		return percent;
	}

	public void sellLemonade(Scanner sc) {
		System.out.println("Day " + (dayCount + 1) + "\n\n");

		setSellQty();
		System.out.println("You made " + pitchCount + " pitchers " + "(" + availCups + " Cups)" + " of lemonade");

		int customersBought = (int) (totalCustomers * percentBuy());
		int sold = Math.min(availCups, customersBought);

		System.out.println("You sold " + sold + " cups of lemonade today!");

		if (customersBought > availCups) {
			System.out.println("More people ( " + (customersBought - sold) +  " ) wanted to buy your lemonade, but you ran out");
		}

		double revenue = pricePerCup * sold;

		System.out.println("You made $" + revenue + " today!\n");

		for (int i = 0; i < 4; i++) {
			qty[i] -= ratio[i] * sold;
			System.out.println(qty[i]*spoilRate[i] + nameSuppliesPlural[i] + " spoiled");
			qty[i] -= qty[i]*spoilRate[i];
		}

		System.out.println("\nCurrent Inventory:");
		getInventory();

		dayCount++;
		setWeather();
		setTemperature();
		setTotalCustomers();
	}
}

	