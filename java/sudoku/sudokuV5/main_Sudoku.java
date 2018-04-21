package sudokuV5;

//import java.util.Random;
//import java.util.Scanner;
//import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.WindowEvent;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;

public class main_Sudoku
{

	public static void main(String args[])
	{
		
		Game gameDifficulty = new Game();
		int difficulty = gameDifficulty.userDifficulty();
		
		// Create Frame
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w.setPreferredSize(new Dimension(1400,1400));		
		w.setSize(new Dimension(500, 544));			
		w.setVisible(true);

		// Create and Add Board to Frame	
		Board gaem = new Board("butt");	

		gaem.setDifficulty(difficulty);				// Set Difficulty

		// THIS IS TO GENERATE FULL BOARD
		gaem.Generator();

		w.add(gaem);

	}
}
