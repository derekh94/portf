package sudokuV5;

//import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Board extends JPanel{
	private JPanel board;
	private JPanel optionsPanel;	
	private JPanel superTiles[][];
	private JPanel tiles[][][][];
	private JLabel words[][][][];
	private JButton butts[][][][];	
	private JButton optionButts[];
	private JLabel checkText;
	private Calendar start;
	private Calendar end;
	//private JPanel testtile;

	private String mode;	
	private int difficulty;

	// Getters and Setters
	public String getMode(){	
		return mode;
	}
	public void setMode(String s){
		String def = mode;
		if(s!="type"||s!="butt"){
			System.err.println("Invalid input");
		}
		else{
			def = s;
		}
	}		
	public JPanel getBoard() {
		return board;
	}
	public void setBoard(JPanel board) {
		this.board = board;
	}
	public JPanel[][] getSuperTiles() {
		return superTiles;
	}
	public void setSuperTiles(JPanel[][] superTiles) {
		this.superTiles = superTiles;
	}
	public JPanel[][][][] getTiles() {
		return tiles;
	}
	public void setTiles(JPanel[][][][] tiles) {
		this.tiles = tiles;
	}
	public JLabel[][][][] getWords() {
		return words;
	}
	public void setWords(JLabel[][][][] words) {
		this.words = words;
	}
	public JPanel getOptionsPanel() {
		return optionsPanel;
	}
	public void setOptionsPanel(JPanel optionsPanel) {
		this.optionsPanel = optionsPanel;
	}
	public JButton[][][][] getButts() {
		return butts;
	}
	public void setButts(JButton[][][][] butts) {
		this.butts = butts;
	}
	public JButton[] getOptionButts() {
		return optionButts;
	}
	public void setOptionButts(JButton[] optionButts) {
		this.optionButts = optionButts;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Calendar getStart() {
		return start;
	}
	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}
	public void setEnd(Calendar end) {
		this.end = end;
	}

	
	// Constructor
	//JPanel
	//4-dimensional array: 2D array of 3 boxes by 3 boxes
	//each box has a 2D array of 3 squares by 3 squares
	public Board(String a){
		mode = a;
		
		//start timer
		setStart(Calendar.getInstance());

		//backboard
		board = new JPanel();
		//board.setPreferredSize(new Dimension(1000,1000));	
		board.setPreferredSize(new Dimension(500,500));		
		board.setBackground(Color.BLACK);

		//tile thingies that hold 9 tiles
		superTiles = new JPanel[3][3];
		tiles = new JPanel[3][3][3][3];
		words = new JLabel[3][3][3][3];
		butts = new JButton[3][3][3][3];

		//options
		optionButts = new JButton[4];

		for(int i = 0;i < 3;i++){
			for(int ii = 0; ii < 3;ii++){
				superTiles[i][ii] = new JPanel();
				//superTiles[i][ii].setPreferredSize(new Dimension(300,300));	
				superTiles[i][ii].setPreferredSize(new Dimension(150,150));		
				superTiles[i][ii].setBackground(Color.GRAY);

				for(int iii = 0; iii<3;iii++){
					for(int iiii = 0; iiii<3;iiii++){
						tiles[i][ii][iii][iiii] = new JPanel();
						//tiles[i][ii][iii][iiii].setPreferredSize(new Dimension(90,90));	
						tiles[i][ii][iii][iiii].setPreferredSize(new Dimension(45,45));		
						tiles[i][ii][iii][iiii].setBackground(Color.WHITE);

						superTiles[i][ii].add(tiles[i][ii][iii][iiii]);	
						if(mode=="type"){	
							words[i][ii][iii][iiii] = new JLabel();

							//words[i][ii][iii][iiii].setFont(new Font("Times New Roman",Font.BOLD, 48));	
							words[i][ii][iii][iiii].setFont(new Font("Times New Roman",Font.BOLD, 24));		
							words[i][ii][iii][iiii].setText("-");

							//words[i][ii][iii][iiii].setFont(new Font("Times New Roman",Font.BOLD, 16));
							//words[i][ii][iii][iiii].setText(i+" "+ii+" "+iii+" "+iiii);

							tiles[i][ii][iii][iiii].add(words[i][ii][iii][iiii]);
						}
						if(mode=="butt"){	
							butts[i][ii][iii][iiii] = new JButton();

							butts[i][ii][iii][iiii].setPreferredSize(new Dimension(40,40));
							butts[i][ii][iii][iiii].setFont(new Font("Times New Roman",Font.BOLD, 12));
							butts[i][ii][iii][iiii].setText("-");

							butts[i][ii][iii][iiii].addActionListener(new buttListener());	// lul
							tiles[i][ii][iii][iiii].add(butts[i][ii][iii][iiii]);
						}	

					}
				}

				board.add(superTiles[i][ii]);

			}
		}

		//Option Buttons
		optionsPanel = new JPanel();
		optionsPanel.setPreferredSize(new Dimension(400,30));
		optionsPanel.setBackground(Color.GRAY);
		
		checkText = new JLabel();	
		checkText.setFont(new Font("Times New Roman",Font.BOLD, 10));
		checkText.setForeground(Color.WHITE);
		checkText.setText("Check: ");
		optionsPanel.add(checkText);
		for (int i = 0;i<4;i++){
			optionButts[i] = new JButton();
			optionButts[i].setPreferredSize(new Dimension(75,25));
			optionButts[i].setFont(new Font("Times New Roman",Font.BOLD, 10));
			optionButts[i].setText("-");
			optionButts[i].addActionListener(new optionButtListener());
			optionsPanel.add(optionButts[i]);

		}
		
		optionButts[0].setText("Board");
		optionButts[1].setText("Columns");
		optionButts[2].setText("Rows");
		optionButts[3].setText("Boxes");

		board.add(optionsPanel);	

		add(board);	

	}

	// Methods
	//set number by tile and supertile
	public void SetNumber(int num,int sTile,int tile){ 
		String value = String.valueOf(num);
		//count in base 3, first 2 num = supertiles, second 2 num = tiles
		words[sTile/3][sTile%3][tile/3][tile%3].setText(value);

	}
	//set number by row and column
	public void SetNumberRC(int num,int row,int col){ 
		String value = "-";
		if (num != 0){
			value = String.valueOf(num);
		}
		//count in base 3, first 2 num = supertiles, second 2 num = tiles		
		butts[row/3][col/3][row%3][col%3].setText(value);


	}

	public void Generator(){
		BoardGenerator lol = new BoardGenerator();
		lol.genNum();
		lol.rand(1);
		lol.rand(0);
		int[][] b = lol.getA();

		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				SetNumberRC(b[i][j], j, i);
			}
		}
		// DIFFICULTY CHANGER
		Random rand = new Random();
		int rand1 = rand.nextInt(10 - 1);
		int rand2 = rand.nextInt(10 - 1);

		switch(difficulty)
		{
		case 1:
			for (int x = 0; x<10 ; x++){
				SetNumberRC(0, rand1, rand2);
				rand1 = rand.nextInt(10 - 1);
				rand2 = rand.nextInt(10 - 1);
			}
			break;
		case 2:
			for (int x = 0; x<15 ; x++){
				SetNumberRC(0, rand1, rand2);
				rand1 = rand.nextInt(10 - 1);
				rand2 = rand.nextInt(10 - 1);
			}
			break;
		case 3:
			for (int x = 0; x<25 ; x++){
				SetNumberRC(0, rand1, rand2);
				rand1 = rand.nextInt(10 - 1);
				rand2 = rand.nextInt(10 - 1);
			}
			break;
		default: 
			// Invalid Input
		}
	}

	//checking per row
	public boolean CheckRowV2(int row)
	{
		Set<String> checker = new TreeSet<String>();
		boolean isUnique = true;
		if(mode=="type"){
			for (int i = 0;i<9;i++){
				if(checker.contains(words[row/3][i/3][row%3][i%3].getText())){
					isUnique = false;
				}
				checker.add(words[row/3][i/3][row%3][i%3].getText());
			}
		}
		if(mode=="butt"){
			for (int i = 0;i<9;i++){
				if(checker.contains(butts[row/3][i/3][row%3][i%3].getText())){
					isUnique = false;
				}
				checker.add(butts[row/3][i/3][row%3][i%3].getText());
			}
		}

		if(isUnique==true&&checker.contains("-")!=true){
			String tru = String.format("Row %d is correct", row + 1);	
			System.out.println(tru);
			return true;
		}
		else{
			String tru = String.format("Row %d is incorrect", row + 1);	
			System.out.println(tru);
			return false;
		}		
	}

	//checking per column
	public boolean CheckColumnV2(int col)
	{
		Set<String> checker = new TreeSet<String>();
		boolean isUnique = true;
		if(mode=="type"){
			for (int i = 0;i<9;i++){
				if(checker.contains(words[i/3][col/3][i%3][col%3].getText())){
					isUnique = false;
				}
				checker.add(words[i/3][col/3][i%3][col%3].getText());
			}
		}
		if(mode=="butt"){
			for (int i = 0;i<9;i++){
				if(checker.contains(butts[i/3][col/3][i%3][col%3].getText())){
					isUnique = false;
				}
				checker.add(butts[i/3][col/3][i%3][col%3].getText());
			}
		}


		if(isUnique==true&&checker.contains("-")!=true){
			String tru = String.format("Column %d is correct", col + 1);	
			System.out.println(tru);
			return true;
		}
		else{
			String tru = String.format("Column %d is incorrect", col + 1);	
			System.out.println(tru);
			return false;
		}		
	}

	//checking parent tiles (the 9 major boxes)
	public boolean CheckSuperTileV2(int tile)
	{
		Set<String> checker = new TreeSet<String>();
		boolean isUnique = true;
		if(mode=="type"){
			for (int i = 0;i<9;i++){
				if(checker.contains(words[tile/3][tile%3][i/3][i%3].getText())){
					isUnique = false;
				}
				checker.add(words[tile/3][tile%3][i/3][i%3].getText());
			}
		}
		if(mode=="butt"){
			for (int i = 0;i<9;i++){
				if(checker.contains(butts[tile/3][tile%3][i/3][i%3].getText())){
					isUnique = false;
				}
				checker.add(butts[tile/3][tile%3][i/3][i%3].getText());
			}
		}

		if(isUnique==true&&checker.contains("-")!=true){
			String tru = String.format("Box %d is correct", tile + 1);	
			System.out.println(tru);
			return true;
		}
		else{
			String tru = String.format("Box %d is incorrect", tile + 1);	
			System.out.println(tru);
			return false;
		}		
	}

	//checking win condition
	public boolean CheckWinV2()
	{
		boolean didWin = true;
		for(int i = 0;i<9;i++)
		{
			if(CheckRowV2(i)==false){
				didWin = false;
			}
			if(CheckColumnV2(i)==false){
				didWin = false;
			}
			if(CheckSuperTileV2(i)==false){
				didWin = false;
			}	
			System.out.println();	
		}

		if(didWin==true){
			//end timer
			setEnd(Calendar.getInstance());
			
			//print winning
			System.out.print("Status: ");	
			System.out.println("You're winner!");
			JOptionPane.showMessageDialog(board,"You're winner!");
			System.out.format("Solve time: %02d:%02d", (end.get(Calendar.MINUTE) - start.get(Calendar.MINUTE)), (end.get(Calendar.SECOND) - start.get(Calendar.SECOND)));
			
			return true;
		}
		else{
			System.out.print("Status: ");	
			System.out.println("nope.avi");
			JOptionPane.showMessageDialog(board,"Nope.avi");
			//System.out.print("Input Number (-1 to Exit): ");	// for mode 'type'
			return false;
		}
	}

	//action listeners
	private class buttListener implements ActionListener{	//buttListener listens for butts
		public void actionPerformed(ActionEvent e)
		{
			for(int i = 0; i < 3; i++){
				for (int ii=0; ii<3;ii++){	//listens from all the pokes
					for (int iii=0; iii<3;iii++){
						for (int iiii=0; iiii<3;iiii++){
							if(butts[i][ii][iii][iiii].equals(e.getSource())){//if poked
								//System.out.println("Button pressed");
								// Exception Handle butt input (not strings and out of bounds)
								try 	
								{		// Check to see if input is integer
									int num = Integer.parseInt(JOptionPane.showInputDialog("Input number: ")); 
									if (num > 9 || num < 1)	// Out of Bounds
									{
										System.out.println("*Out of Bounds*");	// Out of Bounds or Invalid command
									}
									else{
										String value = String.valueOf(num);
										butts[i][ii][iii][iiii].setText(value);
									}
								} 
								catch (Exception fail)
								{
									System.out.println("*The Input is not recognized*");	
								}								
							}
						}
					}			
				}
			}
		}
	}

	private class optionButtListener implements ActionListener{
		public void actionPerformed(ActionEvent o)
		{
			System.out.println();
			if(optionButts[0].equals(o.getSource())){	//check win
				//System.out.println("Button 1 Pressed");
				System.out.println("*Checking if Board is complete*");	// for mode 'butt'
				CheckWinV2();
			}
			if(optionButts[1].equals(o.getSource())){	//check column
				//System.out.println("Button 2 Pressed");
				System.out.println("*Checking if Columns are complete*");	// for mode 'butt'
				for(int i = 0;i<9;i++){
					CheckColumnV2(i);
				}
			}
			if(optionButts[2].equals(o.getSource())){	//check rows
				//System.out.println("Button 3 Pressed");
				System.out.println("*Checking if Rows are complete*");	// for mode 'butt'
				for(int i = 0;i<9;i++){
					CheckRowV2(i);
				}
			}
			if(optionButts[3].equals(o.getSource())){	//check stile
				//System.out.println("Button 4 Pressed");
				System.out.println("*Checking if Boxes are complete*");	// for mode 'butt'
				for(int i = 0;i<9;i++){
					CheckSuperTileV2(i);
				}
			}
		}

	}

}
