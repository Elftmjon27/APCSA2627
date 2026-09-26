import java.util.Scanner;

public class InteractiveDogYears
{
	private static int humanyears; // declared, but not initialized
	private static int dogyears;	

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("How old is your dog in human years? ");
		
		Age(s.nextInt());
	}

	private static void Age(int n)
	{
		humanyears = n;
		dogyears = humanyears * 7;
		System.out.println("Your dog is " + dogyears + " years old" );
	}	
}
