package sudokuV5;

import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Game 
{
	public int userDifficulty()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("**Enter Difficulty Level**");
		int userInt =0;
		
		
		do{
			System.out.println("1) Easy");
			System.out.println("2) Medium");
			System.out.println("3) Hard");
			System.out.print("> ");
			String input = scan.nextLine();
			try 
			{		// Check to see if input is integer
				userInt = Integer.parseInt(input);    
			} 
			catch (Exception fail)
			{
				//System.out.println("*The Input is not recognized*");
			}	

			if(userInt < 1 || userInt > 3)
			{
				System.out.println("*Invalid Setting*");	
			}

		}while(userInt <1 || userInt >3);

		System.out.println("*Difficulty Set*");
		
		return (userInt);
	}

}
