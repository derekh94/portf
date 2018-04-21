package sudokuV5;

import java.util.Random;

/*This class handles populating the board with complete sudoku boards.*/

public class BoardGenerator {
	private int[][] a= new int[9][9];
	
	public int[][] getA() {
		return a;
	}

	public void setA(int[][] a) {
		this.a = a;
	}

	public void genNum() {
	   int k=1,n=1;
	   for(int i=0;i<9;i++)	/*Two for loops are used to fill the 2D array*/
	   {					/*Fills it up in a way that it fulfills the requirement of being 1-9*/
	      k=n;
	      for(int j=0;j<9;j++)
	      {
			if(k<=9){
	              a[i][j]=k;
	              k++;
	          }else{
	           k=1;
	           a[i][j]=k;
	           k++;
	          }
	      }
	      n=k+3; 		/*This is set up k and n variables for the next increment of the loop*/
	      if(k==10)		/*If it's 10, n=4; if it's less than 9, do modulus 9 + 1;*/
	      n=4;
	      if(n>9)
	      n=(n%9)+1;
	   }
	}
	
	public void rand(int check) {
		int k1,k2,max=2,min=0;
		Random r= new Random();
		   for(int i=0;i<3;i++)
		   {
		      k1=r.nextInt(max-min+1)+min;
		      do{
		         k2=r.nextInt(max-min+1)+min;
		      }while(k1==k2);
		      max+=3;min+=3; /*This method is called 2 times during board generation times*/
		      if(check==1)  /*The 2 change methods are used to ensure the randomness/uniqueness*/
		      changerow(k1,k2);
		      else if(check==0)
		      changecol(k1,k2);
		      }
		   }
	
	
	/*These two function handles permutation of row and columns to create unique boards*/
	public void changerow(int k1,int k2) {
		int temp;
		   for(int j=0;j<9;j++)
		   {
		      temp=a[k1][j];
		      a[k1][j]=a[k2][j];
		      a[k2][j]=temp;
		   }  
	}
	
	public void changecol(int k1,int k2) {
		int temp;
		   for(int j=0;j<9;j++)
		   {
		      temp=a[j][k1];
		      a[j][k1]=a[j][k2];
		      a[j][k2]=temp;
		   }
	}
}