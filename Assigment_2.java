import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public class Assigment_2<moveDown> {
	
//                                    1024/2048  Game
//                              Julia Dobrovodska  3061278
//---------------------------------------------------------------------------------------------------------------------
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int size;
		
Scanner in = new Scanner(System.in);
Random rnd = new Random();

System.out.println("Welcome to 1024 game, please enter size of the grid 4- 10: ");

while(true) {
	size = in.nextInt();
	 if (size > 10 || size<4) {
		System.out.printf("\"Please enter valid number. Numbers smaller than 10 or higher than 4 not allowed, please submit number again\" ");
  
    }else {
	    break;   
	}
}

int[][] board = getBoard(size);

 boolean slotExist=true;
 boolean canMove = false;
 
int score = 0;
while(slotExist == true) {
	System.out.println("Please enter one of this 4 options \r\n"
			+ "1. W = Up\r\n"
			+ "2. S = Down\r\n"
			+ "3. A = Left\r\n"
			+ "4. D = Right");
	char direction = in.next().charAt(0);

	//TODO.. until array is not full 
	switch(direction) {
	    case 'w':  //going up
	    	
			board = updateBoard(board, direction);
	  
	    break;
	    case 'a': //going left
			board = updateBoard(board, direction);
		
	    break;
	    case 's': //going down	     
			board = updateBoard(board, direction);
		
	    break;
	    case 'd':  //going right
			board = updateBoard(board, direction);
			 
		
	    break;
	    
		case 'q': return;
		default: System.out.println("Invalid character, please enter again: ");{
			board = updateBoard(board, direction);}
		}
	    score = score(board);
	    if (score == 1024) {
       	System.out.println("Congratulation you won!");
        }
	  System.out.println("Your score is : " + score);
	
	
	  
	  int found = 0;
	
	for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] == 0 ) { // need to do now only if 2 numbers euqals i still have valid move
            	found++;
            }
            if ( found>0) {
    			slotExist=true;}
          else {
            	slotExist=false;// return false as soon as a non-empty is found
            	
            }
        }
 }
}    	 
	    
 System.out.println("Game over! No more moves left!");
}



	private static String getTopBottomBorder(int size) {
		String line = " ";
		
		for(int i = 0; i < size-1; i++) {
			line += "-------";
		}
		line += "------";	
		line += " ";
		return line;
	}

	
	private static String getInnerBorder(int size) {
		String line = "|";
		
		for(int i = 0; i < size-1; i++) {
			line += "------+";
		}
		line += "------";
		
		
		line += "|";
		return line;
			
	}
	
	
	private static char[] getPaddedCell(int number) {
			
		char[] numChars = ("" + number).toCharArray();
		char[] cellChars = {' ', ' ', ' ', ' ', ' ', ' ', '|'};
		cellChars[0] = ' ';
		int startIndex = numChars.length < 3 ? 1 : 0;
		for(int i = 0; i < numChars.length; i++) {
			cellChars[i+1 + startIndex] = numChars[i];
		}

		return cellChars;
		
	}
	
	
	private static char[] getNumericLine(int nums[]) {
		char charLine[] = new char[1 + (nums.length * 7)];
		charLine[0] = '|';
		
		for(int i = 0; i < nums.length; i++) {
			char[] paddedCell = getPaddedCell(nums[i]);
			for(int j = 0; j< paddedCell.length; j++) {
				charLine[1+i*7+j] = paddedCell[j];
			}
		}
		return charLine;	
	}
	
	private static void printBoard(int[][] board) {
		System.out.println(getTopBottomBorder(board.length));
		for(int j=0; j<board.length;j++) {
				if(j > 0) {
				System.out.println(getInnerBorder(board.length));
				}
				System.out.println(getNumericLine(board[j]));
			
		}
		System.out.println(getTopBottomBorder(board.length));
	}
	/* 
	    * Name: Board (the constructor)
	    * Purpose:  Constructs a fresh board with  tiles
	    * Parameter:int size e:set the size of the grid; iuserinpunt
  
	    * */ 
	private static int[][] getBoard(int size) {
		Random rnd = new Random();
		int[][] board;
		board = new int[size][size];
		int midpoint = Math.round(size/2);
		int random1 = rnd.nextInt(midpoint);
		int random2 = rnd.nextInt(size - midpoint)+midpoint;
		board[random1][random1] = 1;
		board[random2][random2] = 1;
		
		
		printBoard(board);
		return board;
	}
	
	
	private static int[][] updateBoard(int[][] board, char direction) {
		// 
		Random rnd = new Random(); 
		//my new board manualy putting everything in new board from old board so  later i can compere
		int[][] newBoard = new int[board.length][board[0].length];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				newBoard[x][y] = board[x][y];
			}
		}
		
		//move everything down
		if (direction == 's' || direction =='S') {
			moveDown(newBoard);
			 // checking if is valid move to go down
		}	
		// move everything up
		else if(direction == 'w' || direction =='W') {
			moveUp(newBoard);
			 // checking if is valid move to go up
		}
		//move right
		else if(direction == 'd' || direction == 'D') {
			moveRight(newBoard);
			 // checking if is valid move to go right
           
			 
		}
		//move left
		else if(direction == 'a' || direction == 'A') {
			moveLeft(newBoard);
			  
	     // checking if is valid move to go left
	     
		}
		
		// checking availabe slots in my array and putting randomly nuber in free slots and than printing board
		//also checking if the move is valid if it's not valid new 1 won't generate
		boolean boardHasChanged = false;
		for(int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != newBoard[x][y]) {
					boardHasChanged = true;
				}
			}
		}
		if (boardHasChanged) {
			slot(newBoard);
		}
		else {
			System.out.println("Invalid move please enter other direction.");
		}
		printBoard(newBoard);
		
		
		return newBoard;
		
	}
	private static boolean moveDown(int newBoard[][]) {
		// TODO Auto-generated method stub
		
		 for(int col=0;col<newBoard.length;col++){
	         int count=0;
	         for(int row=newBoard.length-1;row>=0;row--)
	         {
	            if(newBoard[row][col]!=0)
	            {   newBoard[newBoard.length-1-count][col]=newBoard[row][col];
	               //empty the original tile position after tile is moved
	               if(newBoard.length-1-count!=row)
	               { newBoard[row][col]=0;}
	               count++;}
	         }
	      }
	      for(int col=0;col<newBoard.length;col++)
	      {for(int row=newBoard.length-1;row>0;row--)
	         {if(newBoard[row][col]==newBoard[row-1][col])
	            //add the two tiles with the same value
	            {newBoard[row][col]+=newBoard[row-1][col];
	            newBoard[row-1][col]=0;
	               
	               break;

	            } } }

	      //after adding two tiles, repeat the same first for loop
	      //in this method to move all the tiles to the bottom
	      for(int col=0;col<newBoard.length;col++){
	         int count=0;

	         for(int row=newBoard.length-1;row>=0;row--)

	         {
	            if(newBoard[row][col]!=0)
	            {   newBoard[newBoard.length-1-count][col]=newBoard[row][col];
	               //empty the original tile position after tile is moved
	               if(newBoard.length-1-count!=row)
	               { newBoard[row][col]=0;}
	               count++;}

	         }

	      }
		return false;
	}



	private static void moveUp(int newBoard[][]) {
		// TODO Auto-generated method stub
	    for(int col=0;col<newBoard.length;col++){
	         int count=0;

	         for(int row=0;row<newBoard.length;row++)

	         {
	            if(newBoard[row][col]!=0)
	            {   newBoard[0+count][col]=newBoard[row][col];
	               //empty the original tile position after tile is moved
	               if(0+count!=row)
	               { newBoard[row][col]=0;}
	               count++;}
	         }
	      }
	      for(int col=0;col<newBoard.length;col++)
	      {for(int row=0;row<newBoard.length-1;row++)
	         {if(newBoard[row][col]==newBoard[row+1][col])
	            //add the two tiles with the same value
	            {newBoard[row][col]+=newBoard[row+1][col];
	               newBoard[row+1][col]=0;	              
	               break;		
	}}}
	      //adding two tiles, i have to repeat the same first for loop
	      // move all  to the up 
	      for(int col=0;col<newBoard.length;col++){
	          int count=0;

	          for(int row=0;row<newBoard.length;row++)

	          {
	             if(newBoard[row][col]!=0)
	             {  newBoard[0+count][col]=newBoard[row][col];
	                //empty the original tile position after tile is moved
	                if(0+count!=row)
	                { newBoard[row][col]=0;}
	                count++;}
	          }
	       }
	}
	private static void moveRight(int newBoard[][]) {
		// TODO Auto-generated method stub
		 {  //use count to move the tiles to the right one by one
		      for(int row=0;row<newBoard.length;row++){
		         int count=0;

		         for(int col=newBoard.length-1;col>=0;col--)

		         {
		            if(newBoard[row][col]!=0) // if it's not its not equal 0
		            {   newBoard[row][newBoard.length-1-count]=newBoard[row][col];
		               //empty the original tile position after tile is moved
		               if(newBoard.length-1-count!=col)
		               { newBoard[row][col]=0;}
		               count++;}

		     }}}
		   for(int row=0;row<newBoard.length;row++)
		      {for(int col=newBoard.length-1;col>0;col--)
		         {if(newBoard[row][col]==newBoard[row][col-1])
		            //add the two tiles with the same value
		            {newBoard[row][col]+=newBoard[row][col-1];
		               newBoard[row][col-1]=0;    //emptying the tile          

		               break;
		            } } }
		   for(int row=0;row<newBoard.length;row++){
		         int count=0;

		         for(int col=newBoard.length-1;col>=0;col--)

		         {
		            if(newBoard[row][col]!=0)
		            {   newBoard[row][newBoard.length-1-count]=newBoard[row][col];
		               if(newBoard.length-1-count!=col)
		               {newBoard[row][col]=0;}
		               count++;}

		         }

		      }
		      //adding two tiles, i have to repeat the same first for loop
		      // move all  to the right side
		      for(int row=0;row<newBoard.length;row++){
		         int count=0;

		         for(int col=newBoard.length-1;col>=0;col--)

		         {
		            if(newBoard[row][col]!=0)
		            {   newBoard[row][newBoard.length-1-count]=newBoard[row][col];
		               if(newBoard.length-1-count!=col)
		               {newBoard[row][col]=0;}
		               count++;}			  
		            } }	}
	



	private static void moveLeft(int newBoard[][]) {
		// TODO Auto-generated method stub
		
		// we have 1,initial , 2. merge, 3 move
		 for(int row=0;row<newBoard.length;row++){
	         int count=0;
	         for(int col=0;col<newBoard.length;col++)

	         {
	            if(newBoard[row][col]!=0)
	            {   newBoard[row][0+count]=newBoard[row][col];
	               //Empty the original tile position once tile is moved 
	               if(0+count!=col)
	               {  newBoard[row][col]=0;}
	               count++;}
	            
	          } }
	   for(int row=0;row<newBoard.length;row++)
	      {for(int col=0;col<newBoard.length-1;col++)
	         {if(newBoard[row][col]==newBoard[row][col+1])
	            //add the two tiles with the same value
	            {newBoard[row][col]+=newBoard[row][col+1];
	               newBoard[row][col+1]=0;      
	              
	               break;
} }	      }
  //adding two tiles, repeat the first for loop
  //move all  to the left side
  for(int row=0;row<newBoard.length;row++){
     int count=0;
     for(int col=0;col<newBoard.length;col++)

     {
        if(newBoard[row][col]!=0)
        {   newBoard[row][0+count]=newBoard[row][col];
           //Empty the original tile position once tile is moved 
           if(0+count!=col)
           {  newBoard[row][col]=0;}
           count++;}
     }

  }
	}



	//method for my score
	private static int score(int board[][]) {
		int score = 0;
		  for (int i = 0; i < board.length; i++) {
		        for (int j = 0; j < board[i].length; j++) {
		            if (board[i][j] > score) {
		               score = board[i][j];
		              }}
		        }
		return score;	
	}

	private static void slot(int board[][]) {
		// checking availabe slots in my array and putting randomly number in free slots.
		List<int[]> availableSlots = new ArrayList<int[]>();

		for (int x = 0; x < board.length; x++) {
		  for (int y = 0; y < board[x].length; y++) {
		    int[] slotCoordinates = { x, y };
		    if (board[x][y] == 0) {
		      availableSlots.add(slotCoordinates);
		    }
		  }
		}
		Random randomSlotSelector = new Random();
		
		int selectedSlotIndex = randomSlotSelector.nextInt(availableSlots.size());
		int[] selectedSlot = availableSlots.get(selectedSlotIndex);

		board[selectedSlot[0]][selectedSlot[1]] = 1;
	}



}
	
	

